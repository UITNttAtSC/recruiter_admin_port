package com.jobseeker.adminportal.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jobseeker.adminportal.common.JobExpireStatus;
import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.repository.JobPostRepository;
import com.jobseeker.adminportal.service.JobPostService;

@Service
public class JobPostServiceImpl implements JobPostService{

    @Autowired
    private JobPostRepository postRepository;

    @Override
    public List<JobPost> findAll() {
        return (List<JobPost>) postRepository.findAll();
    }

    @Override
    public JobPost save(JobPost post) {
        return postRepository.save(post);
    }

    @Override
    public JobPost findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<JobPost> findFirst5ByStatusOrderByUpdatedAt(boolean status) {
        return postRepository.findFirst5ByStatusOrderByUpdatedAt(status);
    }

    @Override
    public Page<JobPost> findAllByStatusOrderByUpdatedAt(Integer pageNo, Integer pageSize, boolean status) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return postRepository.findAllByStatusOrderByUpdatedAt(status, paging);
    }

    @Override
	public List<JobPost> findByJobCategory(String jobCategory) {
		return postRepository.findByJobCategory(jobCategory);
	}

    @Override
    public Page<JobPost> findPaginated(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.findActiveJob(pageable);
    }

    @Override
    public Page<JobPost> findPaginatedForReview(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.findPendingJob(pageable);
    }

    @Override
    public Page<JobPost> findPaginatedForExpire(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.findExpiredJob(pageable);
    }

    @Override
    public int getPageNoById(JobPost job) {
        int count = postRepository.getCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }

    @Override
    public int getExpiredPageNoById(JobPost job) {
        int count = postRepository.getExpiredCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }

    @Override
    public int getReviewPageNoById(JobPost job) {
        int count = postRepository.getPendingCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }


	@Override
	public List<JobPost> getAllActive() {
		return postRepository.getAllActive();
	}

	@Override
	public List<JobPost> getRecentTenActiveJobPosts() {
        List<JobPost> jobs = new ArrayList<>();
        jobs = postRepository.getRecentTenActiveJobs();
        return jobs;
	}

}
