package com.example.cricketapp.dao;

import com.example.cricketapp.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepo extends JpaRepository<Season, Integer> {
}
