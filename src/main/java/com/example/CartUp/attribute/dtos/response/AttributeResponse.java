package com.example.CartUp.attribute.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributeResponse {
    private Long id;
    private String name;
}
