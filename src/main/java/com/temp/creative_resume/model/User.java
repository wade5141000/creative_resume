package com.temp.creative_resume.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/*
    使用者
 */

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID_FK")
    private List<Curriculum> curricula;


}