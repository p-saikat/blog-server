package com.myproject.blogserver.controllers;

import com.myproject.blogserver.payloads.ApiResponse;
import com.myproject.blogserver.payloads.CategoryDTO;
import com.myproject.blogserver.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDto, @PathVariable Integer categoryId) {
        return categoryService.updateCategory(categoryDto, categoryId);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResponse getAllCategory() {
        List<CategoryDTO> categories = categoryService.getAllCategory();
        return new ApiResponse("Records fetched successfully.", true, categories);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, String> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Record deleted successfully.");
        return response;
    }
}
