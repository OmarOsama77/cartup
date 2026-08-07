package com.example.CartUp.brand.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateBrandRequest {
    @NotBlank(message = "Brand name is required")
    @Size(min = 2, max = 100)
    private String brandName;

}
