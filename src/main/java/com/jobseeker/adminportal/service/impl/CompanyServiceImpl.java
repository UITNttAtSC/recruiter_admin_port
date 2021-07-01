package com.jobseeker.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.domain.Payment;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.repository.CompanyRepository;
import com.jobseeker.adminportal.repository.JobPostRepository;
import com.jobseeker.adminportal.repository.UserRepository;
import com.jobseeker.adminportal.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobPostRepository jobPostRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user, Company company) {
        company.setUser(user);
       // user.setCompany(company);
        companyRepository.save(company);
        userRepository.save(user);
    }

    @Override
    public void updateJobPost(JobPost jobPost, Company company, Payment payment) {
        jobPost.setCompany(company);
        jobPost.setPayment(payment);
        company.getPost().add(jobPost);
        jobPostRepository.save(jobPost);
        // save(company);
    }

	@Override
	public Company findByUser(User user) {
		return companyRepository.findByUser(user);
	}
    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    @Override
    public Company findByUser_Id(Long userId) {
        return companyRepository.findByUser_userId(userId);
    }

    
}
