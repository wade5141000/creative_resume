package com.sedia.my_course.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

    private String message;

    private T data;
 
}