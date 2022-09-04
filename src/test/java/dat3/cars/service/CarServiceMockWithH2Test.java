package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceMockWithH2Test {

    public CarService carService;

    public static CarRepository carRepository;


    @BeforeAll
    public static void setupData(@Autowired CarRepository car_Repository){
        carRepository = car_Repository;
        List<Car> cars = List.of(new Car(4,"b1","m1",100,10),
                new Car(5,"b2","m2",200,20)
            );
        carRepository.saveAll(cars);
    }

    @BeforeEach
    public void setCarService(){
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() {
        CarRequest request = new CarRequest(new Car(6,"b3","m3",300,30));
        carService.addCar(request);
        assertEquals(6,carRepository.count());
    }

    @Test
    void getAllCars() {
        List<CarResponse> responses = carService.getAllCars();
        assertEquals(5,responses.size());
    }

    @Test
    void getCarById() {
        CarRequest request = new CarRequest(carRepository.findById(4).get());
        assertEquals("m1",request.getModel());
    }
}