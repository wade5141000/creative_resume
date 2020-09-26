package com.sedia.my_course.entity.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pwResetId;

	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	private LocalDateTime expiryDate;

	private boolean used;

	public PasswordResetToken(User user, String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = LocalDateTime.now().plusDays(1);
	}
}