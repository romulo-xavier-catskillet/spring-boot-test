package com.example.sprintboottest.controllers;

import com.example.sprintboottest.models.Job;
import com.example.sprintboottest.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/jobs/")
    public String getAllJobs(Model model)
    {
        model.addAttribute("jobs", this.jobRepository.findAll());
        return "list";
    }

    @GetMapping("/jobs/create")
    public String create(Model model)
    {
        model.addAttribute("job", new Job());
        return "create";
    }

    @PostMapping("/jobs/store")
    public String store(@Valid Job job, BindingResult result)
    {
        try {
            if (result.hasErrors()) {
                return "create";
            }

            this.jobRepository.save(job);
            return "redirect:/jobs/";

        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @GetMapping("/jobs/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model)
    {
        Job job = this.jobRepository.findById(id).get();
        model.addAttribute("job", job);
        return "edit";
    }

    @PostMapping("/jobs/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid Job job, BindingResult result, Model model)
    {
        try {
            if (result.hasErrors()) {
                return "edit";
            }

            this.jobRepository.save(job);
            model.addAttribute("jobs", this.jobRepository.findAll());
            return "redirect:/jobs/";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @GetMapping("/jobs/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model)
    {
        try {
            this.jobRepository.deleteById(id);
            model.addAttribute("jobs", this.jobRepository.findAll());
            return "redirect:/jobs/";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

}
