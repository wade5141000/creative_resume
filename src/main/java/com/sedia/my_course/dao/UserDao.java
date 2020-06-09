package com.sedia.my_course.dao;

import com.sedia.my_course.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User getUserByUserId(Integer id);

    User getUserByAccount(String account);

}