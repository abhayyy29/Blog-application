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
			if(!roleRepo.existsByName("ADMIN")){
			Role admin = new Role();
			// admin.setId(AppConstants.ADMIN_USER);
			admin.setName("ADMIN");
			roleRepo.save(admin);
			}
			if(!roleRepo.existsByName("USER")){
			Role user = new Role();
			// user.setId(AppConstants.NORMAL_USER);
			user.setName("USER");
			roleRepo.save(user);
			}

		    // List<Role> roles = List.of(admin, user);
			// List<Role> result = this.roleRepo.saveAll(roles);

			roleRepo.findAll()
			.forEach(r->{
				System.out.println(r.getName());
			});
			
		}catch(Exception e){
            e.printStackTrace();
		}
	}

}
