package lv.challenge.domain.competitions;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 15.05.2017.
 */
@Entity(name = "MiniSumo")
@Table(name = "minisumo")
@Component
@Scope("prototype")
public class MiniSumo implements Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @OneToOne
    @JoinColumn(name = "robotid")
    Robot robot;
    @Column(name = "wins")
    int wins;
    @Column(name = "loses")
    int loses;
    @Column(name = "points")
    int points;
    @OneToOne
    @JoinColumn(name = "tournamentid")
    Tournament tournament;

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public MiniSumo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
