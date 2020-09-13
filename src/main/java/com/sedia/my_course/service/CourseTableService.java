package com.sedia.my_course.service;


import com.sedia.my_course.entity.Course;
import com.sedia.my_course.entity.CourseTable;
import com.sedia.my_course.repository.CourseRepository;
import com.sedia.my_course.repository.CourseTableRepository;
import com.sedia.my_course.repository.UserRepository;
import com.sedia.my_course.utils.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CourseTableService {

  final UserRepository userRepository;
  final CourseTableRepository courseTableRepository;
  final CourseRepository courseRepository;

  public void save(CourseTable courseTable, int userId) {
    //TODO 測試 + null訊息
    userRepository.findById(userId)
        .map(userEntity -> {
          userEntity.getCourseTables().add(courseTable);
          return userEntity;
        })
        .map(userRepository::save)
        .orElseThrow(ExceptionUtil.nullPointerException());
  }

  public void saveCourseToCourseTable(int courseTableId, int courseId) {
    // TODO 已經在課表裡或課表時間有衝突的判斷
    // TODO 所有細節要修正(copyProperties)
    Course newCourse = new Course();
    BeanUtils.copyProperties(courseRepository.findById(courseId), newCourse, "courseId");
    courseTableRepository.findById(courseTableId).get().getCourses().add(newCourse);
  }

  public CourseTable getCourseTableById(int courseTableId) {
    return courseTableRepository.findById(courseTableId)
        .orElseThrow(ExceptionUtil.nullPointerException("找不到course id為[" + courseTableId + "]的資料"));
  }
}
