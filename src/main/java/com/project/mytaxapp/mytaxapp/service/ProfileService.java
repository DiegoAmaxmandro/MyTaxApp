package com.project.mytaxapp.mytaxapp.service;

import java.util.List;
import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;

public interface ProfileService {
	List < AccountantsProfile > getAllProfiles();
    void saveProfiles(AccountantsProfile accprofile);
    AccountantsProfile getProfileById(long id);
    void deleteProfileById(long id);
    
}
