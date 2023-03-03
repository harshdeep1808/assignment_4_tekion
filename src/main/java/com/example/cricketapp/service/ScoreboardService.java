package com.example.cricketapp.service;

import com.example.cricketapp.model.Scoreboard;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ScoreboardService{
    List<Scoreboard> getAll();
    void save(Scoreboard s);
}
