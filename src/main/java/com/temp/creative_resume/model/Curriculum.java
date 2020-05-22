package com.temp.creative_resume.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
    課表
 */
@Entity
@Getter
@Setter
public class Curriculum {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer totalUnits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="CURRICULUM_ID_FK")
    private List<Course> courses;

}
