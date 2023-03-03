package com.example.cricketapp.dao;
import com.example.cricketapp.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match,Integer>
{

}
