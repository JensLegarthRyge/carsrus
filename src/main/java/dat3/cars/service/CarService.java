package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponse addCar(CarRequest carRequest){
        if (carRepository.existsById(carRequest.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exists");
        }
        Car newCar = CarRequest.getCarEntity(carRequest);
        carRepository.save(newCar);

        return new CarResponse(newCar);
    }

    public List<CarResponse> getAllCars(){
        List<Car> allCars = carRepository.findAll();
        return allCars.stream().map(car -> new CarResponse(car)).collect(Collectors.toList());
    }

    public CarResponse getCarById(Integer id) throws Exception{
        Car carFound = carRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with ID: "+id+" does not exist"));
        return new CarResponse(carFound);

    }

    public void editCar(CarRequest body, Integer id){
        Car carToEdit = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with ID does not exist"));

        carToEdit.setPricePrDay(body.getPricePrDay());
        carToEdit.setBrand(body.getBrand());
        carToEdit.setModel(body.getModel());
        carToEdit.setBestDiscount(body.getBestDiscount());

        carRepository.save(carToEdit);
    }

    public void deleteCarById(Integer id){
        carRepository.deleteById(id);
    }
}
