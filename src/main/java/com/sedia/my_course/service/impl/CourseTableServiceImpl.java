package com.sedia.my_course.service.impl;


import com.sedia.my_course.dao.CourseDao;
import com.sedia.my_course.dao.CourseTableDao;
import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.Course;
import com.sedia.my_course.model.CourseTable;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.CourseTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("CourseTableService")
public class CourseTableServiceImpl implements CourseTableService {

	@Resource
	private UserDao userDao;
	@Resource
	private CourseTableDao courseTableDao;
	@Resource
	private CourseDao courseDao;

	@Override
	public void save(CourseTable courseTable, int userId) {
		User user = userDao.getOne(userId);
		user.getCourseTables().add(courseTable);
		userDao.save(user);
	}

	@Override
	public void saveCourseToCourseTable(int courseTableId, int courseId) {
		// TODO 已經在課表裡或課表時間有衝突的判斷
		Course newCourse = new Course();
		BeanUtils.copyProperties(courseDao.getOne(courseId), newCourse, "courseId");
		courseTableDao.getOne(courseTableId).getCourses().add(newCourse);
	}

	@Override
	public CourseTable getCourseTableById(int courseTableId) {
		return courseTableDao.getOne(courseTableId);
	}
}
