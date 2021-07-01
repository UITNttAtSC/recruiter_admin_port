package com.jobseeker.adminportal.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.service.JobPostService;
import com.jobseeker.adminportal.service.UserService;

@Controller
@RequestMapping("/")
public class DashboardController {
    @Autowired
    JobPostService jobService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewJobPage(Model model) {
        int jobCount = jobService.getAllActive().size();
        int jobPendingCount = jobService.findAll().size() - jobCount;
        List<JobPost> jobs = jobService.getRecentTenActiveJobPosts();

        int userCount = userService.getAllCount();

        model.addAttribute("jobCount", jobCount);
        model.addAttribute("jobs", jobs);
        model.addAttribute("jobPendingCount", jobPendingCount);

        model.addAttribute("userCount", userCount);
        return "index";
    }
}
