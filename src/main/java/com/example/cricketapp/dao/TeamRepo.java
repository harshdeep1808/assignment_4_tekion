package com.example.cricketapp.dao;
import com.example.cricketapp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team,Integer> {
}
