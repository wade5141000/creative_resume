package com.temp.creative_resume.model.user;

import com.temp.creative_resume.model.CourseTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

/**
    使用者
 */
@Entity
@Getter
@Setter
@Table(name = "all_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

	@Column(unique = true, name="account")
	private String username;

    private String password;

    private String userRealName;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID_FK")
    private List<CourseTable> courseTable;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID_FK")
	private List<UserRole> authorities;

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