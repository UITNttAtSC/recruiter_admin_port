package com.jobseeker.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.Payment;
import com.jobseeker.adminportal.repository.PaymentRepository;
import com.jobseeker.adminportal.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment findByCompany(Company company) {
        return paymentRepository.findByCompany(company);
    }
    
}
