package com.sedia.my_course.repository;

import com.sedia.my_course.entity.user.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	Optional<PasswordResetToken> findPasswordResetTokenByToken(String token);

}