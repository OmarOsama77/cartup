package com.example.CartUp.category.services;

import com.example.CartUp.category.dto.request.CreateCategoryRequest;
import com.example.CartUp.category.dto.response.CreateCategoryResponse;
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

    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        if (repository.existsByNameIgnoreCase(request.getCatName())) {
            throw new ApplicationException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category category = Category
                .builder()
                .name(request.getCatName())
                .build();

        Category saved = repository.save(category);

        return CreateCategoryResponse.builder().catId(saved.getId()).catName(saved.getName()).build();
    }

    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public boolean isCategoryExists(Long id) {
        return repository.existsById(id);
    }
    public Category findCategoryById(Long id){
        return repository.findById(id).orElseThrow(()-> new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND));
    }
}
