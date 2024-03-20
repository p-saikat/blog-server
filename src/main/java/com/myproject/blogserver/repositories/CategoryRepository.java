package com.myproject.blogserver.repositories;

import com.myproject.blogserver.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
