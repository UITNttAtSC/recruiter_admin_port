package com.jobseeker.adminportal.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.jobseeker.adminportal.domain.User;

public interface UserRepository extends PagingAndSortingRepository<User,Long>{

	User findByUsername(String username);
	
	User findByEmail(String email);
	
}
