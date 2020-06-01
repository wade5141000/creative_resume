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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer courseTableId;

    private String courseTableName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="COURSE_TABLE_ID_FK")
    private List<Course> courses;

}
