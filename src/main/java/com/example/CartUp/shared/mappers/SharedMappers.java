package com.example.CartUp.shared.mappers;

import com.example.CartUp.shared.dto.PageResponse;
import org.springframework.data.domain.Page;

public class SharedMappers {
    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
