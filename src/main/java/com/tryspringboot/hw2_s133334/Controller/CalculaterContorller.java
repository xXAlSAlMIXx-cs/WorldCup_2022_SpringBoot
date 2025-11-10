package com.tryspringboot.hw2_s133334.Controller;

import com.tryspringboot.hw2_s133334.DatabaseAccess;
import com.tryspringboot.hw2_s133334.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CalculaterContorller {

    @Autowired
    DatabaseAccess databaseAccess;

    @PostMapping("/calculate")
    public Map<String, Integer> calculate(@RequestBody Team team) {
        int played = team.getWon() + team.getDrawn() + team.getLost();
        int points = team.getWon() * 3 + team.getDrawn();

        Map<String, Integer> result = new HashMap<>();
        result.put("played", played);
        result.put("points", points);
        return result;
    }

}
