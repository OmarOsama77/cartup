package com.example.CartUp.attributes.dtos.attributevaluedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadAttributeValueRequest {

    private String value;

}
