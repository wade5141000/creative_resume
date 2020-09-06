package com.sedia.my_course.web;

import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

	final CourseService courseService;

	@GetMapping("/add")
	public String addCoursePage(Model model, @AuthenticationPrincipal User user) {

		model.addAttribute("course", courseService.getAllCourseByUser(user.getUserId()));
		return "course/course_add";
	}

	@PostMapping("/add")
	public @ResponseBody
	void addCourse(@RequestBody Course course, @AuthenticationPrincipal User user) {
		courseService.save(course, user.getUserId());
	}

}
