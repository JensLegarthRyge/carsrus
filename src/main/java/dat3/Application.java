package dat3;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

//Remove the exclude when/if security is needed
@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
