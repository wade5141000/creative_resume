package com.temp.creative_resume.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
    使用者
 */
@Entity
@Getter
@Setter
@Table(name = "all_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true,name = "USER_NAME")
    private String userName;

    private String email;

    private String account;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID_FK")
    private List<Curriculum> curricula;


}