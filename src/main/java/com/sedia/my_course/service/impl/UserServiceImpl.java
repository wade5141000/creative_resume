package com.sedia.my_course.service.impl;

import com.sedia.my_course.dao.PasswordResetTokenDao;
import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.user.PasswordResetToken;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.model.user.UserRole;
import com.sedia.my_course.service.UserService;
import lombok.SneakyThrows;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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
	public User findUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		PasswordResetToken userToken = new PasswordResetToken(user, token);
		passwordResetTokenDao.save(userToken);
		mailSender.send(constructResetTokenEmail(request.getContextPath(), token, user));
	}

	private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
		String url = contextPath + "/user/changePassword?token=" + token;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Reset Password");
		email.setText("請點擊連結重置密碼：" + url);
		email.setTo("wade5141000@outlook.com");
		return email;
	}
}
