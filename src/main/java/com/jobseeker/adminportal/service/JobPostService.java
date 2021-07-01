package com.jobseeker.adminportal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jobseeker.adminportal.domain.JobPost;

public interface JobPostService {
	
    List<JobPost> findAll();

    List<JobPost> findFirst5ByStatusOrderByUpdatedAt(boolean status);

    Page<JobPost> findAllByStatusOrderByUpdatedAt(Integer pageNo, Integer pageSize, boolean status);

    JobPost save(JobPost post);

    JobPost findById(Long id);

    void removeById(Long id);
    
    List<JobPost> findByJobCategory(String JobPostCategory);
    
   // List<JobPost> getAll();

    List<JobPost> getAllActive();

    //void save(JobPost JobPost);

   // JobPost getById(long id);

    //void deleteById(long id);

    Page<JobPost> findPaginated(int pageNo);

    Page<JobPost> findPaginatedForReview(int pageNo);

    Page<JobPost> findPaginatedForExpire(int pageNo);

    int getPageNoById(JobPost JobPost);

    int getExpiredPageNoById(JobPost JobPost);

    int getReviewPageNoById(JobPost JobPost);

    List<JobPost> getRecentTenActiveJobPosts();

//    void checkToExpireJobPost();
}
