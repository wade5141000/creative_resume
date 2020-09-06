package com.sedia.my_course.web;

import com.sedia.my_course.entity.CourseTable;
import com.sedia.my_course.entity.user.User;
import com.sedia.my_course.service.CourseService;
import com.sedia.my_course.service.CourseTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course-table")
@RequiredArgsConstructor
public class CourseTableController {

  final CourseTableService courseTableService;
  final CourseService courseService;

  @GetMapping("/add")
  public String addCourseTablePage(Model model) {
    return "course_table/course_table_add";
  }

  @PostMapping("/add")
  public @ResponseBody void addCourseTable(@RequestBody CourseTable courseTable, @AuthenticationPrincipal User user) {
    courseTableService.save(courseTable, user.getUserId());
  }

  @GetMapping("/{courseTableId}")
  public String courseTable(Model model, @AuthenticationPrincipal User user, @PathVariable int courseTableId) {
    model.addAttribute("courseTable", courseTableService.getCourseTableById(courseTableId));
    model.addAttribute("courses", courseService.getAllCourseByUser(user.getUserId()));
    return "course_table/course_table";
  }

  @GetMapping("/append")
  public @ResponseBody void addCourseToCourseTable(@RequestParam int courseTableId, @RequestParam int courseId) {
    courseTableService.saveCourseToCourseTable(courseTableId, courseId);
  }


}
