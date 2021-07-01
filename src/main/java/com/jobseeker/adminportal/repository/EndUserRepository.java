package com.jobseeker.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobseeker.adminportal.domain.EndUser;
import com.jobseeker.adminportal.domain.User;

public interface EndUserRepository extends CrudRepository<EndUser, Long> {
    
    EndUser findByUser(User user);
}
