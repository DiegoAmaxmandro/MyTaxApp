package com.project.mytaxapp.mytaxapp.service;

import java.util.List;
import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;

//This is the interface with the methods to be implemented on "ProfileServiceImpl".

public interface ProfileService {
	List < AccountantsProfile > getAllProfiles();
    void saveProfiles(AccountantsProfile accprofile);
    AccountantsProfile getProfileById(long id);
    void deleteProfileById(long id);
    
}
