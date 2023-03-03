package com.example.cricketapp.controller;

import com.example.cricketapp.dao.PlayerRepo;
import com.example.cricketapp.dao.SeasonRepo;
import com.example.cricketapp.dao.TeamRepo;
import com.example.cricketapp.model.Match;
import com.example.cricketapp.model.Player;
import com.example.cricketapp.model.Season;
import com.example.cricketapp.model.Team;
import com.example.cricketapp.service.impl.MatchServiceImpl;
import com.example.cricketapp.service.impl.SeasonServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/season")
@RestController
public class SeasonController {
    @Autowired
    SeasonServiceImpl ssi;

    @GetMapping("/runSeason")
    public ResponseEntity<String> runSeason(){
       return ssi.runSeason();
    }
}
