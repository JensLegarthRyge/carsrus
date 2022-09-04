package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    static ArrayList<Integer> carIds = new ArrayList<>();

    @BeforeAll
    public static void initializeData(@Autowired CarRepository carRepository) {
        ArrayList<Car> carsArray = new ArrayList<>(Arrays.asList(
                new Car("Ford", "Fiesta", 400, 50),
                new Car("Mazda", "Miata", 550, 40),
                new Car("Volkswagen", "Golf VII", 699, 30)
            )
        );

        for (int i = 0; i < carsArray.size(); i++) {
            carRepository.save(carsArray.get(i));
            carIds.add(carsArray.get(i).getId());
        }
    }

    @Test
    public void testFindCarById() {
        Car found = carRepository.findById(carIds.get(0)).get();
        assertEquals(carIds.get(0),found.getId());
        assertEquals("Ford",found.getBrand());
        assertEquals(400,found.getPricePrDay());
    }
}