package lv.challenge.domain.competitors;

import lv.challenge.domain.DomainObject;
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
@Entity(name = "Participant")
@Table(name = "participants")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Component
@Scope("prototype")
public class Participant implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;
    @Column(name = "age")
    int age;
    @Version
    int version;

    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Contact contact;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "robot_participant",
            joinColumns = @JoinColumn(name = "participantid"),
            inverseJoinColumns = @JoinColumn(name = "robotid")
    )
    Set<Robot> robots = new HashSet<>();

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Robot> getRobots() {
        return robots;
    }

    public void setRobots(Set<Robot> robots) {
        this.robots = robots;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (age != that.age) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        return surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }

    public static final class ParticipantBuilder {
        int age;
        int version;
        Contact contact;
        Set<Robot> robots = new HashSet<>();
        private Integer id;
        private String name;
        private String surname;
        private Team team;

        private ParticipantBuilder() {
        }

        public static ParticipantBuilder createParticipant() {
            return new ParticipantBuilder();
        }

        public ParticipantBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public ParticipantBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ParticipantBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ParticipantBuilder withTeam(Team team) {
            this.team = team;
            return this;
        }

        public ParticipantBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public ParticipantBuilder withVersion(int version) {
            this.version = version;
            return this;
        }

        public ParticipantBuilder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public ParticipantBuilder withRobots(Set<Robot> robots) {
            this.robots = robots;
            return this;
        }

        public Participant build() {
            Participant participant = new Participant();
            participant.setId(id);
            participant.setName(name);
            participant.setSurname(surname);
            participant.setTeam(team);
            participant.setAge(age);
            participant.setVersion(version);
            participant.setContact(contact);
            participant.setRobots(robots);
            return participant;
        }
    }
}
