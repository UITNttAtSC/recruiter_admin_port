package com.jobseeker.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobseeker.adminportal.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

	Role findByName(String name);
	

}
