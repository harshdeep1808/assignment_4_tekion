package com.example.cricketapp.controller;

import com.example.cricketapp.model.Player;
import com.example.cricketapp.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/player")
@RestController
public class PlayerController {

    @Autowired
    PlayerServiceImpl psi;
    @PostMapping(path="/addPlayers",consumes= {"application/json"})
    public ResponseEntity<List<Player>> addPlayers(@RequestBody List<Player> players){
      return psi.addPlayers(players);
    }
}
