package com.jobseeker.adminportal.service.impl;

import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.repository.UserRepository;
import com.jobseeker.adminportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        Optional<User> optionalJob = userRepository.findById(id);
        User user;

        if (optionalJob.isPresent()) {
            user = optionalJob.get();
        } else {
            throw new RuntimeException("User is not found");
        }
        return user;
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
}
