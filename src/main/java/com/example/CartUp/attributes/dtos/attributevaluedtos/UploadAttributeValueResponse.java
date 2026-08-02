package com.example.CartUp.attributes.dtos.attributevaluedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadAttributeValueResponse {
    private Long attributeValueId;
    private String attributeValue;
    private Long attributeId;

}
