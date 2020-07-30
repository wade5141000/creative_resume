package com.sedia.my_course.service;

import com.sedia.my_course.entity.Course;

import java.util.List;

public interface CourseService {

	void save(Course course, int userId);

	List<Course> getAllCourseByUser(int userId);

}
