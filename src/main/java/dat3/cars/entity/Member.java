package dat3.cars.entity;

import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "members")
@Getter
@Setter
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    @Column(length = 4)
    private String zip;
    private boolean approved;
    private String ranking;

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, String zip, boolean approved, String ranking) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    public Member(){}
}
