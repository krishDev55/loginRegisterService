package com.login_RegisterService.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login_RegisterService.entity.UserCreadincial;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserCreadincial, Integer> {

	List<UserCreadincial> findUserCreadincialByEmail(String email);
	
	List<UserCreadincial> findUserCreadincialByEmailAndPassword(String email, String password);

	Collection<UserCreadincial> findUserCreadincialByUsername(String username);

	int deleteByUsername(String username);
	
	@SuppressWarnings("unchecked")
	@Transactional
	UserCreadincial save(UserCreadincial user);

}
