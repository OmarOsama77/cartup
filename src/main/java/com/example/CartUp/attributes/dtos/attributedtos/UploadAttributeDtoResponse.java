package com.example.CartUp.attributes.dtos.attributedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UploadAttributeDtoResponse {
    private Long id;
    private String name;

}
