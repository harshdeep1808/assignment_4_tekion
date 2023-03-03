package com.example.cricketapp.service.impl;

import com.example.cricketapp.dao.MatchRepo;
import com.example.cricketapp.dao.PlayerRepo;
import com.example.cricketapp.dao.ScoreboardRepo;
import com.example.cricketapp.model.Match;
import com.example.cricketapp.model.Player;
import com.example.cricketapp.model.Scoreboard;
import com.example.cricketapp.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepo repo;
    private final PlayerServiceImpl psi;
    private final ScoreboardServiceImpl ssi;

    public MatchServiceImpl(MatchRepo repo,PlayerServiceImpl psi,ScoreboardServiceImpl ssi) {
        this.repo = repo;
        this.psi=psi;
        this.ssi=ssi;
    }

    public ResponseEntity<String> toss(){
        int a =(int)Math.floor((Math.random())*2);
        return a==0?ResponseEntity.ok("A"):ResponseEntity.ok("B");
    }

    public int func(){
        int rand=(int)Math.floor((Math.random()*8)-1);
        if(rand==5) return func();
        return rand;
    }

    public void updateWicket(Player bowler){
        int wickets= bowler.getWickets();
        wickets++;
        bowler.setWickets(wickets);
        psi.savePlayer(bowler);
    }
    public void updateBalls(Player bowler){
        int balls= bowler.getBalls();
        balls++;
        bowler.setBalls(balls);
        psi.savePlayer(bowler);
    }
    public void updateRuns(Player batsman,int run){
        int runs= batsman.getRuns();
        runs+=run;
        batsman.setRuns(runs);
        psi.savePlayer(batsman);
    }

    public ResponseEntity<Match> runMatch( String toss,String [] teams){
        Match m =new Match();
        m.setMid(repo.findAll().size());
        int totalOvers=10;
        Boolean matchOver=false;
        int balls=0,overs=0,wickets=0,runs=0,currTeam=toss.equals(teams[0])?0:1,target=-1;
        List<Player> bowlers=psi.findByType("bowler",currTeam==0?teams[1]:teams[0]);
        List<Player> batsmans=psi.findByTeam(teams[currTeam]);
        int batsmanInd=0,bowlersInd=0,innings=0;
        Player bowler=bowlers.get(bowlersInd);
        Player batsman= batsmans.get(batsmanInd);
        boolean flag=false;
        String winner="";
        String loser="";
        while(!matchOver){
            int res=func();
            balls=(balls+1)%7;
            if(balls==0)balls++;
            if(res==-1){  //-1==wicket taken
                updateWicket(bowler);
                wickets++;
                batsmanInd++;
                if(wickets>=5){
                    Scoreboard s = new Scoreboard(ssi.getAll().size(), teams[currTeam], batsman.getName(), bowler.getName(), wickets, overs, balls, runs);
                    ssi.save(s);
                    List<Scoreboard> l=m.getScoreboard();
                    l.add(s);
                    m.setScoreboard(l);
                    innings++;
                    if(target==-1) {
                        target = runs;
                    }
                    if(innings<2){
                        if(currTeam==1)currTeam=0;
                        else currTeam=1;
                        balls=overs=wickets=runs=batsmanInd=bowlersInd=0;
                        if(currTeam==0)
                            bowlers=psi.findByType("bowler",teams[1]);
                        else bowlers=psi.findByType("bowler",teams[0]);
                        batsmans=psi.findByTeam(teams[currTeam]);
                        bowler=bowlers.get(bowlersInd);
                        batsman= batsmans.get(batsmanInd);
                    }
                }
                else batsman= batsmans.get(batsmanInd);
            }else{
                updateBalls(bowler);
                updateRuns(batsman,res);
                runs+=res;
                if(innings==1){
                    if(runs>target){
                        innings++;
                    }
                }
            }

            Scoreboard s = new Scoreboard(ssi.getAll().size(), teams[currTeam], batsman.getName(), bowler.getName(), wickets, overs, balls, runs);
            ssi.save(s);
            List<Scoreboard> l=m.getScoreboard();
            l.add(s);
            m.setScoreboard(l);

            if(balls==6&&flag){
                overs++;
                if(overs==totalOvers){
                    if(target==-1)
                        target=runs;
                    if(innings<2){
                        if(currTeam==1)currTeam=0;
                        else currTeam=1;
                        innings++;
                        balls=overs=wickets=runs=batsmanInd=bowlersInd=0;
                        bowlers=psi.findByType("bowler",currTeam==0?teams[1]:teams[0]);
                        batsmans=psi.findByTeam(teams[currTeam]);
                    }
                }
                if(innings<2){
                    bowlersInd=(bowlersInd+1)%2;
                    bowler=bowlers.get(bowlersInd);
                }
            }
            if(innings==2){
                if(runs<target) {
                    if(currTeam==0)
                        currTeam=1;
                    else currTeam=0;
                }
                winner = teams[currTeam];
                if(currTeam==0) loser=teams[1];
                else loser=teams[0];
                matchOver=true;
                break;
            }
            flag=true;
        }
        //.
        //.
        //logic ends
        String result="Team "+winner+" won by "+Integer.toString(Math.abs(target-runs))+" runs.";
        m.setResult(result);
        m.setWinnner(winner);
        m.setLoser(loser);
        repo.save(m);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
}
