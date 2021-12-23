package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // assertThat 부분 스태틱해서 생김

class MemoryMemberRepositoryTest {  // 굳이 퍼플릭 안해도되서 뺌!
     MemoryMemberRepository repository = new MemoryMemberRepository();

     @Test
    public void save() {
         Member member = new Member(); // 내가 기대한 멤버가
         member.setName("spring");

         repository.save(member);      // 여기 파인드 했을때 튀어나와야함

         Member result = repository.findById(member.getId()).get();
         assertThat(member).isEqualTo(result);
         // 방식1 , 이걸 기억하자!
         // 스태틱으로 바꾸면 더 짧아짐!

//         방식2
//         Assertions.assertEquals(member, result);
//         // 둘이 같은지 확인하는 방법 (expected 익스펙터드 == 예상,기대하는 것)
//         // 즉, 기대한 값과 알맞는 코드를 넣으면 정상으로 표시됨(틀린 코드를 넣음 실제값과 아래 오류 표시로뜸)

//         방식3
//         System.out.println("result = " + (result == member));
//         // 리절트와 멤버가 같냐 => 같다고 밑에 뜸! / but, 이 방식으로 매번 문자 확인할 수 없으니 위에 방식 Assertions 로 함
//         // 옵션널 반환타입 이므로, 옵션널에서 값 꺼낼때는 뒤에 .get()을 넣어야 꺼낼 수 있다
//         // 겟으로 바로 꺼내는건 좋은 방법이 아님 그러나 테스트에서는 해도 됨~

     } // 세이브 기능 작성해 테스트로 실행

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // 단축키) member1에 쉬프트+F6 누르면 한번에 변경가능!
        member2.setName("spring2");
        repository.save(member2);      // 정교함을 위해 2까지 만들어주는 쎈쓰

        Member result = repository.findByName("spring1").get();// 단축키) result 부분 변경 옵션+컨트롤+V

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
