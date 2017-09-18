package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "legoLabyrinthSettings")
@Table(name = "LegoLabyrinthSettings")
@Component
@Scope(value = "prototype")

public class LegoLabyrinthSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "maxAttemptsNumber")
    private int maxAttemptsNumber;


    public int getMaxAttemptsNumber() {
        return maxAttemptsNumber;
    }

    public void setMaxAttemptsNumber(int maxAttemptsNumber) {
        this.maxAttemptsNumber = maxAttemptsNumber;
    }
}
