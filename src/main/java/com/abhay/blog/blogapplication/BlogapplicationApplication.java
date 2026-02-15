package com.abhay.blog.blogapplication;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.abhay.blog.blogapplication.config.AppConstants;
import com.abhay.blog.blogapplication.entities.Role;
import com.abhay.blog.blogapplication.repositeries.RoleRepo;

@SpringBootApplication
public class BlogapplicationApplication  implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogapplicationApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		try{
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN");

			Role role1 = new Role();
			role.setId(AppConstants.NORMAL_USER);
			role.setName("USER");

		    List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});
			
		}catch(Exception e){
            e.printStackTrace();
		}
	}

}
