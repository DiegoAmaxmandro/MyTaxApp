package com.project.mytaxapp.mytaxapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;
import com.project.mytaxapp.mytaxapp.repository.ListAccountantRepository;

//This controller maps the view where the user can see the accountant's profiles.

@Controller
@RequestMapping("user")
public class ListAccountController {
	//This is a implementation of the repository.
	@Autowired
	private ListAccountantRepository listrepository;
	
	//This method maps the view "listaccount" where the accountant's profile it's shown.
	//The method works getting a list of the model "AccountantsProfile" through the JPA repository that give us the "finAll" method.
	//So on the "listaccount" the user can view all the profiles that is save on the database.
	
	@GetMapping("listaccount")
	public String findAccountant(Model model) {
		List<AccountantsProfile> accountants = listrepository.findAll();
		model.addAttribute("accountants", accountants);
		
		return "user/listaccount";
	}
	
	//This method maps the view of the search by specialty.
		@RequestMapping("specialty")
		public String search(@RequestParam String specialty, Model model) {
			model.addAttribute("accountants", listrepository.findBySpecialtyContaining(specialty));
			return "user/listaccount";
		}

}
