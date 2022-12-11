package com.project.mytaxapp.mytaxapp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;
import com.project.mytaxapp.mytaxapp.repository.ListAccountantRepository;
import com.project.mytaxapp.mytaxapp.service.ProfileService;

//This controller maps the views that allows the accountant to insert, edit and delete a profile.

@Controller
@RequestMapping("accountant")
public class ProfileController {
	// This is a implementation of the profile service where is the methods.
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ListAccountantRepository listAccountantRepository;
	

	// This method maps the view where the accountant can see the profiles and can
	// edit or delete them.
	// The list of profiles is brought through the method "getAllProfile()" that was
	// created inside the "profileService"
	@GetMapping("myProfile")
	public String myProfile( Model model) {
		model.addAttribute("accountants", profileService.getAllProfiles());
		return "accountant/myProfile";
	}

	// This method maps the view where the accountant can insert new profiles using
	// the model "addAttribute" to do this.
	@GetMapping("profileForm")
	public String addProfile(Model model) {
		AccountantsProfile accProfile = new AccountantsProfile();
		model.addAttribute("accProfile", accProfile);
		return "accountant/profileForm";
	}
	

	// This is the post method to save the profile through the method "saveProfile"
	// created inside the "profileService".
	@PostMapping("saveProfile")
	public String saveProfile( @ModelAttribute("accountantsProfile")  @Valid AccountantsProfile accProfile, Model model,
			BindingResult result) {
		
		model.addAttribute("accProfile" , accProfile);
		
		if(result.hasErrors()) {
			return "accountant/profileForm";
		}
		
		profileService.saveProfiles(accProfile);
		return "redirect:/accountant/myProfile";
	}
	

	// This method maps the view where the accountant can edit profiles by their id.
	@GetMapping("updateProfile/{id}")
	public String updateProfile(@PathVariable(value = "id") long id, Model model) {
		AccountantsProfile accProfile = profileService.getProfileById(id);
		model.addAttribute("accProfile", accProfile);
		return "accountant/updateProfile";
	}

	// This method is to save the profile after edit them, the only between this and
	// the "saveProfile" is the url address that will return in case of an error on
	// the form.
	@PostMapping("saveUpdateProfile")
	public String saveUpdateProfile(@Valid @ModelAttribute("accountantsProfile") AccountantsProfile accProfile,
			BindingResult result) {
		if (result.hasErrors()) {
			return "accountant/updatePrifile";
		}
		profileService.saveProfiles(accProfile);
		return "redirect:/accountant/myProfile";
	}

	// This method maps the view where the accountant can delete profiles by their id.
	@GetMapping("deleteProfile/{id}")
	public String deleteProfile(@PathVariable(value = "id") Long id) {
		this.profileService.deleteProfileById(id);
		return "redirect:/accountant/myProfile";
	}
	
	
	//This method maps the view of the search by specialty.
	@RequestMapping("specialty")
	public String search(@RequestParam String specialty, Model model) {
		model.addAttribute("accountants", listAccountantRepository.findBySpecialtyContaining(specialty));
		return "accountant/myProfile";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "accountant/profileForm";
	}
	

}
