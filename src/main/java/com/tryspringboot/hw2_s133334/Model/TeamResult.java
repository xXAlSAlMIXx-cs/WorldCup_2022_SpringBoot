package com.tryspringboot.hw2_s133334.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResult {
    private Long TeamID;
    private String TeamName;
    private String Continent;
    private int Won;
    private int Drawn;
    private int Lost;
    private int played;
    private int points;
    public TeamResult(Team team, int played, int points) {
        this.TeamID = team.getTeamID();
        this.TeamName = team.getTeamName();
        this.Continent = team.getContinent();
        this.Won = team.getWon();
        this.Drawn = team.getDrawn();
        this.Lost = team.getLost();
        this.played = played;
        this.points = points;
    }
}