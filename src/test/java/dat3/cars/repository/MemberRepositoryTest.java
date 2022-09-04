package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    static ArrayList<String> memberUsernames = new ArrayList<>();

    @BeforeAll
    public static void initializeData(@Autowired MemberRepository memberRepository){
        ArrayList<Member> membersArray = new ArrayList<>(Arrays.asList(
                new Member("caravaggio","jens.l.ryge@pol.dk","hejjegersej123","Jens",
                        "Legarth Ryge","Valgaardsvej 7, 2 TH","Valby","2500",true,1
                ),
                new Member("hrforsting","johaforsting@gmail.com","elskeralt","Johannes",
                        "Forsting","Vetintagade 39, 1 MF","Frederiksberg","2000",false,2
                )
            )
        );

        for (int i=0; i<membersArray.size(); i++){
            memberRepository.save(membersArray.get(i));
            memberUsernames.add(membersArray.get(i).getUsername());
        }
    }

    @Test
    public void testFindMemberById(){
        Member found = memberRepository.findById(memberUsernames.get(0)).get();
        assertEquals(memberUsernames.get(0), found.getUsername());
        assertEquals("caravaggio",found.getUsername());
    }

    @Test
    public void testDeleteMemberById(){
        memberRepository.deleteById(memberUsernames.get(0));
        assertEquals(memberRepository.count(),1);
    }
}