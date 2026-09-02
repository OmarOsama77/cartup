package com.example.CartUp.category.services;

import com.example.CartUp.category.dto.request.CategoryRequest;
import com.example.CartUp.category.dto.response.CategoryResponse;
import com.example.CartUp.category.entities.Category;
import com.example.CartUp.category.repositories.CategoryRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryResponse createCategory(CategoryRequest request) {
        request.setCatName(request.getCatName().toLowerCase().trim());
        if (repository.existsByName(request.getCatName())) {
            throw new ApplicationException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category category = Category
                .builder()
                .name(request.getCatName())
                .build();
        repository.save(category);

        return CategoryResponse.builder().catId(category.getId()).catName(category.getName()).build();
    }

    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        repository.deleteById(id);
    }


    public Category findCategoryById(Long id){
        return repository.findById(id).orElseThrow(()-> new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND));
    }

}
