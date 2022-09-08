package dat3.cars.service;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void addReservation(ReservationRequest reservationRequest){
        Member member = memberRepository.findById(reservationRequest.getUsername()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found"));
        Car car = carRepository.findById(reservationRequest.getCarId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-MM-yyy");

        LocalDate date = LocalDate.parse(reservationRequest.getDate(),dateTimeFormatter);

        if (reservationRepository.existsByCar_idAndRentalDate(reservationRequest.getCarId(), date)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car already reserved");
        }

        Reservation reservation = new Reservation(date, car, member);
        reservationRepository.save(reservation);

    }

    public List<ReservationResponse> getReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().map(ReservationResponse::new).collect(Collectors.toList());
    }
}
