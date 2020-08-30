package com.sedia.my_course.repository;

import com.sedia.my_course.entity.user.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	PasswordResetToken findPasswordResetTokenByToken(String token);


}