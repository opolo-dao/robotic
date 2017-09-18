package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@Entity(name = "legoFolkraceSettings")
@Table(name = "LegoFolkraceSettings")
@Component
@Scope(value = "prototype")
public class LegoFolkraceSettings implements CompetitionSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "groupsize")
    int groupSize;


    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
