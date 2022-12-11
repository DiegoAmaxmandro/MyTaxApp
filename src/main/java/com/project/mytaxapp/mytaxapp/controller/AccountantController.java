package com.project.mytaxapp.mytaxapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//This controller mapping the accountant's dashboard view.

@Controller
public class AccountantController {
	@GetMapping("/accountant/dashboard")
	public String dashboard() {
		return "accountant/dashboard";
	}

}
