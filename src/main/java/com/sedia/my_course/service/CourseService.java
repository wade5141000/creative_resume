package com.sedia.my_course.service;

import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.repository.CourseRepository;
import com.sedia.my_course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class CourseService {

	final CourseRepository courseRepository;
	final UserRepository userRepository;

	public void save(Course course, int userId) {
		// TODO get要改掉
		User user = userRepository.findById(userId).get();
		user.getCourses().add(course);
		userRepository.save(user);
	}

	public List<Course> getAllCourseByUser(int userId) {
		return courseRepository.findAllByUserId(userId);
	}

}
