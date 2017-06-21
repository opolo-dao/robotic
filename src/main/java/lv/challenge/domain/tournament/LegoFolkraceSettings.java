package lv.challenge.domain.tournament;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "LegoFolkraceSettings")
@Table(name = "LegoFolkraceSettings")
public class LegoFolkraceSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
}
