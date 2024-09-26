package com.cb.come_back.Config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {

    private String authorName;
    private String email;
    private String password;
    private String phone;
}
