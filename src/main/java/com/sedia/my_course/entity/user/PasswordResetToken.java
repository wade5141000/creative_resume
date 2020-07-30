package com.sedia.my_course.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pwResetId;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable=false, name = "user_id")
    private User user;
 
    private LocalDateTime expiryDate;

    public PasswordResetToken(User user, String token){
    	this.user = user;
    	this.token = token;
    	this.expiryDate = LocalDateTime.now().plusDays(1);
    }
}