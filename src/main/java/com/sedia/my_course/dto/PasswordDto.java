package com.sedia.my_course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {

	private String token;

	private String password;

	private String confirmPassword;
}
