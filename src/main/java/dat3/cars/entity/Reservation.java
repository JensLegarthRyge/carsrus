package dat3.cars.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @Builder @AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private LocalDateTime reservationDate;
    private LocalDate rentalDate;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Car car;

    public Reservation(LocalDate rentalDate, Car car, Member member) {
        this.member = member;
        this.rentalDate = rentalDate;
        this.car = car;
    }
}
