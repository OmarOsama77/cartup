package com.example.CartUp.attribute.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadAttributeResponse {
    private Long id;
    private String name;

}
