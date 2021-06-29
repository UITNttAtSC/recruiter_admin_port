package com.jobseeker.adminportal.controller;

import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.service.JobService;
import com.jobseeker.adminportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class DashboardController {
    @Autowired
    JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewJobPage(Model model) {
        int jobCount = jobService.getAllActive().size();
        List<Job> jobs = jobService.getRecentTenActiveJobs();

        int userCount = userService.getAllCount();

        model.addAttribute("jobCount", jobCount);
        model.addAttribute("jobs", jobs);

        model.addAttribute("userCount", userCount);
        return "index";
    }
}
