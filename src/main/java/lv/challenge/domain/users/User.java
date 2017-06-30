package lv.challenge.domain.users;

import com.google.gson.annotations.Expose;
import lv.challenge.domain.DomainObject;
import lv.challenge.domain.competitors.Contact;
import lv.challenge.domain.competitors.Team;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Daniil on 16.04.2017.
 */
@Entity(name = "User")
@Table(name = "users", schema = "registration")
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class User implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "organisation")
    private String organisation;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "surname")
    private String surname;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "user")
    @JoinColumn(name = "teamid")
    private Team team;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "contactid")
    private Contact contact;
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private Set<UserRole> roles;
    @Column(name = "enabled")
    boolean enabled;
    @Version
    private int version;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public static final class UserBuilder {
        boolean enabled;
        private String login;
        private String password;
        private String organisation;
        private String state;
        private String city;
        private String name;
        private String surname;
        private Team team;
        private Contact contact;
        private Set<UserRole> roles;

        private UserBuilder() {
        }

        public static UserBuilder createUser() {
            return new UserBuilder();
        }

        public UserBuilder withLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withOrganisation(String organisation) {
            this.organisation = organisation;
            return this;
        }

        public UserBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public UserBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withTeam(Team team) {
            this.team = team;
            return this;
        }

        public UserBuilder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public UserBuilder withRoles(Set<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setOrganisation(organisation);
            user.setState(state);
            user.setCity(city);
            user.setRoles(roles);
            user.setEnabled(enabled);
            user.name = this.name;
            user.contact = this.contact;
            user.team = this.team;
            user.surname = this.surname;
            return user;
        }
    }
}
