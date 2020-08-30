package com.sedia.my_course.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 課程
 */
@Entity
@Data
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	// 課程名稱
	private String courseName;
	// 課程代碼
	private String courseNumber;
	// 選修、必修、通識
	private String type;
	// 抵免、已修、未修
	private String status;
	// 學分
	private Integer units;
	// 授課老師
	private String teacher;
	// 教室
	private String classRoom;
	// 分數
	private Double score;
	// 備註
	private String remark;
	// 星期幾
	private String dayOfTheWeek;
	// 起始節次
	private String periodStart;
	// 結束節次
	private String periodEnd;
	// 課表
//	@ManyToMany(cascade=CascadeType.ALL, mappedBy="courses")
//	private List<CourseTable> courseTables;


}
