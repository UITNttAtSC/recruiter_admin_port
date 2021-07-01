package com.jobseeker.adminportal.service;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.Payment;

public interface PaymentService {

    Payment findByCompany(Company company);
}
