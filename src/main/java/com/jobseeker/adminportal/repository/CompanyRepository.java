package com.jobseeker.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.User;


public interface CompanyRepository extends CrudRepository<Company, Long> {
    

    Company findByUser(User user);

    Company findByCompanyName(String companyName);
  
     Company findByUser_userId(Long userId);
}
