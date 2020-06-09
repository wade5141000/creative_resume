package com.sedia.my_course.web;

import com.sedia.my_course.model.Course;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Resource
	private CourseService courseService;

	@GetMapping("/add")
	public String addCoursePage(){
		return "course/course_add";
	}

	@PostMapping("/add")
	public @ResponseBody void addCourse(@RequestBody Course course, User user){
		courseService.save(course, user.getUserId());
	}

}
