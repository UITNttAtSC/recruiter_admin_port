package com.jobseeker.adminportal.service.impl;

import com.jobseeker.adminportal.common.JobExpireStatus;
import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.repository.JobRepository;
import com.jobseeker.adminportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getAllActive() {
        return jobRepository.getAllActive();
    }

    @Override
    public void save(Job job) {
        if (job != null) {
            jobRepository.save(job);
        }
    }

    @Override
    public Job getById(long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        Job job;

        if (optionalJob.isPresent()) {
            job = optionalJob.get();
        } else {
            throw new RuntimeException("Job is not found");
        }
        return job;
    }

    @Override
    public void deleteById(long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Page<Job> findPaginated(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.jobRepository.findActiveJob(pageable);
    }

    @Override
    public Page<Job> findPaginatedForReview(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.jobRepository.findPendingJob(pageable);
    }

    @Override
    public Page<Job> findPaginatedForExpire(int pageNo) {
        Sort sort = Sort.by("approvedDate").descending();
        int pageSize = 30;
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.jobRepository.findExpiredJob(pageable);
    }

    @Override
    public int getPageNoById(Job job) {
        int count = jobRepository.getCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }

    @Override
    public int getExpiredPageNoById(Job job) {
        int count = jobRepository.getExpiredCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }

    @Override
    public int getReviewPageNoById(Job job) {
        int count = jobRepository.getPendingCountById(job.getApprovedDate());
        int page = (int) Math.ceil(count / 30);
        return page + 1;
    }

    @Override
    public List<Job> getRecentTenActiveJobs() {
        List<Job> jobs = new ArrayList();
        jobs = jobRepository.getRecentTenActiveJobs();
        return jobs;
    }

    @Override
    @Scheduled(cron="*/5 * * * * *")
//    @Scheduled(fixedRate = 900000)
    public void checkToExpireJob() {
        List<Job> jobs = (jobRepository.getExpiredActiveJob(LocalDateTime.now()));
        if (jobs != null && !jobs.isEmpty()) {
            jobs.forEach(job -> {
                job.setExpireStatus(JobExpireStatus.EXPIRED);
                this.save(job);
            });
        }
    }

}
