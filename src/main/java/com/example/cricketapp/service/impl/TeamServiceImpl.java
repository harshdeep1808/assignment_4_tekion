package com.example.cricketapp.service.impl;

import com.example.cricketapp.dao.TeamRepo;
import com.example.cricketapp.model.Team;
import com.example.cricketapp.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepo repo;

    public TeamServiceImpl(TeamRepo repo) {
        this.repo = repo;
    }
    public ResponseEntity<List<Team>> addTeams(){
        String []teams={"A","B","C","D","E","F","G","H"};
        for(int i=0;i<8;i++){
            Team t=new Team(i,teams[i]);
            saveTeam(t);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(getTeams());
    }

    public List<Team> getTeams(){
        return repo.findAll();
    }

    public void saveTeam(Team t){
        repo.save(t);
    }
}
