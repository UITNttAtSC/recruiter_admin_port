package com.jobseeker.adminportal.service;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.domain.Payment;
import com.jobseeker.adminportal.domain.User;

public interface CompanyService {

    void save(User user, Company company);

    Company findByUser(User user);

    Company findByCompanyName(String companyName);

    Company findByUser_Id(Long userId);
    
    void updateJobPost(JobPost jobPost, Company company, Payment payment);
}
