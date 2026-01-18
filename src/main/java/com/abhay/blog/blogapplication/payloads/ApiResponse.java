package com.abhay.blog.blogapplication.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {

   private String message;
   private boolean success;

}
