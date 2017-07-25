package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Daniil on 16.06.2017.
 */
@Entity(name = "Tournament")
@Component
@Scope(value = "prototype")
@Table(name = "tournaments", schema = "contest")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "event_date")
    private LocalDateTime eventDateTime;
    @Column(name = "registration_allowed")
    private boolean registrationOpen;
    @Column(name = "start_registration")
    private LocalDateTime startRegistrationDateTime;
    @Column(name = "end_registration")
    private LocalDateTime endRegistrationDateTime;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "active")
    private boolean active;
    @OneToOne
    @JoinColumn(name = "LLFSid")
    private LegoLinefollowerSettings legoLinefollowerSettings;

    @OneToOne
    @JoinColumn(name = "FLFSid")
    private FreeLinefollowerSettings freeLinefollowerSettings;

    @OneToOne
    @JoinColumn(name = "FFRSid")
    private FreeFolkraceSettings freeFolkraceSettings;
    @OneToOne
    @JoinColumn(name = "LFRSid")
    private LegoFolkraceSettings legoFolkraceSettings;
    @OneToOne
    @JoinColumn(name = "FLSid")
    private FreeLabyrinthSettings freeLabyrinthSettings;
    @OneToOne
    @JoinColumn(name = "LLSid")
    private LegoLabyrinthSettings legoLabyrinthSettings;
    @OneToOne
    @JoinColumn(name = "MSSid")
    private MiniSumoSettings miniSumoSettings;
    @OneToOne
    @JoinColumn(name = "LSSid")
    private LegoSumoSettings legoSumoSettings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public boolean isRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        this.registrationOpen = registrationOpen;
    }

    public LocalDateTime getStartRegistrationDateTime() {
        return startRegistrationDateTime;
    }

    public void setStartRegistrationDateTime(LocalDateTime startRegistrationDateTime) {
        this.startRegistrationDateTime = startRegistrationDateTime;
    }

    public LocalDateTime getEndRegistrationDateTime() {
        return endRegistrationDateTime;
    }

    public void setEndRegistrationDateTime(LocalDateTime endRegistrationDateTime) {
        this.endRegistrationDateTime = endRegistrationDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LegoLinefollowerSettings getLegoLinefollowerSettings() {
        return legoLinefollowerSettings;
    }

    public void setLegoLinefollowerSettings(LegoLinefollowerSettings legoLinefollowerSettings) {
        this.legoLinefollowerSettings = legoLinefollowerSettings;
    }

    public FreeLinefollowerSettings getFreeLinefollowerSettings() {
        return freeLinefollowerSettings;
    }

    public void setFreeLinefollowerSettings(FreeLinefollowerSettings freeLinefollowerSettings) {
        this.freeLinefollowerSettings = freeLinefollowerSettings;
    }

    public FreeFolkraceSettings getFreeFolkraceSettings() {
        return freeFolkraceSettings;
    }

    public void setFreeFolkraceSettings(FreeFolkraceSettings freeFolkraceSettings) {
        this.freeFolkraceSettings = freeFolkraceSettings;
    }

    public LegoFolkraceSettings getLegoFolkraceSettings() {
        return legoFolkraceSettings;
    }

    public void setLegoFolkraceSettings(LegoFolkraceSettings legoFolkraceSettings) {
        this.legoFolkraceSettings = legoFolkraceSettings;
    }

    public FreeLabyrinthSettings getFreeLabyrinthSettings() {
        return freeLabyrinthSettings;
    }

    public void setFreeLabyrinthSettings(FreeLabyrinthSettings freeLabyrinthSettings) {
        this.freeLabyrinthSettings = freeLabyrinthSettings;
    }

    public LegoLabyrinthSettings getLegoLabyrinthSettings() {
        return legoLabyrinthSettings;
    }

    public void setLegoLabyrinthSettings(LegoLabyrinthSettings legoLabyrinthSettings) {
        this.legoLabyrinthSettings = legoLabyrinthSettings;
    }

    public MiniSumoSettings getMiniSumoSettings() {
        return miniSumoSettings;
    }

    public void setMiniSumoSettings(MiniSumoSettings miniSumoSettings) {
        this.miniSumoSettings = miniSumoSettings;
    }

    public LegoSumoSettings getLegoSumoSettings() {
        return legoSumoSettings;
    }

    public void setLegoSumoSettings(LegoSumoSettings legoSumoSettings) {
        this.legoSumoSettings = legoSumoSettings;
    }

    public static final class TournamentBuilder {
        private Integer id;
        private LocalDateTime eventDateTime;
        private boolean registrationOpen;
        private LocalDateTime startRegistrationDateTime;
        private LocalDateTime endRegistrationDateTime;
        private String name;
        private boolean active;
        private LegoLinefollowerSettings legoLinefollowerSettings;
        private FreeLinefollowerSettings freeLinefollowerSettings;
        private FreeFolkraceSettings freeFolkraceSettings;
        private LegoFolkraceSettings legoFolkraceSettings;
        private FreeLabyrinthSettings freeLabyrinthSettings;
        private LegoLabyrinthSettings legoLabyrinthSettings;
        private MiniSumoSettings miniSumoSettings;
        private LegoSumoSettings legoSumoSettings;

        private TournamentBuilder() {
        }

        public static TournamentBuilder createTournament() {
            return new TournamentBuilder();
        }

        public TournamentBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public TournamentBuilder withEventDateTime(LocalDateTime eventDateTime) {
            this.eventDateTime = eventDateTime;
            return this;
        }

        public TournamentBuilder withRegistrationOpen(boolean registrationOpen) {
            this.registrationOpen = registrationOpen;
            return this;
        }

        public TournamentBuilder withStartRegistrationDateTime(LocalDateTime startRegistrationDateTime) {
            this.startRegistrationDateTime = startRegistrationDateTime;
            return this;
        }

        public TournamentBuilder withEndRegistrationDateTime(LocalDateTime endRegistrationDateTime) {
            this.endRegistrationDateTime = endRegistrationDateTime;
            return this;
        }

        public TournamentBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TournamentBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public TournamentBuilder withLegoLinefollowerSettings(LegoLinefollowerSettings legoLinefollowerSettings) {
            this.legoLinefollowerSettings = legoLinefollowerSettings;
            return this;
        }

        public TournamentBuilder withFreeLinefollowerSettings(FreeLinefollowerSettings freeLinefollowerSettings) {
            this.freeLinefollowerSettings = freeLinefollowerSettings;
            return this;
        }

        public TournamentBuilder withFreeFolkraceSettings(FreeFolkraceSettings freeFolkraceSettings) {
            this.freeFolkraceSettings = freeFolkraceSettings;
            return this;
        }

        public TournamentBuilder withLegoFolkraceSettings(LegoFolkraceSettings legoFolkraceSettings) {
            this.legoFolkraceSettings = legoFolkraceSettings;
            return this;
        }

        public TournamentBuilder withFreeLabyrinthSettings(FreeLabyrinthSettings freeLabyrinthSettings) {
            this.freeLabyrinthSettings = freeLabyrinthSettings;
            return this;
        }

        public TournamentBuilder withLegoLabyrinthSettings(LegoLabyrinthSettings legoLabyrinthSettings) {
            this.legoLabyrinthSettings = legoLabyrinthSettings;
            return this;
        }

        public TournamentBuilder withMiniSumoSettings(MiniSumoSettings miniSumoSettings) {
            this.miniSumoSettings = miniSumoSettings;
            return this;
        }

        public TournamentBuilder withLegoSumoSettings(LegoSumoSettings legoSumoSettings) {
            this.legoSumoSettings = legoSumoSettings;
            return this;
        }

        public Tournament build() {
            Tournament tournament = new Tournament();
            tournament.setId(id);
            tournament.setEventDateTime(eventDateTime);
            tournament.setRegistrationOpen(registrationOpen);
            tournament.setStartRegistrationDateTime(startRegistrationDateTime);
            tournament.setEndRegistrationDateTime(endRegistrationDateTime);
            tournament.setName(name);
            tournament.setActive(active);
            tournament.setLegoLinefollowerSettings(legoLinefollowerSettings);
            tournament.setFreeLinefollowerSettings(freeLinefollowerSettings);
            tournament.setFreeFolkraceSettings(freeFolkraceSettings);
            tournament.setLegoFolkraceSettings(legoFolkraceSettings);
            tournament.setFreeLabyrinthSettings(freeLabyrinthSettings);
            tournament.setLegoLabyrinthSettings(legoLabyrinthSettings);
            tournament.setMiniSumoSettings(miniSumoSettings);
            tournament.setLegoSumoSettings(legoSumoSettings);
            return tournament;
        }
    }
}
