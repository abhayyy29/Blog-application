package com.abhay.blog.blogapplication.payloads;
// import lombok.Data;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;


public class UserDto {

    private int id;

    private String name;

    private String email;

    private String password;

    private String about;

    public UserDto(){
    }
      
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getAbout(){
        return about;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setAbout(String about){
      this.about = about;
    }
}






