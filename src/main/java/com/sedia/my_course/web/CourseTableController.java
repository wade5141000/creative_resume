package com.sedia.my_course.web;

import com.sedia.my_course.model.CourseTable;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.CourseTableService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/course-table")
public class CourseTableController {

	@Resource
	private CourseTableService courseTableService;

	@GetMapping("/add")
	public String addCourseTablePage(){
		return "course_table/course_table_add";
	}

	@PostMapping("/add")
	public @ResponseBody void addCourseTable(@RequestBody CourseTable courseTable, User user){
		courseTableService.save(courseTable, user.getUserId());
	}


}
