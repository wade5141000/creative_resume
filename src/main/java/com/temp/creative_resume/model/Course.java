package com.temp.creative_resume.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
    課程
 */
@Entity
@Getter
@Setter
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;

	private String courseName;

	private String courseNumber;

	// 選修、必修、通識
	private String type;

	private Integer units;

	private String teacher;

	private String classRoom;

	private Double score;

	private String remark;

	// 抵免、已修、未修
	private String status;



}
