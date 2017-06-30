package lv.challenge.domain.competitors;

import lv.challenge.domain.DomainObject;
import lv.challenge.domain.users.User;
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
@Entity(name = "Team")
@Table(name = "teams")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Component
@Scope("prototype")
public class Team  implements DomainObject {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", updatable = false, nullable = false)
        private int id;
        @Column(name = "name", unique = true, nullable = false)
        private String name;
        @OneToMany
        @JoinColumn(name="teamid")
        private Set<Participant> participants = new HashSet<>();
        @OneToMany
        @JoinColumn(name="teamid")
        private Set<Robot> robots = new HashSet<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;
        @Version
        private int version;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

        public Set<Participant> getParticipants() {
                return participants;
        }

        public void setParticipants(Set<Participant> participants) {
                this.participants = participants;
        }

        public Set<Robot> getRobots() {
                return robots;
        }

        public void setRobots(Set<Robot> robots) {
                this.robots = robots;
        }

        public int getVersion() {
                return version;
        }

        public void setVersion(int version) {
                this.version = version;
        }

        public int getId() {
                return id;
        }


        public String getName() {
                return name;
        }

        public void setId(int id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }


        public static final class TeamBuilder {
                private int id;
                private String name;
                private Set<Participant> participants = new HashSet<>();
                private Set<Robot> robots = new HashSet<>();
            private User user;
                private int version;

                private TeamBuilder() {
                }

                public static TeamBuilder createTeam() {
                        return new TeamBuilder();
                }

                public TeamBuilder withId(int id) {
                        this.id = id;
                        return this;
                }

                public TeamBuilder withName(String name) {
                        this.name = name;
                        return this;
                }

                public TeamBuilder withParticipants(Set<Participant> participants) {
                        this.participants = participants;
                        return this;
                }

                public TeamBuilder withRobots(Set<Robot> robots) {
                        this.robots = robots;
                        return this;
                }

            public TeamBuilder withUser(User user) {
                this.user = user;
                return this;
            }

                public TeamBuilder withVersion(int version) {
                        this.version = version;
                        return this;
                }

                public Team build() {
                        Team team = new Team();
                        team.setId(id);
                        team.setName(name);
                        team.setParticipants(participants);
                        team.setRobots(robots);
                    team.setUser(user);
                        team.setVersion(version);
                        return team;
                }
        }
}
