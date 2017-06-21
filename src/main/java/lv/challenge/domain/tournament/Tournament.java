package lv.challenge.domain.tournament;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

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
    private int id;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "registration_allowed")
    private boolean registrationOpen;
    @Column(name = "name")
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

    public FreeLinefollowerSettings getFreeLinefollowerSettings() {
        return freeLinefollowerSettings;
    }

    public void setFreeLinefollowerSettings(FreeLinefollowerSettings freeLinefollowerSettings) {
        this.freeLinefollowerSettings = freeLinefollowerSettings;
    }

    public LegoLinefollowerSettings getLegoLinefollowerSettings() {
        return legoLinefollowerSettings;
    }

    public void setLegoLinefollowerSettings(LegoLinefollowerSettings legoLinefollowerSettings) {
        this.legoLinefollowerSettings = legoLinefollowerSettings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isRegistrationOpen() {
        return registrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        this.registrationOpen = registrationOpen;
    }
}
