package com.jobseeker.adminportal.controller;

import com.jobseeker.adminportal.common.JobStatus;
import com.jobseeker.adminportal.domain.JobPost;
import com.jobseeker.adminportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/review")
public class JobReviewController {
	
    @Autowired
    private JobPostService jobService;
    static List<String> jobLocationsList = new ArrayList<String>(){{
        add("Yangon");
        add("Mandalay");
        add("Nay Pyi Thaw");
        add("Mon");
        add("Shan");
        add("Bago");
        add("Sagaing");
        add("Kachin");
        add("Ayeyarwady");
        add("Rakhine");
        add("Magway");
        add("Tanintharyi");
        add("Kayin");
        add("Kaya");
}};
static List<String> jobCategoriesList = new ArrayList<String>(){{
    add("Administration, business and management");
    add("Alternative therapies");
    add("Animals, land and environment");
    add("Computing and ICT");
    add("Construction and building");
    add("Design, arts and crafts");
    add("Education and training");
    add("Engineering");
    add("Facilities and property services");
    add("Financial services");
    add("Garage services");
    add("Hairdressing and beauty");
    add("Healthcare");
    add("Heritage, culture and libraries");
    add("Hospitality, catering and tourism");
    add("Languages");
    add("Legal and court services");
    add("Manufacturing and production");
    add("Performing arts and media");
    add("Print and publishing, marketing and advertising");
    add("Retail and customer services");
    add("Science, mathematics and statistics");
    add("Security, uniformed and protective services");
    add("Social sciences and religion");
    add("Social work and caring services");
    add("Sport and leisure");
    add("Transport, distribution and logistics");
}};

    @GetMapping
    public String viewJobPage(Model model) {
        return viewPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String viewPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        Page<JobPost> page = jobService.findPaginatedForReview(pageNo);
        List<JobPost> jobs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("jobs", jobs);

        return "review/job";
    }

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable(value = "id") long id,
                          Model model) {
        JobPost job = jobService.findById(id);
        model.addAttribute("isView", false);
        model.addAttribute("post", job);
        model.addAttribute("jobLocationsList", jobLocationsList);
        model.addAttribute("jobCategoriesList", jobCategoriesList);
        return "review/new";
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("job") JobPost job, Model model, RedirectAttributes redirectAttributes) throws ParseException {
        int page = jobService.getReviewPageNoById(job);
        job.setStatus(true);
        job.setApprovedDate(LocalDateTime.now());
        jobService.save(job);
        
//        Date date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(job.getCreatedAt().toString());
//        job.setCreatedAt(date);

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
        JobPost job = jobService.findById(id);
        jobService.removeById(id);

        redirectAttributes.addFlashAttribute("notification",
                String.format("Job\"%s\" successfully delete", job.getJobTitle()));
        redirectAttributes.addFlashAttribute("action", "delete");

        return "redirect:/review/page/" + page;
    }

}
