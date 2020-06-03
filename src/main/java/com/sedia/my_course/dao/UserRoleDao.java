package com.sedia.my_course.dao;

import com.sedia.my_course.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, Integer> {


}
