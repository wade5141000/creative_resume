package com.sedia.my_course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
    課表
 */
@Entity
@Getter
@Setter
public class CourseTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseTableId;
	// 課表名稱
    private String courseTableName;
    // 一周哪幾天排課 星期一,星期二,星期三
    private String days;
	// 課表顯示幾節課
	private String lessons;
    // 課程
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//	    name="course_table_and_course",
//	    joinColumns={@JoinColumn(name="COURSE_TABLE_FK")},
//	    inverseJoinColumns={@JoinColumn(name="COURSE_FK")}
//    )
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="COURSE_TABLE_ID_FK")
    private List<Course> courses;


}
