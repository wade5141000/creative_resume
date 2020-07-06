package com.sedia.my_course.web;

import com.sedia.my_course.model.CourseTable;
import com.sedia.my_course.model.user.User;
import com.sedia.my_course.service.CourseService;
import com.sedia.my_course.service.CourseTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/course-table")
public class CourseTableController {

	@Resource
	private CourseTableService courseTableService;
	@Resource
	private CourseService courseService;

	@GetMapping("/add")
	public String addCourseTablePage(Model model){
		return "course_table/course_table_add";
	}

	@PostMapping("/add")
	public @ResponseBody void addCourseTable(@RequestBody CourseTable courseTable, User user){
		courseTableService.save(courseTable, user.getUserId());
	}

	@GetMapping("/{courseTableId}")
	public String courseTable(Model model, User user, @PathVariable int courseTableId){
		model.addAttribute("courseTable", courseTableService.getCourseTableById(courseTableId));
		model.addAttribute("courses", courseService.getAllCourseByUser(user.getUserId()));
		return "course_table/course_table";
	}

	@GetMapping("/abc")
	public @ResponseBody void addCourseToCourseTable(@RequestParam() int courseTableId, @RequestParam() int courseId){
		courseTableService.saveCourseToCourseTable(courseTableId, courseId);
		System.out.println("courseId:"+courseId);
		System.out.println("courseTableId:"+courseTableId);
	}


}
