package com.project.mytaxapp.mytaxapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountantController {
	@GetMapping("/accountant/dashboard")
	public String dashboard() {
		return "accountant/dashboard";
	}

}
