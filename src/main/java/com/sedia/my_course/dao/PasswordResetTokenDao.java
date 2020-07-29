package com.sedia.my_course.dao;

import com.sedia.my_course.model.user.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {



}