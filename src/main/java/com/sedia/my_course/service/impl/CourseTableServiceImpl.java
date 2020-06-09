package com.sedia.my_course.service.impl;


import com.sedia.my_course.dao.CourseTableDao;
import com.sedia.my_course.dao.UserDao;
import com.sedia.my_course.model.CourseTable;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.CourseTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("CourseTableService")
public class CourseTableServiceImpl implements CourseTableService {

	@Resource
	private CourseTableDao courseTableDao;

	@Resource
	private UserDao userDao;

	@Override
	public void save(CourseTable courseTable, int userId) {
		User user = userDao.getOne(userId);
		user.getCourseTables().add(courseTable);
		userDao.save(user);
	}
}
