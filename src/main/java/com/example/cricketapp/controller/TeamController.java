package com.example.cricketapp.controller;

import com.example.cricketapp.dao.TeamRepo;
import com.example.cricketapp.model.Team;
import com.example.cricketapp.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/team")
@RestController
public class TeamController {

    @Autowired
    TeamServiceImpl tsi;

    @GetMapping(path="/addTeams")
    public ResponseEntity<List<Team>> addTeams(){
        return tsi.addTeams();
    }
}
