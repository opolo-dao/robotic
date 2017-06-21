package lv.challenge.domain.competitions;

import lv.challenge.domain.competitors.Robot;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 31.05.2017.
 */
@Entity(name = "LegoLinefollower")
@Table(name = "legolinefollower")
@Component
@Scope("prototype")
public class LegoLinefollower implements Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @OneToOne
    @JoinColumn(name = "robotid")
    Robot robot;
    @Column(name = "mstime")
    int millisecondsTime;

    public Robot getRobot() {
        return robot;
    }

    public int getMillisecondsTime() {
        return millisecondsTime;
    }

    public void setMillisecondsTime(int millisecondsTime) {
        this.millisecondsTime = millisecondsTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
