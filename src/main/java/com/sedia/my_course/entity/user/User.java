package com.sedia.my_course.entity.user;

import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.CourseTable;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 使用者
 */
@Entity
@Table(name = "all_user")
@Data
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	// 帳號
	@Column(unique = true)
	private String account;
	// 密碼
	private String password;
	// 暱稱 / 姓名
	private String nickname;
	// 學校
	private String school;
	// 科系
	private String department;
	// email
	private String email;
	// 註冊時間
	private LocalDateTime createTime;
	// 課表
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_FK")
	private List<CourseTable> courseTables;
	// 課程
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_FK")
	private List<Course> courses;
	// 使用者角色
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID_FK")
	private List<UserRole> authorities;

	@PrePersist
	void createAt(){
		createTime = LocalDateTime.now();
	}

	@Override
	public String getUsername() {
		return this.account;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}