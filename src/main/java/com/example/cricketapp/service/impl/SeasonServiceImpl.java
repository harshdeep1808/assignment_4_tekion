package com.example.cricketapp.service.impl;

import com.example.cricketapp.dao.PlayerRepo;
import com.example.cricketapp.dao.SeasonRepo;
import com.example.cricketapp.dao.TeamRepo;
import com.example.cricketapp.model.Match;
import com.example.cricketapp.model.Player;
import com.example.cricketapp.model.Season;
import com.example.cricketapp.model.Team;
import com.example.cricketapp.service.PlayerService;
import com.example.cricketapp.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonServiceImpl implements SeasonService {

    SeasonRepo repo;
    MatchServiceImpl msi;

    TeamServiceImpl tsi;
    PlayerServiceImpl psi;

    public SeasonServiceImpl(SeasonRepo repo, MatchServiceImpl msi, TeamServiceImpl tsi,PlayerServiceImpl psi) {
        this.repo = repo;
        this.msi = msi;
        this.tsi = tsi;
        this.psi=psi;
    }

    public void addPlayers(Team t){
        int pid=psi.getPlayers().size();
        String type="";
        for(int i=0;i<5;i++){
            if(i<3)type="batsman";
            else type="bowler";
            String name=t.getName()+Integer.toString(i);
            Player p=new Player(pid,name,t.getName(),type);
            psi.savePlayer(p);
            pid++;
        }
    }
    public ResponseEntity<String> runSeason(){
        Season s=new Season();
        s.setSid(repo.findAll().size());
        //get teams
        List<Team> teams=tsi.getTeams();
        for(int i=0;i<teams.size();i++) addPlayers(teams.get(i));
        String seasonWinner="";
        String winner="";
        // loop starts while teams!=0
        while(teams.size()>1) {
            List<Integer> del=new ArrayList<>();
            for(int i=0;i<teams.size();i+=2){
                List<Team> currteams= new ArrayList<>();
                currteams.add(teams.get(i));
                currteams.add(teams.get(i+1));
                //add players
                Team t1=teams.get(i);Team t2=teams.get(i+1);
                //toss
                String toss=msi.toss().getBody();
                //run match between consecutive teams
                String [] t={t1.getName(),t2.getName()};
                Match m=msi.runMatch(toss,t).getBody();
                winner=m.getWinnner();
                List<Match> l=s.getMatches();
                l.add(m);
                s.setMatches(l);
                repo.save(s);
                //remove all losing team in the end
                int delInd;
                if(winner==t1.getName())
                    delInd=i+1;
                else delInd=i;
                del.add(delInd);
            }
            //del from teams
            List<Team> temp=new ArrayList<Team>();
            for(int i=0;i<teams.size();i++){
                if(del.contains(i))
                    continue;
                temp.add(teams.get(i));
            }
            teams=temp;
            if(teams.size()==1) seasonWinner=winner;
        }
        s.setWinner(seasonWinner);
        repo.save(s);
        //loop ends
        return new ResponseEntity<>(seasonWinner, HttpStatus.OK);
    }
}
