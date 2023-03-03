package com.example.cricketapp.controller;
import com.example.cricketapp.model.Match;
import com.example.cricketapp.service.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/match")
@RestController
public class MatchController {

     @Autowired
    MatchServiceImpl msi;

       @GetMapping("/toss")
    public ResponseEntity<String> toss(){
           return msi.toss();
       }
       @GetMapping("/runMatch/{toss}")
    public ResponseEntity<Match> runMatch(@PathVariable String toss) {
           String [] teams={"A","B"};
          return  msi.runMatch(toss,teams);
       }
}
