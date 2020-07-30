package com.sedia.my_course.service.impl;

import com.sedia.my_course.dao.CourseDao;
import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Transactional
@Service("CourseService")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseDao courseDao;

	@Resource
	private UserDao userDao;

	public void save(Course course, int userId){
		User user = userDao.getOne(userId);
		user.getCourses().add(course);
		userDao.save(user);
	}

	@Override
	public List<Course> getAllCourseByUser(int userId) {
		return userDao.getOne(userId).getCourses();
	}

}
