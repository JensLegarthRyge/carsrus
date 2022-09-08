package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime reservationDate;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    //For displaying standalone reservationRequests
    private String username;
    private int carId;
    private String carBrand;

    //For displaying standalone reservationRequests
    public ReservationResponse(Reservation reservation) {
        this.username = reservation.getMember().getUsername();
        this.id = reservation.getId();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
        this.rentalDate = reservation.getRentalDate();
    }
}
