package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    public String displaySearchResults(Model model, @RequestParam String type, @RequestParam String term) {
        ArrayList<Job> jobs;
        model.addAttribute("columns", columnChoices);
        if (!type.toLowerCase().equals("all")) {
            jobs = JobData.findByColumnAndValue(type, term);
            model.addAttribute("title", "Job on " + columnChoices.get(type) + ": " + term)
        }
        else {
            if (term.equals("")) {
                jobs = JobData.findAll();
                model.addAttribute("title", "Jobs");
            } else {
                jobs = JobData.findByColumnAndValue(type, term);
                model.addAttribute("title", "Jobs on " + term)
            }
        }
        model.addAttribute("jobs", jobs);
        return "search";
    }


}
