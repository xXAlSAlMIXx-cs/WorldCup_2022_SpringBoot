package com.tryspringboot.hw2_s133334.Controller;

import com.tryspringboot.hw2_s133334.Model.Team;
import com.tryspringboot.hw2_s133334.Model.TeamResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.tryspringboot.hw2_s133334.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class NavigationController {
    final String REST_URL = "http://localhost:8080/api";

    @Autowired
    DatabaseAccess da;

    @Autowired
    private RestTemplate restTemplate;  // ADD THIS

    @GetMapping("/")
    public String home(Model model) {
        return "Home";
    }


    @GetMapping("/display")
    public String DisplayTeams(Model model) {
        List<Team> teams = da.getTeams();

        List<TeamResult> results = teams.stream().map(team -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Team> entity = new HttpEntity<>(team, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    REST_URL + "/calculate", entity, Map.class);

            Map<String, Integer> calc = response.getBody();
            return new TeamResult(team, calc.get("played"), calc.get("points"));
        }).collect(Collectors.toList());

        model.addAttribute("results", results);
        return "displayResults";
    }

    /* ---------- ADD TEAM ---------- */
    @GetMapping("/add")
    public String AddTeam(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("pageTitle", "Add Team");
        return "AddTeam";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute Team team,Model model) {
        da.add(team);
        return DisplayTeams(model);
    }

    /* ---------- EDIT TEAM ---------- */
    @GetMapping("/edit")
    public String EditTeam(Model model) {
        model.addAttribute("teams", da.getTeams());
        return "EditTeam";
    }

    @GetMapping("/edit/{id}")
    public String EditTeamForm(@PathVariable long id, Model model) {
        Team t = da.findByID(id);
        if (t == null) {
            model.addAttribute("teams",da.getTeams());
            return "EditTeam";
        }
        model.addAttribute("team", t);
        return "EditSingleTeam";
    }

    @PostMapping("/edit/{id}")
    public String EditTeamSubmit(@PathVariable Long id, @ModelAttribute("team") Team team, Model model) {
        da.modify(team);
        return DisplayTeams(model);
    }

    @GetMapping("/edit/search")
    public String Search(@RequestParam String search,@RequestParam String search_method, Model model) {
        List<Team> teams = da.getTeams();
        List<Team> Result;
        if (search_method.equals("TeamName")) {
            Result= teams.stream().filter(team -> team.getTeamName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        }else if (search_method.equals("continent")) {
            Result= teams.stream().filter(team -> team.getContinent().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        }else {
            Result =teams;
        }
        model.addAttribute("teams",Result);
        return "EditTeam" ;
    }


    /* ---------- DELETE TEAM ---------- */
    @GetMapping("/delete")
    public String DeleteTeam(Model model) {
        model.addAttribute("teams",da.getTeams());
        return "DeleteTeam";
    }

//    @GetMapping("/delete/{id}")
//    public String DeleteTeamForm(@PathVariable long id,Model model) {
//        Team t = da.findByID(id);
//        if (t == null) {
//            return "redirect:/display";
//        }
//        model.addAttribute("team", t);
//        return "addTeam";
//    }

    @GetMapping("/delete/{id}")
    public String AddTeamSubmit(@PathVariable long id,@ModelAttribute Team team,Model model) {
        da.deleteTeam(id);
        return DisplayTeams(model);
    }

    @GetMapping("/delete/search")
    public String DSearch(@RequestParam String search,@RequestParam String search_method, Model model) {
        List<Team> teams1 = da.getTeams();
        List<Team> Result;
        if (search_method.equals("TeamName")) {
            Result= teams1.stream().filter(team -> team.getTeamName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        }else if (search_method.equals("continent")) {
            Result= teams1.stream().filter(team -> team.getContinent().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
        }else {
            Result =teams1;
        }
        model.addAttribute("teams",Result);
        return "DeleteTeam" ;
    }
}
