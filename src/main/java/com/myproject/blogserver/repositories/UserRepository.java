package com.myproject.blogserver.repositories;

import com.myproject.blogserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
