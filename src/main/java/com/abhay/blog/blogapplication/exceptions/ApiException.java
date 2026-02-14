package com.abhay.blog.blogapplication.exceptions;

public class ApiException extends RuntimeException {



    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
        super();
    }

    
}
