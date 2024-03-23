package com.myproject.blogserver.services;

import com.myproject.blogserver.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    public void deleteCategory(Integer categoryId);

    CategoryDTO getCategoryById(Integer categoryId);

    List<CategoryDTO> getAllCategory();
}
