package com.project.mytaxapp.mytaxapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.mytaxapp.mytaxapp.models.User;

//This is the user repository that checks on the database if there is already saved a user with the email and mobile that has been inputed.

public interface UserRepository extends JpaRepository<User, Long>{
	
	 	Optional<User> findByEmail(String email);
	    Optional<User> findByMobile(String mobile);
	

}
