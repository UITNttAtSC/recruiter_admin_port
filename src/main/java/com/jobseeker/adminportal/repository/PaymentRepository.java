package com.jobseeker.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    
    Payment findByCompany(Company company);
}
