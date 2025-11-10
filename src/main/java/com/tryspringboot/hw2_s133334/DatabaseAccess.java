package com.tryspringboot.hw2_s133334;

import com.tryspringboot.hw2_s133334.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public List<Team> getTeams() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "select * from Teams";
        return jdbc.query(sql,params , new BeanPropertyRowMapper<>(Team.class));
    }

    public void add(Team team) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String query = "INSERT INTO Teams (TeamName, Continent, Won, Drawn, Lost) VALUES (:TeamName,:Continent,:Won,:Drawn,:Lost);";
        params.addValue("TeamName", team.getTeamName());
        params.addValue("Continent", team.getContinent());
        params.addValue("Won", team.getWon());
        params.addValue("Drawn", team.getDrawn());
        params.addValue("Lost", team.getLost());
        int affected_row=jdbc.update(query, params);
        if (affected_row==1) {
            System.out.println("Team added successfully");
        }
        else {
            System.out.println("Team not added, There is a problem");
        }
    }


    public Team finds(String county) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "select * from Teams where Continent like :Continent";
        return jdbc.queryForObject(sql,params,new BeanPropertyRowMapper<>(Team.class));
    }

    public List<Team> findsByName(String TeamName) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "select * from Teams where TeamName like :TeamName";
        params.addValue("TeamName",TeamName);
        return jdbc.query(sql,params,new BeanPropertyRowMapper<>(Team.class));
    }
    public Team findByID(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "select * from Teams where TeamID=:id";
        params.addValue("id", id);
        return jdbc.queryForObject(sql,params,new BeanPropertyRowMapper<>(Team.class));
    }


    public void modify(Team team) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "UPDATE Teams set TeamName= :TeamName ,Continent= :Continent , Won= :Won, Drawn = :Drawn , Lost = :Lost where TeamID=:TeamID";
        params.addValue("TeamID", team.getTeamID());
        params.addValue("TeamName", team.getTeamName());
        params.addValue("Continent", team.getContinent());
        params.addValue("Won", team.getWon());
        params.addValue("Drawn", team.getDrawn());
        params.addValue("Lost", team.getLost());
        int affected_row=jdbc.update(sql, params);
        if (affected_row==1) {
            System.out.println("Team modified successfully");
        }
        else {
            System.out.println("Team not modified, There is a problem");
        }
    }

    public void deleteTeam(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "delete from Teams where TeamID=:id";
        params.addValue("id", id);
        int affected_row=jdbc.update(sql, params);
        if (affected_row==1) {
            System.out.println("Team deleted successfully"+id);
        }
    }
}
