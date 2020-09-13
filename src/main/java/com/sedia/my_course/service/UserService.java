package com.sedia.my_course.service;

import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.PasswordResetToken;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.entity.user.UserRole;
import com.sedia.my_course.repository.PasswordResetTokenRepository;
import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.utils.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

	final UserRepository userRepository;
	final PasswordResetTokenRepository passwordResetTokenRepository;
	final JavaMailSender mailSender;
	final BCryptPasswordEncoder passwordEncoder;

	public void addNewUser(User user) {
		if (!userRepository.existsByAccount(user.getAccount()) && !userRepository.existsByEmail(user.getEmail())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAuthorities(List.of(new UserRole("USER")));
			userRepository.save(user);
			return;
		}
		throw new RuntimeException("account or e-mail already exists");
	}

	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email)
				.orElseThrow(ExceptionUtil.nullPointerException("找不到此email的使用者:[" + email + "]"));
	}

	public void createPasswordResetTokenForUser(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		PasswordResetToken userToken = new PasswordResetToken(user, token);
		passwordResetTokenRepository.save(userToken);
		mailSender.send(constructResetTokenEmail(request.getContextPath(), token, user));
	}

	public boolean validatePasswordResetToken(String token) {
		return passwordResetTokenRepository.findPasswordResetTokenByToken(token)
				.map(this::validToken)
				.orElse(false);
	}

	public User getUserByPasswordResetToken(String token) {
		return passwordResetTokenRepository.findPasswordResetTokenByToken(token)
				.map(PasswordResetToken::getUser)
				.orElseThrow(ExceptionUtil.nullPointerException("找不到此token的使用者:[" + token + "]"));
	}

	public void changeUserPassword(PasswordDto passwordDto) {
		User user = getUserByPasswordResetToken(passwordDto.getToken());
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
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

	private boolean validToken(PasswordResetToken passToken) {
		return LocalDateTime.now().isBefore(passToken.getExpiryDate());
	}

}
