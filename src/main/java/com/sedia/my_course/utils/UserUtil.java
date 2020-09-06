package com.sedia.my_course.utils;

import com.sedia.my_course.entity.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserUtil {

  public static Optional<User> getCurrentUser() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(User.class::isInstance)
        .map(User.class::cast);
  }
}
