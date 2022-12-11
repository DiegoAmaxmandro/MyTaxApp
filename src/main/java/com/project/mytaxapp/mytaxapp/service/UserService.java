package com.project.mytaxapp.mytaxapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.mytaxapp.mytaxapp.models.User;

//This is the interface with the methods to be implemented on "UserServiceImpl".
@Service
public interface UserService {
	
	public void saveUser(User user);
    public List<Object> isUserPresent(User user);

}
