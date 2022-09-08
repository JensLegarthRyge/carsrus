package dat3.cars.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
    private String username;
    private int carId;
    private String date;
}
