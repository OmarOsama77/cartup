package com.example.CartUp.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserResponse {
    private UUID id;
    private String firstName;
    private String secondName;
    private String email;
}
