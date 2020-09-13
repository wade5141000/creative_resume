package com.sedia.my_course.repository;

import com.sedia.my_course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query(value = "select * from Course c where c.user_id_fk = :userId", nativeQuery = true)
	List<Course> findAllByUserId(@Param("userId") Integer userId);

}
