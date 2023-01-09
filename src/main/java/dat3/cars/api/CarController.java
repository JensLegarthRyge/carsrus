package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Security ADMIN ???
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //Security USER ???
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getAllCars();
    }

    //Security USER ???
    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable Integer id) throws Exception {
        return carService.getCarById(id);
    }

    //Security ADMIN ???
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable Integer id){
        carService.editCar(body,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // Security ADMIN ????
    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable Integer id) {
        carService.deleteCarById(id);
    }
}
