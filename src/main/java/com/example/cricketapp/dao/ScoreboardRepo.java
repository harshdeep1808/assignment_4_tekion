package com.example.cricketapp.dao;
import com.example.cricketapp.model.Scoreboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreboardRepo extends JpaRepository<Scoreboard,Integer>
{

}
