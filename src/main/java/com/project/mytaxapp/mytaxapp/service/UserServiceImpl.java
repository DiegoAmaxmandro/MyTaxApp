package com.project.mytaxapp.mytaxapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.mytaxapp.mytaxapp.models.User;
import com.project.mytaxapp.mytaxapp.repository.UserRepository;

//This is the service that have the methods used on the controller class.
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	// Here is the injection of the "BCryptPasswordEncoder".
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	// Here is the injection of the user repository.
	@Autowired
	UserRepository userRepository;

	// this is the method that encrypts the passwords entered by the new user in the
	// database and save it.
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
	}
	//This method checks the existence of the user, the email entered and the mobile.
	@Override
	public List<Object> isUserPresent(User user) {
		boolean userExists = false;
		String message = null;
		Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
		if (existingUserEmail.isPresent()) {
			userExists = true;
			message = "Email Already Present!";
		}
		Optional<User> existingUserMobile = userRepository.findByMobile(user.getMobile());
		if (existingUserMobile.isPresent()) {
			userExists = true;
			message = "Mobile Number Already Present!";
		}
		if (existingUserEmail.isPresent() && existingUserMobile.isPresent()) {
			message = "Email and Mobile Number Both Already Present!";
		}
		System.out.println("existingUserEmail.isPresent() - " + existingUserEmail.isPresent()
				+ "existingUserMobile.isPresent() - " + existingUserMobile.isPresent());
		return Arrays.asList(userExists, message);
	}
	
	//this method set the email as a username.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND", email)));
	}

}
