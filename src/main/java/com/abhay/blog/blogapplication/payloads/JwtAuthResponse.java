package com.abhay.blog.blogapplication.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
@Getter
@Setter
public class JwtAuthResponse {

    private String token;
    private Integer id;
    
}
