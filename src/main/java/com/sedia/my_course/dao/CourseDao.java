package com.sedia.my_course.dao;

import com.sedia.my_course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Integer> {


}
