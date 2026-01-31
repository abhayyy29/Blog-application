package com.abhay.blog.blogapplication.payloads;

import java.util.Date;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
     
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryDto category;

    @ManyToOne
    private UserDto user;
}


