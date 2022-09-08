package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "members")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    @Column(length = 4)
    private String zip;
    private boolean approved;
    private int ranking;
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation res){
        reservations.add(res);
        res.setMember(this);
    }

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, String zip, boolean approved, int ranking) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", approved=" + approved +
                ", ranking=" + ranking +
                "} " + super.toString();
    }

    public Member(String user, String password, String email, String firstName, String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
