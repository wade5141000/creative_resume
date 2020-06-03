package com.sedia.my_course.web;

import com.sedia.my_course.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

	@GetMapping("/add")
	public String addCoursePage(){
		return "course/course_form";
	}

	@PostMapping("/add")
	public @ResponseBody void addCourse(@RequestBody Course course){
		System.out.println("OK");
	}

}