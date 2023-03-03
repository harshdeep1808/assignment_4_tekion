package com.example.cricketapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Season {
    @Id
    private int sid;
    private String winner;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Match> matches=new ArrayList<Match>();

    public Season() {
    }

    public Season(int sid, String winner, List<Match> matches) {
        this.sid = sid;
        this.winner = winner;
        this.matches = matches;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Season{" +
                "sid=" + sid +
                ", result='" + winner + '\'' +
                ", matches=" + matches +
                '}';
    }
}
