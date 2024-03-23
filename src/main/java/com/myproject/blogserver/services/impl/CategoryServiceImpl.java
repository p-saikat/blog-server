package com.myproject.blogserver.services.impl;

import com.myproject.blogserver.entities.Category;
import com.myproject.blogserver.exceptions.ResourceNotFoundException;
import com.myproject.blogserver.payloads.CategoryDTO;
import com.myproject.blogserver.repositories.CategoryRepository;
import com.myproject.blogserver.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = dtoToCategory(categoryDTO);
        Category saveCategory = categoryRepository.save(category);
        return categoryToDto(saveCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updateCategory = categoryRepository.save(category);
        return categoryToDto(updateCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoriesDto = categories.stream()
                .map(category -> categoryToDto(category)).collect(Collectors.toList());

        return categoriesDto;
    }

    private Category dtoToCategory(CategoryDTO categoryDto) {
        Category category = this.mapper.map(categoryDto, Category.class);
        return category;
    }

    private CategoryDTO categoryToDto(Category category) {
        CategoryDTO categoryDTO = this.mapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }
}
