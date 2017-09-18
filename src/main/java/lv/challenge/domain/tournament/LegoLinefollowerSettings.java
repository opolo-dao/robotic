package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "legoLinefollowerSettings")
@Table(name = "LegoLinefollowerSettings")
@Component
@Scope(value = "prototype")
public class LegoLinefollowerSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "attempts")
    private int numberOfAttempts;
    @Column(name = "maxTimeS")
    private double maxTimeS;

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public double getMaxTimeS() {
        return maxTimeS;
    }

    public void setMaxTimeS(double maxTimeS) {
        this.maxTimeS = maxTimeS;
    }

}
