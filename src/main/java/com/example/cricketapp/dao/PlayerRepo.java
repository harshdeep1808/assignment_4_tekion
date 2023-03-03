package com.example.cricketapp.dao;
import com.example.cricketapp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepo extends JpaRepository<Player,Integer>
{
    @Query("from Player where type=?1 and team=?2")
   List<Player> findByType(String type, String team);
    List<Player> findByTeam(String team);
}
