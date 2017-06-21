package lv.challenge.domain.tournament;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "LegoLabyrinthSettings")
@Table(name = "LegoLabyrinthSettings")
public class LegoLabyrinthSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
}
