package com.jobseeker.adminportal.util;

import com.jobseeker.adminportal.common.JobExpireStatus;
import com.jobseeker.adminportal.common.JobStatus;
import com.jobseeker.adminportal.common.JobType;
import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.repository.JobRepository;
import com.jobseeker.adminportal.repository.UserRepository;
import com.jobseeker.adminportal.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobMock {
	@Autowired
	JobRepository jobRepository;

	@Autowired
	UserRepository userRepository;

	// TODO:: to swap with mockito
	/*
	 * @Test
	 * 
	 * @Rollback(false)
	 * 
	 * public void createMockJobToTest() throws Exception {
	 * 
	 * for (int i = 1; i <= 300; i++) { jobRepository.save(createJob(i));
	 * userRepository.save(createUser(i)); } }
	 * 
	 * 
	 * private Job createJob(int count) {
	 * 
	 * Job newJob = new Job(); newJob.setJobTitle("JobTitle " + count);
	 * newJob.setJobType(JobType.HALF_TIME); newJob.setSalary(1000 * count);
	 * newJob.setAgeLimit(count); newJob.setEmployeeLimit(count);
	 * newJob.setFormSubmissionDeadline(LocalDateTime.now() .plusDays(count)
	 * .plusHours(count) .plusMinutes(count)); newJob.setJobHour(count);
	 * newJob.setJobDay(count); newJob.setJobDescription("Description " + count);
	 * newJob.setJobRequirement("Requirement " + count);
	 * newJob.setJobBenefit("Benefit " + count);
	 * 
	 * if(count > 200) { newJob.setJobStatus(JobStatus.PENDING);
	 * newJob.setExpireStatus(JobExpireStatus.NOT_EXPIRED); } else { if(count > 100)
	 * { newJob.setJobStatus(JobStatus.ACTIVE);
	 * newJob.setApprovedDate(LocalDateTime.now() .minusDays(count)
	 * .minusHours(count) .minusMinutes(count));
	 * newJob.setExpireStatus(JobExpireStatus.EXPIRED); } else {
	 * newJob.setJobStatus(JobStatus.ACTIVE);
	 * newJob.setApprovedDate(LocalDateTime.now() .minusDays(count)
	 * .minusHours(count) .minusMinutes(count));
	 * newJob.setExpireStatus(JobExpireStatus.NOT_EXPIRED); } }
	 * 
	 * 
	 * return newJob;
	 * 
	 * }
	 * 
	 * private User createUser(int count) { User user = new User();
	 * user.setName("Name" + count); user.setDescription("Description" + count);
	 * return user; }
	 * 
	 * @Test public void countTest() { System.out.println(jobRepository.count()); }
	 */

	@Test
	public void pageNoTest() {
		Job job = jobRepository.findById(49l).get();
		long count = (jobRepository.getCountById(job.getApprovedDate()));
		System.out.println(count / 30);
		System.out.println(Math.ceil(count / 30));

	}

	@Test
	public void getRecentTenJobsTest() {
		List<Job> jobs = (jobRepository.getRecentTenActiveJobs());
		System.out.println(jobs);
	}

	@Test
	public void getExpiredActiveJobTest() {
		List<Job> jobs = (jobRepository.getExpiredActiveJob(LocalDateTime.now()));
		System.out.println(jobs.size());
		System.out.println(jobs);
	}

}
