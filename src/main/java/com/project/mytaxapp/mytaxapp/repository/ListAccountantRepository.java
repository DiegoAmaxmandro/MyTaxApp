package com.project.mytaxapp.mytaxapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mytaxapp.mytaxapp.models.AccountantsProfile;


@Repository
public interface ListAccountantRepository extends JpaRepository<AccountantsProfile, Long>{
	
	@Query("select a from AccountantsProfile a join a.user u where u.id = :id")
	List<AccountantsProfile> findProfileById(@Param("id")Long id);
	


}
