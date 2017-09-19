package lv.challenge.domain.competitors;

import com.google.gson.annotations.Expose;
import lv.challenge.domain.DomainObject;
import lv.challenge.domain.competitions.CompetitionType;
import lv.challenge.domain.tournament.Tournament;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniil on 12.04.2017.
 */
@Entity(name = "Robot")
@Table(name = "robots")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Component
@Scope("prototype")
public class Robot implements DomainObject {
    public Robot() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Expose
    int id;
    @Column(name = "name", unique = true, nullable = false)
    @Expose
    String name;
    @Column(name = "registerednumber", unique = true)
    Integer registeredNumber;
    @Column(name = "checked", nullable = false)
    boolean checked;
    @Version
    int version;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "robot_participant",
            joinColumns = @JoinColumn(name = "robotid"),
            inverseJoinColumns = @JoinColumn(name = "participantid")
    )
    Set<Participant> operators = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamid")
    Team team;
    @ElementCollection
    Set<CompetitionType> competitions = new HashSet<>();
    @Column(name = "registered")
    private boolean registered;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "robot_tournament",
            joinColumns = @JoinColumn(name = "robotid"),
            inverseJoinColumns = @JoinColumn(name = "tournamentid")
    )
    Set<Tournament> tournaments = new HashSet<>();
    @Column(name = "adminComment")
    String adminComment;

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<CompetitionType> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Set<CompetitionType> competitions) {
        this.competitions = competitions;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Participant> getOperators() {
        return operators;
    }

    public void setOperators(Set<Participant> operators) {
        this.operators = operators;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRegistered_number() {
        return registeredNumber;
    }

    public void setRegistered_number(Integer number) {
        this.registeredNumber = number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        if (id != robot.id) return false;
        if (!name.equals(robot.name)) return false;
        if (operators != null ? !operators.equals(robot.operators) : robot.operators != null) return false;
        if (team != null ? !team.equals(robot.team) : robot.team != null) return false;
        return competitions != null ? competitions.equals(robot.competitions) : robot.competitions == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    public static final class RobotBuilder {
        int id;
        String name;
        int version;
        Integer registeredNumber;
        Set<Participant> operators = new HashSet<>();
        Team team;
        Set<CompetitionType> competitions = new HashSet<>();
        boolean checked;

        private RobotBuilder() {
        }

        public static RobotBuilder createRobot() {
            return new RobotBuilder();
        }

        public RobotBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public RobotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RobotBuilder withVersion(int version) {
            this.version = version;
            return this;
        }

        public RobotBuilder withOperators(Set<Participant> operators) {
            this.operators = operators;
            return this;
        }

        public RobotBuilder withTeam(Team team) {
            this.team = team;
            return this;
        }

        public RobotBuilder withCompetitions(Set<CompetitionType> competitions) {
            this.competitions = competitions;
            return this;
        }


        public RobotBuilder withChecked(boolean checked) {
            this.checked = checked;
            return this;
        }


        public Robot build() {
            Robot robot = new Robot();
            robot.setId(id);
            robot.setName(name);
            robot.setVersion(version);
            robot.setOperators(operators);
            robot.setTeam(team);
            robot.setCompetitions(competitions);
            robot.setRegistered_number(registeredNumber);
            robot.setChecked(checked);
            return robot;
        }
    }
}
