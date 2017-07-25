package lv.challenge.application;

import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

/**
 * Created by Daniil on 18.07.2017.
 */
@Component
public class ApplicationService {
    Application app = new Application();
    String propertiesFileName = "/app.properties";
    @Autowired
    TournamentService tournamentService;

    protected ApplicationService() {
    }

    @PostConstruct
    private void init() {
        Optional<Tournament> opt = tournamentService.getActiveTournament();
        if (opt.isPresent())
            app.tournament = opt.get();
        else app.tournament = null;
    }

    public void setActiveTournament(Tournament tournament) {
        app.tournament = tournament;
    }

    @PreDestroy
    public void saveAppSettings() {
    }

    public Tournament getActiveTournament() {
        return app.tournament;
    }
}
