package dat3.cars.configuration;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SetupDevUsers implements ApplicationRunner {


    UserWithRolesRepository userWithRolesRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        passwordUsedByAll = "test12";

    }

    @Override
    public void run(ApplicationArguments args) {
        ArrayList<Car> carsArray = new ArrayList<>(Arrays.asList(
                new Car ("Ford","Fiesta",400,50),
                new Car ("Mazda","Miata",550,40),
                new Car ("Volkswagen","Golf VII",699,30)
        )
        );

        ArrayList<Member> membersArray = new ArrayList<>(Arrays.asList(
                new Member("caravaggio","jens.l.ryge@pol.dk","hejjegersej123","Jens",
                        "Legarth Ryge","Valgaardsvej 7, 2 TH","Valby","2500",true,1
                ),
                new Member("hrforsting","johaforsting@gmail.com","elskeralt","Johannes",
                        "Forsting","Vetintagade 39, 1 MF","Frederiksberg","2000",false,2
                ),
                new Member("mbhnielsenxluna","mbhxnielsen@gmail.com","cookieluna123","Mads",
                        "Bøgh Højer Nielsen","Nokevej 1C, 5 TH","København N","2200",false,3
                )
        )
        );
        for (Member cm:membersArray) {
            System.out.println(cm);
            memberRepository.save(cm);
        }

        for (Car cc:carsArray) {
            carRepository.save(cc);
        }
    }

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
    }
}
