package com.ecommerceproject.ProductName.Payload;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOremail;
    private String password;
}
