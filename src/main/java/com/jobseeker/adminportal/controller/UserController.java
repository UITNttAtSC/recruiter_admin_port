package com.jobseeker.adminportal.controller;

import com.jobseeker.adminportal.domain.Job;
import com.jobseeker.adminportal.domain.User;
import com.jobseeker.adminportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String viewJobPage(Model model) {
        return viewPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String viewPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        Page<User> page = userService.findPaginated(pageNo);
        List<User> users = page.getContent();


        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", users);

        return "user/user";
    }

    @GetMapping("/delete/{id}/{page}")
    public String deleteJob(@PathVariable(value = "id") long id,
                            @PathVariable(value = "page") int page,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        User user = userService.getById(id);
        this.userService.deleteById(id);

        redirectAttributes.addFlashAttribute("notification",
                String.format("User:\"%s\" successfully delete", user.getName()));
        redirectAttributes.addFlashAttribute("action", "delete");

        return "redirect:/user/page/" + page;
    }
}
