package com.example.CartUp.brands.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadBrandDtoResponse {
    private Long id;
    private String name;
}
