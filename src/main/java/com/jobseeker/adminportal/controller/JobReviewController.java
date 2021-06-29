package com.jobseeker.adminportal.controller;

import com.jobseeker.adminportal.common.JobStatus;
import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/review")
public class JobReviewController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public String viewJobPage(Model model) {
        return viewPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String viewPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        Page<Job> page = jobService.findPaginatedForReview(pageNo);
        List<Job> jobs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("jobs", jobs);

        return "review/job";
    }

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable(value = "id") long id,
                          Model model) {
        Job job = jobService.getById(id);
        model.addAttribute("isView", false);
        model.addAttribute("job", job);
        return "review/new";
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("job") Job job, Model model, RedirectAttributes redirectAttributes) {
        int page = jobService.getReviewPageNoById(job);
        job.setJobStatus(JobStatus.ACTIVE);
        job.setApprovedDate(LocalDateTime.now());
        jobService.save(job);

        redirectAttributes.addFlashAttribute("notification",
                String.format("Job\"%s\" successfully approved", job.getJobTitle()));
        redirectAttributes.addFlashAttribute("action", "update");

        return "redirect:/review/page/" + page;
    }

    @GetMapping("/delete/{id}/{page}")
    public String deleteJob(@PathVariable(value = "id") long id,
                            @PathVariable(value = "page") int page,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        Job job = jobService.getById(id);
        this.jobService.deleteById(id);

        redirectAttributes.addFlashAttribute("notification",
                String.format("Job\"%s\" successfully delete", job.getJobTitle()));
        redirectAttributes.addFlashAttribute("action", "delete");

        return "redirect:/review/page/" + page;
    }

}
