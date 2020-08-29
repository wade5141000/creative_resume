package com.sedia.my_course.service;

import com.sedia.my_course.repository.PasswordResetTokenRepository;
import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.PasswordResetToken;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.entity.user.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

	final UserRepository userRepository;
	final PasswordResetTokenRepository passwordResetTokenRepository;
	final JavaMailSender mailSender;

	@SneakyThrows
	public void addNewUser(User user) {
		if (userRepository.getUserByAccount(user.getAccount()) == null) {
			user.setPassword(encryptPassword(user.getPassword()));
			List<UserRole> authorities = new ArrayList<>();
			authorities.add(new UserRole("GENERAL"));
			user.setAuthorities(authorities);
			userRepository.save(user);
		} else {
			throw new Exception("account already exists");
		}
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public void createPasswordResetTokenForUser(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		PasswordResetToken userToken = new PasswordResetToken(user, token);
		passwordResetTokenRepository.save(userToken);
		constructResetTokenEmail(request.getContextPath(), token, user);
		mailSender.send(constructResetTokenEmail(request.getContextPath(), token, user));
	}

	public String validatePasswordResetToken(String token) {
		PasswordResetToken passToken = passwordResetTokenRepository.getPasswordResetTokenByToken(token);
		return !isTokenFound(passToken) ? "invalidToken"
			: isTokenExpired(passToken) ? "expired"
			: null;
	}

	public User getUserByPasswordResetToken(String token) {
		return passwordResetTokenRepository.getPasswordResetTokenByToken(token).getUser();
	}

	public void changeUserPassword(PasswordDto passwordDto) {
		User user = getUserByPasswordResetToken(passwordDto.getToken());
		user.setPassword(encryptPassword(passwordDto.getPassword()));
		userRepository.save(user);
	}

	@SneakyThrows
	private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
		String ip = InetAddress.getLocalHost().getHostAddress();
		String resetUrl = "http://" + ip + ":8080" + contextPath + "/user/changePassword?token=" + token;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Reset Password");
		email.setText("請點擊連結重置密碼：" + resetUrl);
		email.setTo("wade5141000@outlook.com");
		return email;
	}

	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {
		return !(LocalDateTime.now().isBefore(passToken.getExpiryDate()));
	}

	private String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
