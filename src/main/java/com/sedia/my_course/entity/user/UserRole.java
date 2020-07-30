package com.sedia.my_course.entity.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
	使用者權限
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserRole implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	// 使用者角色
	private String role;

	public UserRole(String role){
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}
}
