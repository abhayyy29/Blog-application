package com.abhay.blog.blogapplication.payloads;
import java.util.HashSet;
import java.util.Set;

import com.abhay.blog.blogapplication.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    private int id;

    @NotEmpty
    private String name;

    @Email(message = "Email adress is not valid!!!")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10,message = "Password must be minimum of 2 Char and maximum of 10 characters!!")
    private String password;

    @NotEmpty
    private String about;

    private Set<CommentDto> comments = new HashSet<>();

    private Set<Role> roles = new HashSet<>();

}






