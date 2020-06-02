package com.temp.creative_resume.model;

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

    private String courseTableName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
	    name="course_table_and_course",
	    joinColumns={@JoinColumn(name="COURSE_TABLE_FK")},
	    inverseJoinColumns={@JoinColumn(name="COURSE_FK")}
    )
    private List<Course> courses;


}
