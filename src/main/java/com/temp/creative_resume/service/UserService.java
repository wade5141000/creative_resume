package com.temp.creative_resume.service;

import com.temp.creative_resume.model.user.User;

public interface UserService {

	String addNewUser(User user);

	Iterable<User> getAllUsers();

	User getUserById(String id);

}
