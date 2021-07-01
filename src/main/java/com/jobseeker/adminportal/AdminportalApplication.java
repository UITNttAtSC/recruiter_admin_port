package com.jobseeker.adminportal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.jobseeker.adminportal.common.JobExpireStatus;
import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.repository.JobPostRepository;
import com.jobseeker.adminportal.service.EndUserService;
import com.jobseeker.adminportal.service.JobPostService;
import com.jobseeker.adminportal.service.UserService;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class AdminportalApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EndUserService endUserService;
	
	@Autowired
	private JobPostService jobPostService;

    @Autowired
    private JobPostRepository postRepository;
    
	public static void main(String[] args) {
        SpringApplication.run(AdminportalApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		checkToExpireJobPost();
	}
	
    //@Scheduled(cron="*/5 * * * * *")
    @Scheduled(fixedRate = 900000)
	public void checkToExpireJobPost() {
    	Date today = new Date();
		List<JobPost> jobs = (postRepository.getExpiredActiveJob(today));
//        if (jobs != null && !jobs.isEmpty()) {
//            jobs.forEach(job -> {
//                job.setExpireStatus(JobExpireStatus.EXPIRED);
//                System.out.println(job.getExpireStatus());
//                postRepository.save(job);
//            });
//        }
		for(JobPost job : jobs) {
			job.setExpireStatus(JobExpireStatus.EXPIRED);
			postRepository.save(job);
		}
	}
    
    
//    @Override
//	public void run(String... args) throws Exception {
//    	
//    	
//    	List<JobPost> jobPostList = jobPostService.findAll();
//    	
//    	for(JobPost jobPost:jobPostList) {
//    		if(jobPost.getExpireStatus() == null) {
//    			jobPost.setExpireStatus((JobExpireStatus.NOT_EXPIRED));
//    			jobPostService.save(jobPost);
//    		}
//    	}
    
    	
		
		/*
		 * 
		 * 
		 * User user=new User();
		 * 
		 * user.setUsername("admin");
		 * user.setPassword(SecurityUtility.passwordEncoder().encode("1234"));
		 * user.setEmail("kyawkhaing29581@gmail.com");
		 * 
		 * 
		 * Set<UserRole> userRoles=new HashSet<>(); Role role=new Role();
		 * role.setRoleId(3l); role.setName("ROLE_ADMIN");
		 * 
		 * userRoles.add(new UserRole(user, role));
		 * 
		 * userService.createUser(user, userRoles);
		 * 
		 * EndUser endUser = new EndUser(); endUser.setUser(user);
		 * 
		 * endUserService.save(endUser);
		 */
		 
		 
	}


