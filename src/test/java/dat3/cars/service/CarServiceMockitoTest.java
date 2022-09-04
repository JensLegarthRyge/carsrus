package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;




@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {
    @Autowired
    CarService carService;
    @Mock
    CarRepository carRepository;

    @BeforeEach
    public void setup(){
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() throws Exception {
        Car c1 = new Car(1,"b1","m1",100,50);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(c1);

        CarRequest request = new CarRequest(c1);
        CarResponse response = carService.addCar(request);

        assertEquals("b1",response.getBrand());
    }

    @Test
    void getAllCars() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(
                new Car(1,"b1","m1",100,50),
                new Car(2,"b2","m2",300,20)
        ));

        List<CarResponse> cars = carService.getAllCars();
        assertEquals(2,cars.size());
    }

    @Test
    void getCarById() throws Exception {
        Car c1 = new Car(1,"b1","m1",100,50);
        Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(c1));

        CarResponse response = carService.getCarById(1);
        assertEquals(100,response.getPricePrDay());
    }

    @Test
    void editCar() throws Exception {
        Car c1 = new Car(1,"b1","m1",100,50);
        Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(c1));

        CarResponse response = carService.getCarById(1);
        response.setModel("m2");
        assertEquals("m2",response.getModel());

    }
}