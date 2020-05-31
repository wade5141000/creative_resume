package com.temp.creative_resume.dao;

import com.temp.creative_resume.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User getUserByUserId(Integer id);

    User getUserByUsername(String username);

}