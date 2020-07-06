package com.sedia.my_course.service;

import com.sedia.my_course.model.CourseTable;

public interface CourseTableService {

	void save(CourseTable courseTable, int userId);

	void saveCourseToCourseTable(int courseTableId, int courseId);

	CourseTable getCourseTableById(int courseTableId);

}
