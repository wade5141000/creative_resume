package com.sedia.my_course.service;

import com.sedia.my_course.repository.CourseRepository;
import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class CourseService {

	final CourseRepository courseRepository;
	final UserRepository userRepository;

	public void save(Course course, int userId) {
		User user = userRepository.getOne(userId);
		user.getCourses().add(course);
		userRepository.save(user);
	}

	public List<Course> getAllCourseByUser(int userId) {
		return courseRepository.findAllByUserId(userId);
	}

}
