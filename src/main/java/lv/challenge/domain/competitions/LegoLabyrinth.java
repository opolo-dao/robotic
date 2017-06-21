package lv.challenge.domain.competitions;

import lv.challenge.domain.competitors.Robot;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 31.05.2017.
 */
@Entity(name = "LegoLabyrinth")
@Table(name = "legolabyrinth")
@Component
@Scope("prototype")
public class LegoLabyrinth implements Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @OneToOne
    @JoinColumn(name = "robotid")
    Robot robot;

    public Robot getRobot() {
        return robot;
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
