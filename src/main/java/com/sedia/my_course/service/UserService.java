package com.sedia.my_course.service;

import com.sedia.my_course.model.user.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	void addNewUser(User user);

	Iterable<User> getAllUsers();

	User findUserByEmail(String email);

	void createPasswordResetTokenForUser(User user, HttpServletRequest request);

}
