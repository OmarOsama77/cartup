package com.example.CartUp.brand.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateBrandResponse {
    private Long brandId;
    private String brandName;

}
