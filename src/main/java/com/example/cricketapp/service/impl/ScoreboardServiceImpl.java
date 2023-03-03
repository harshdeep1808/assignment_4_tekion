package com.example.cricketapp.service.impl;

import com.example.cricketapp.dao.ScoreboardRepo;
import com.example.cricketapp.model.Scoreboard;
import com.example.cricketapp.service.ScoreboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreboardServiceImpl implements ScoreboardService {

    private final ScoreboardRepo repo;

    public ScoreboardServiceImpl(ScoreboardRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Scoreboard> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(Scoreboard s) {
        repo.save(s);
    }
}
