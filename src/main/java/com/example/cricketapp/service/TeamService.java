package com.example.cricketapp.service;

import com.example.cricketapp.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    ResponseEntity<List<Team>> addTeams();
    List<Team> getTeams();
    void saveTeam(Team t);
}
