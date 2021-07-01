package com.jobseeker.adminportal.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jobseeker.adminportal.domain.JobPost;

@Repository
public interface JobPostRepository extends PagingAndSortingRepository<JobPost, Long>,JpaRepository<JobPost, Long> {
  
    List<JobPost> findFirst5ByStatusOrderByUpdatedAt(boolean status);

    List<JobPost> findAllByStatusOrderByUpdatedAtDesc(boolean status);
    
    List<JobPost> findByJobCategory(String jobCategory);

    Page<JobPost> findAllByStatusOrderByUpdatedAt(boolean status, Pageable paging);
    
    @Query("select count(j) from JobPost j where j.approvedDate > ?1 and j.status=true and j.expireStatus=0 order by j.approvedDate desc")
    int getCountById(LocalDateTime localDateTime);

    @Query("select count(j) from JobPost j where j.approvedDate > ?1 and j.expireStatus=1 order by j.approvedDate desc")
    int getExpiredCountById(LocalDateTime localDateTime);

    @Query("select count(j) from JobPost j where j.approvedDate > ?1 and j.status=false and j.expireStatus=0 order by j.updatedAt desc")
    int getPendingCountById(LocalDateTime localDateTime);

    @Query(nativeQuery = true, value = "select * from JobPost j where j.status=true and j.expire_status=0 order by j.approved_date desc limit 10")
    List<JobPost> getRecentTenActiveJobs();

    @Query(value = "select j from JobPost j where j.status=true and j.expireStatus=0 order by j.approvedDate desc")
    List<JobPost> getAllActive();

//    @Query(value = "select j from Job j where j.jobStatus='ACTIVE' or j.jobStatus='EXPIRED' order by j.approvedDate desc")
    @Query(value = "select j from JobPost j where j.status=true and j.expireStatus=0 order by j.approvedDate desc")
    Page<JobPost> findActiveJob(Pageable var1);

//    @Query(value = "select j from Job j where j.jobStatus='PENDING' order by j.updateDate desc")
    @Query(value = "select j from JobPost j where j.status=false and j.expireStatus=0 order by j.updatedAt desc")
    Page<JobPost> findPendingJob(Pageable var1);

    @Query(value = "select j from JobPost j where  j.expireStatus=1 order by j.approvedDate desc")
    Page<JobPost> findExpiredJob(Pageable var1);

//    @Query(value = "select j from Job j where j.deadline = ?1 and j.jobStatus='ACTIVE'")
    @Query(value = "select j from JobPost j where j.deadline = ?1 and j.status=true and j.expireStatus=0")
    List<JobPost> getExpiredActiveJob(Date today);

}
