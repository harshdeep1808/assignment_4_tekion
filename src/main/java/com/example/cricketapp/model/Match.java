package com.example.cricketapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Match {
    @Id
    private int mid;
    String result;
    String winnner;
    String loser;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Scoreboard> scoreboard=new ArrayList<Scoreboard>();

    public Match(){

    }

    public Match(int mid, String result, String winnner,String loser, List<Scoreboard> scoreboard) {
        this.mid = mid;
        this.result = result;
        this.winnner = winnner;
        this.scoreboard = scoreboard;
        this.loser=loser;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    @Override
    public String toString() {
        return "Match{" +
                "mid=" + mid +
                ", result='" + result + '\'' +
                ", scoreboard=" + scoreboard +
                '}';
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Scoreboard> getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(List<Scoreboard> scoreboard) {
        this.scoreboard = scoreboard;
    }
    public String getWinnner() {
        return winnner;
    }

    public void setWinnner(String winnner) {
        this.winnner = winnner;
    }
}
