package com.sedia.my_course.service.impl;

import com.sedia.my_course.dao.PasswordResetTokenDao;
import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.PasswordResetToken;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.entity.user.UserRole;
import com.sedia.my_course.service.UserService;
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
@Service("UserService")
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;

	@Resource
	private PasswordResetTokenDao passwordResetTokenDao;

	@Resource
	JavaMailSender mailSender;

	@Override
	@SneakyThrows
  public void addNewUser(User user){
		if(userDao.getUserByAccount(user.getAccount()) == null){
			user.setPassword(encryptPassword(user.getPassword()));
			List<UserRole> authorities = new ArrayList<>();
			authorities.add(new UserRole("GENERAL"));
			user.setAuthorities(authorities);
			userDao.save(user);
		}else{
			throw new Exception("account already exists");
		}
  }

	@Override
  public Iterable<User> getAllUsers(){
        return userDao.findAll();
    }

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		PasswordResetToken userToken = new PasswordResetToken(user, token);
		passwordResetTokenDao.save(userToken);
		constructResetTokenEmail(request.getContextPath(), token, user);
		mailSender.send(constructResetTokenEmail(request.getContextPath(), token, user));
	}

	@Override
	public String validatePasswordResetToken(String token) {
		PasswordResetToken passToken = passwordResetTokenDao.getPasswordResetTokenByToken(token);
		return !isTokenFound(passToken) ? "invalidToken"
			: isTokenExpired(passToken) ? "expired"
			: null;
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		return passwordResetTokenDao.getPasswordResetTokenByToken(token).getUser();
	}

	@Override
	public void changeUserPassword(PasswordDto passwordDto) {
		User user = getUserByPasswordResetToken(passwordDto.getToken());
		user.setPassword(encryptPassword(passwordDto.getPassword()));
		userDao.save(user);
	}

	@SneakyThrows
	private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
		String ip = InetAddress.getLocalHost().getHostAddress();
		String resetUrl = "http://" + ip + ":8080" + contextPath + "/user/changePassword?token=" + token;
		System.out.println(resetUrl);
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

	private String encryptPassword(String password){
		return new BCryptPasswordEncoder().encode(password);
	}
}
