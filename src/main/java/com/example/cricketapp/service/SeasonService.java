package com.example.cricketapp.service;

import com.example.cricketapp.model.Scoreboard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeasonService {
    ResponseEntity<String> runSeason();
}
