package lv.challenge.domain.competitions;

import lv.challenge.domain.competitors.Robot;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 31.05.2017.
 */
@Entity(name = "LegoSumo")
@Table(name = "legosumo")
@Component
@Scope("prototype")
public class LegoSumo implements Competition {

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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
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
