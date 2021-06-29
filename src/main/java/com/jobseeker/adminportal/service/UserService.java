package com.jobseeker.adminportal.service;

import com.jobseeker.adminportal.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(long id);

    void deleteById(long id);

    Page<User> findPaginated(int pageNo);

    int getAllCount();
}
