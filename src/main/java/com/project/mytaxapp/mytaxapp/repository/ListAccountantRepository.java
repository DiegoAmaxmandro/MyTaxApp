package com.project.mytaxapp.mytaxapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;

//This is the profile repository that is been used on the service to communicate with the database though the JPA repository methods, as "finAall" and "findById".

@Repository
public interface ListAccountantRepository extends JpaRepository<AccountantsProfile, Long>{
	
	//This method is supposed to filter on the database the profile with the same id of the user logged.
//	@Query("select a from AccountantsProfile a join a.user u where u.id = :id")
//	Optional<AccountantsProfile> findById(@Param("id")Long id);
	
	//This method is to filter on the database the profile with the same specialty or part of it.
	public List<AccountantsProfile> findBySpecialtyContaining(String specialty);
	

}
