package com.example.cricketapp.service;

import com.example.cricketapp.model.Match;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


public interface MatchService {
    public ResponseEntity<String> toss();
    public ResponseEntity<Match> runMatch(String toss, String [] team);
}
