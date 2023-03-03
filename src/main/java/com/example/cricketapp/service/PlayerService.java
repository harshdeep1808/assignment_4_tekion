package com.example.cricketapp.service;

import com.example.cricketapp.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerService {
     ResponseEntity<List<Player>> addPlayers( List<Player> players);
     List<Player> getPlayers();
     void savePlayer(Player player);
     List<Player> findByType(String type,String team);
     List<Player> findByTeam(String team);
}
