package lv.challenge.domain.competitions;

import lv.challenge.domain.competitors.Robot;
import lv.challenge.domain.tournament.Tournament;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Daniil on 31.05.2017.
 */
@Entity(name = "FreeLinefollower")
@Table(name = "freelinefollower")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Component
@Scope("prototype")
public class FreeLinefollower implements Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @OneToOne
    @JoinColumn(name = "robotid")
    Robot robot;
    @Column(name = "stime")
    Double sTime;
    @OneToOne
    @JoinColumn(name = "tournamentid")
    Tournament tournament;
    @Version
    Date date;

    public Robot getRobot() {
        return robot;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSTime() {
        return sTime;
    }

    public void setSTime(Double msTime) {
        this.sTime = msTime;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
