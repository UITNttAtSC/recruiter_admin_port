package com.jobseeker.adminportal.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobseeker.adminportal.domain.Company;
import com.jobseeker.adminportal.domain.EndUser;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.domain.security.PasswordResetToken;
import com.jobseeker.adminportal.domain.security.UserRole;
import com.jobseeker.adminportal.repository.CompanyRepository;
import com.jobseeker.adminportal.repository.EndUserRepository;
import com.jobseeker.adminportal.repository.PasswordResetTokenRepository;
import com.jobseeker.adminportal.repository.RoleRepository;
import com.jobseeker.adminportal.repository.UserRepository;
import com.jobseeker.adminportal.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG=LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EndUserRepository endUserRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);	
	}

	@Override
	public void createPasswordResetTokenForUser(final User user,final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			String role = "";
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
				role = ur.getRole().getName();
			}
			
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
			
			if (role.equals("ROLE_END_USER")) {
			   EndUser endUser = new EndUser();
			   endUser.setUser(localUser);
			   endUserRepository.save(endUser);
			} else if (role.equals("ROLE_COMPANY")) {
		       Company company = new Company();
		       company.setUser(localUser);
		       companyRepository.save(company);
			}
		}
		
		return localUser;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo) {
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public int getAllCount() {
        return (int) userRepository.count();
    }

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}
