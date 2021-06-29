package com.jobseeker.adminportal.controller;

import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/expire")
public class JobExpireController {
    @Autowired
    private JobService jobService;

    @GetMapping
    public String viewJobPage(Model model) {
        return viewPaginated(1, model);
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("job") Job job, Model model, RedirectAttributes redirectAttributes) {
        int page = jobService.getExpiredPageNoById(job);
        jobService.save(job);

        redirectAttributes.addFlashAttribute("notification",
                String.format("Job\"%s\" successfully updated", job.getJobTitle()));
        redirectAttributes.addFlashAttribute("action", "update");

        return "redirect:/expire/page/" + page;
    }

    @GetMapping("/view/{id}")
    public String viewJob(@PathVariable(value = "id") long id, Model model) {
        Job job = jobService.getById(id);
        model.addAttribute("job", job);
        model.addAttribute("isView", true);
        return "expire/new";
    }

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable(value = "id") long id,
                          Model model) {
        Job job = jobService.getById(id);
        model.addAttribute("job", job);
        return "expire/new";
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

        return "redirect:/expire/page/" + page;
    }

    @GetMapping("/page/{pageNo}")
    public String viewPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        Page<Job> page = jobService.findPaginatedForExpire(pageNo);
        List<Job> jobs = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("jobs", jobs);

        return "expire/job";
    }
}
