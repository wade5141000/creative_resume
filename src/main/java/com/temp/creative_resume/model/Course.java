package com.temp.creative_resume.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
	// 星期幾
	private String dayOfTheWeek;
	// 節次
	private String period;

	@ManyToMany(cascade=CascadeType.ALL, mappedBy="courses")
	private List<CourseTable> courseTables;


}
