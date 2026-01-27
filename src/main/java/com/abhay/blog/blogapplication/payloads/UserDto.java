package com.abhay.blog.blogapplication.payloads;
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

    // public UserDto(){
    // }
      
    // public int getId(){
    //     return id;
    // }
    // public String getName(){
    //     return name;
    // }

    // public String getEmail(){
    //     return email;
    // }
    // public String getPassword(){
    //     return password;
    // }
    // public String getAbout(){
    //     return about;
    // }

    // public void setId(int id){
    //     this.id=id;
    // }
    // public void setName(String name){
    //     this.name = name;
    // }
    // public void setEmail(String email){
    //     this.email=email;
    // }
    // public void setPassword(String password){
    //     this.password = password;
    // }
    // public void setAbout(String about){
    //   this.about = about;
    // }
}






