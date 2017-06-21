package lv.challenge.domain.tournament;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "LegoLinefollowerSettings")
@Table(name = "LegoLinefollowerSettings")
public class LegoLinefollowerSettings implements CompetitionSettings{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "attempts")
    private int numberOfAttempts;
    @Column(name = "maxTimeMs")
    private int maxTimeMs;
}
