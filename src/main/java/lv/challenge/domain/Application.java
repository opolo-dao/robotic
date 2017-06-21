package lv.challenge.domain;

import lv.challenge.domain.tournament.Tournament;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.*;

/**
 * Created by Daniil on 17.06.2017.
 */
@ApplicationScope
@Component
@Entity(name = "Application")
@Table(name = "applicationSettings")
public class Application {
    @OneToOne
    @JoinColumn(name = "tournamentid")
    Tournament tournament;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
