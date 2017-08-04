package lv.challenge.domain.competitors;

import com.google.gson.annotations.Expose;
import lv.challenge.domain.DomainObject;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Daniil on 29.03.2017.
 */
@Entity(name = "Contact")
@Table(name = "contacts")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Component
@Scope("prototype")
public class Contact implements DomainObject {

    @Expose
    @Column(name = "phone")
    String phoneNumber;
    @Expose
    @Column(name = "email")
    String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Integer id;
    @Version
    int version;

    public Contact() {
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public static final class ContactBuilder {
        String phoneNumber;
        String email;
        Integer id;

        private ContactBuilder() {
        }

        public static ContactBuilder createContact() {
            return new ContactBuilder();
        }

        public ContactBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactBuilder withId(Integer id) {
            this.id = id;
            return this;
        }


        public Contact build() {
            Contact contact = new Contact();
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact.setId(id);
            return contact;
        }
    }
}

