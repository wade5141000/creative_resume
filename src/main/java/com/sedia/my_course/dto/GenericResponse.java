package com.sedia.my_course.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

	private boolean success;

	private String message;

	private T data;

	public GenericResponse(String message, T data) {
		this.success = true;
		this.message = message;
		this.data = data;
	}

	public GenericResponse(T data) {
		this.success = true;
		this.message = null;
		this.data = data;
	}

}