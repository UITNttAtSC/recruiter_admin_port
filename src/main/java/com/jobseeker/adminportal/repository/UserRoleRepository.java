package com.jobseeker.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobseeker.adminportal.domain.security.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>{

}
