package com.jobseeker.adminportal.repository;

import com.jobseeker.adminportal.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select count(j) from Job j where j.approvedDate > ?1 and j.jobStatus=1 and j.expireStatus=0 order by j.approvedDate desc")
    int getCountById(LocalDateTime localDateTime);

    @Query("select count(j) from Job j where j.approvedDate > ?1 and j.expireStatus=1 order by j.approvedDate desc")
    int getExpiredCountById(LocalDateTime localDateTime);

    @Query("select count(j) from Job j where j.approvedDate > ?1 and j.jobStatus=0 and j.expireStatus=0 order by j.updateDate desc")
    int getPendingCountById(LocalDateTime localDateTime);

    @Query(nativeQuery = true, value = "select * from job_post j where j.job_status=1 and j.expire_status=0 order by j.approved_date desc limit 10")
    List<Job> getRecentTenActiveJobs();

    @Query(value = "select j from Job j where j.jobStatus=1 and j.expireStatus=0 order by j.approvedDate desc")
    List<Job> getAllActive();

//    @Query(value = "select j from Job j where j.jobStatus='ACTIVE' or j.jobStatus='EXPIRED' order by j.approvedDate desc")
    @Query(value = "select j from Job j where j.jobStatus=1 and j.expireStatus=0 order by j.approvedDate desc")
    Page<Job> findActiveJob(Pageable var1);

//    @Query(value = "select j from Job j where j.jobStatus='PENDING' order by j.updateDate desc")
    @Query(value = "select j from Job j where j.jobStatus=0 and j.expireStatus=0 order by j.updateDate desc")
    Page<Job> findPendingJob(Pageable var1);

    @Query(value = "select j from Job j where  j.expireStatus=1 order by j.approvedDate desc")
    Page<Job> findExpiredJob(Pageable var1);

//    @Query(value = "select j from Job j where j.formSubmissionDeadline < ?1 and j.jobStatus='ACTIVE'")
    @Query(value = "select j from Job j where j.formSubmissionDeadline < ?1 and j.jobStatus=1 and j.expireStatus=1")
    List<Job> getExpiredActiveJob(LocalDateTime localDateTime);
}
