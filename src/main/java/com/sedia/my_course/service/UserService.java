package com.sedia.my_course.service;

import com.sedia.my_course.model.user.User;

public interface UserService {

	void addNewUser(User user);

	Iterable<User> getAllUsers();

}
