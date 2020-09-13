package com.sedia.my_course.repository;

import com.sedia.my_course.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByAccount(String account);

	boolean existsByEmail(String email);

	Optional<User> findUserByAccount(String account);

	Optional<User> findUserByEmail(String email);

}