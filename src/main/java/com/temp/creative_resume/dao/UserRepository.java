package com.temp.creative_resume.dao;

import com.temp.creative_resume.modal.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}