package com.jobseeker.adminportal.service;

import com.jobseeker.adminportal.domain.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    List<Job> getAll();

    List<Job> getAllActive();

    void save(Job job);

    Job getById(long id);

    void deleteById(long id);

    Page<Job> findPaginated(int pageNo);

    Page<Job> findPaginatedForReview(int pageNo);

    Page<Job> findPaginatedForExpire(int pageNo);

    int getPageNoById(Job job);

    int getExpiredPageNoById(Job job);

    int getReviewPageNoById(Job job);

    List<Job> getRecentTenActiveJobs();

    void checkToExpireJob();
}
