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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
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

	public void createPasswordResetTokenForUser(User user, String contextPath) {
		String token = UUID.randomUUID().toString();
		PasswordResetToken userToken = new PasswordResetToken(user, token);
		passwordResetTokenRepository.save(userToken);
		mailSender.send(constructResetTokenEmail(contextPath, token, user));
	}

	public boolean validatePasswordResetToken(String token) {
		return passwordResetTokenRepository.findPasswordResetTokenByToken(token)
				.map(this::validToken)
				.orElse(false);
	}

	public void changeUserPassword(PasswordDto passwordDto) {
		User user = passwordResetTokenRepository.findPasswordResetTokenByToken(passwordDto.getToken())
				.stream()
				.peek(token -> token.setUsed(true))
				.map(passwordResetTokenRepository::save)
				.map(PasswordResetToken::getUser)
				.findAny()
				.orElseThrow(ExceptionUtil.nullPointerException("找不到此token的使用者:[" + passwordDto.getToken() + "]"));
		user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
		userRepository.save(user);
	}

	@SneakyThrows
	private MimeMessage constructResetTokenEmail(String contextPath, String token, User user) {
		String ip = InetAddress.getLocalHost().getHostAddress();
		// FIXME URL
		String resetUrl = "http://" + ip + ":8080" + contextPath + "/user/changePassword?token=" + token;
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
		helper.setTo("wade5141000@outlook.com");
		helper.setSubject("Reset Password");
		helper.setText("<h2>請點擊連結：<a href='" + resetUrl + "'>重置你的密碼</a></h2>",true);
		return mimeMessage;
	}

	private boolean validToken(PasswordResetToken passToken) {
		return LocalDateTime.now().isBefore(passToken.getExpiryDate()) && !passToken.isUsed();
	}

}
