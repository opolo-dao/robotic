package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "freeLabyrinthSettings")
@Table(name = "FreeLabyrinthSettings")
@Component
@Scope(value = "prototype")

public class FreeLabyrinthSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "maxattemptsnumber")
    private int maxAttemptsNumber;


    public int getMaxAttemptsNumber() {
        return maxAttemptsNumber;
    }

    public void setMaxAttemptsNumber(int maxAttemptsNumber) {
        this.maxAttemptsNumber = maxAttemptsNumber;
    }
}
