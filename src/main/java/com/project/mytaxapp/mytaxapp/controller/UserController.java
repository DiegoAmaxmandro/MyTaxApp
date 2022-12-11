package com.project.mytaxapp.mytaxapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//This controller mapping the user's dashboard view.

@Controller
public class UserController {
	
	@RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String homePage(){
        return "user/dashboard";
    }

}
