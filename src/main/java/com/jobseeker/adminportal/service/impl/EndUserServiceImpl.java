package com.jobseeker.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobseeker.adminportal.domain.EndUser;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.repository.EndUserRepository;
import com.jobseeker.adminportal.service.EndUserService;

@Service
public class EndUserServiceImpl implements EndUserService{
	
	@Autowired
	private EndUserRepository endUserRepository;

	@Override
	public EndUser save(EndUser endUser) {
		return endUserRepository.save(endUser);
	}

	@Override
	public EndUser findByUser(User user) {
		return endUserRepository.findByUser(user);
	}

}
