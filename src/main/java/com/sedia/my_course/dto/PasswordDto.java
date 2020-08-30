package com.sedia.my_course.dto;

import lombok.Data;

@Data
public class PasswordDto {

	private String token;

	private String password;

	private String confirmPassword;
}
