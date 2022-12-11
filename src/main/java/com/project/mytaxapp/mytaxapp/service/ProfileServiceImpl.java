package com.project.mytaxapp.mytaxapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;
import com.project.mytaxapp.mytaxapp.repository.ListAccountantRepository;


//This is the service that have the methods used on the controller class.
@Service
public class ProfileServiceImpl implements ProfileService {
	//Here is the injection of the profiles repository.
	@Autowired
	private ListAccountantRepository accountantRepository;
	
	//This method gets and show through the JPA repository all the profiles.
	@Override
	public List<AccountantsProfile> getAllProfiles() {
		return accountantRepository.findAll() ;
	}
	
	//This method through the JPA repository save the profiles on the database.
	@Override
	public void saveProfiles(AccountantsProfile accprofile) {
		this.accountantRepository.save(accprofile);
		
	}
	
	//This method through the JPA repository filter the profiles by the id on the database.
	@Override
	public AccountantsProfile getProfileById(long id) {
		Optional <AccountantsProfile> opt = accountantRepository.findById(id);
		AccountantsProfile accprofile = null;
		if (opt.isPresent()) {
			accprofile = opt.get();
		}else {
			throw new RuntimeException(" Profile not found for id : " + id);
		}
		return accprofile;
	}
	
	//This method through the JPA repository delete by the id the profiles on the database.
	@Override
	public void deleteProfileById(long id) {
		this.accountantRepository.deleteById(id);
		
	}

	

}
