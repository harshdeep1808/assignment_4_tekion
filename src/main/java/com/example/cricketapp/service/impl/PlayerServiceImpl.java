package com.example.cricketapp.service.impl;

import com.example.cricketapp.dao.PlayerRepo;
import com.example.cricketapp.model.Player;
import com.example.cricketapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepo repo;
    public ResponseEntity<List<Player>> addPlayers(List<Player> players){
        for(int i=0;i<players.size();i++) repo.save(players.get(i));
        return ResponseEntity.status(HttpStatus.CREATED).body(players);
    }

    @Override
    public List<Player> getPlayers() {
        return repo.findAll();
    }
    @Override
    public void savePlayer(Player player){
        repo.save(player);
    }

    @Override
    public List<Player> findByType(String type,String team) {
        return repo.findByType(type,team);
    }

    @Override
    public List<Player> findByTeam(String team) {
        return repo.findByTeam(team);
    }
}
