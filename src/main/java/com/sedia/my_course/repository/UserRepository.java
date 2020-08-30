package com.sedia.my_course.repository;

import com.sedia.my_course.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByAccount(String account);

	User findUserByEmail(String email);

}