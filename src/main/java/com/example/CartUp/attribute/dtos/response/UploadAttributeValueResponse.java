package com.example.CartUp.attribute.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UploadAttributeValueResponse {
    private Long id;
    private String value;
    private String attributeName;
}
