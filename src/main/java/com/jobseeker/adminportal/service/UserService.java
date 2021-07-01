package com.jobseeker.adminportal.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.domain.security.PasswordResetToken;
import com.jobseeker.adminportal.domain.security.UserRole;

public interface UserService {
	
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user,final String token);
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	User findById(Long id);
	
	User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
    List<User> getAll();

    void deleteById(long id);

    Page<User> findPaginated(int pageNo);

    int getAllCount();

}
