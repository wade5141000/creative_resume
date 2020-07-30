package com.sedia.my_course.service;

import com.sedia.my_course.dto.PasswordDto;
import com.sedia.my_course.entity.user.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	void addNewUser(User user);

	Iterable<User> getAllUsers();

	User getUserByEmail(String email);

	void createPasswordResetTokenForUser(User user, HttpServletRequest request);

	String validatePasswordResetToken(String token);

	User getUserByPasswordResetToken(String token);

	void changeUserPassword(PasswordDto passwordDto);

}
