package com.jobseeker.adminportal.service;

import com.jobseeker.adminportal.domain.EndUser;
import com.jobseeker.adminportal.domain.User;

public interface EndUserService {

    EndUser save(EndUser endUser);
    
    EndUser findByUser(User user);
    
}
