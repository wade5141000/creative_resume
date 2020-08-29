package com.sedia.my_course.service;


import com.sedia.my_course.repository.CourseRepository;
import com.sedia.my_course.repository.CourseTableRepository;
import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.CourseTable;
import com.sedia.my_course.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseTableService {

	final UserRepository userRepository;
	final CourseTableRepository courseTableRepository;
	final CourseRepository courseRepository;

	public void save(CourseTable courseTable, int userId) {
		User user = userRepository.getOne(userId);
		user.getCourseTables().add(courseTable);
		userRepository.save(user);
	}

	public void saveCourseToCourseTable(int courseTableId, int courseId) {
		// TODO 已經在課表裡或課表時間有衝突的判斷
		Course newCourse = new Course();
		BeanUtils.copyProperties(courseRepository.getOne(courseId), newCourse, "courseId");
		courseTableRepository.getOne(courseTableId).getCourses().add(newCourse);
	}

	public CourseTable getCourseTableById(int courseTableId) {
		return courseTableRepository.getOne(courseTableId);
	}
}
