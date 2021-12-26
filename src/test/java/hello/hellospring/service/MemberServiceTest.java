package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {    // 단축키) 컨트롤+R : 이전 톰캣실행 해줌

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // 전체 테스트를 하기 위해 클리어를 해줄려면 이렇게 해야함, 방식3

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();     // 같은 메모리멤버레포지토리를 사용
        memberService = new MemberService(memberRepository);
    } // 테스터는 각각 독립성으로 실행되어야 하기 때문에 위 처럼 각각 실행해준다
      // "DI : Dependency Injection(디펜던시 인젝션,의존성주입)"

    @AfterEach // 테스트 끝나고 깔끔하게 지워주는 코드(디비의 값을 날려줌), 방식3
    public void aftreEach() {
        memberRepository.clearStore();
    }
    // 즉, 방식3을 한 이유는 회원가입에서 헬로를 예외처리에서는 스프링으로 넣어줬기 때문이다

    @Test
    void 회원가입() { // 테스트는 한글로 바꿔도됨(테스트 빌드될 때 한글은 포함X)
        // given (주어진, "이 데이터를 기반으로 하는구나")
        Member member = new Member();
        member.setName("hello");

        // when (이것을 실행했을때, "이걸 검증하는구나")
        Long saveId = memberService.join(member);

        // then(결과가 나와야해, "여기가 검증 부분이구나" 이런식으로 알 수 있다!!)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 테스트는 예외가 중요
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 메세지 검증하는 방식은 이렇게 변경됨
        // e부분 변경 : "옵션+커맨드+V"를 눌러서 글 수정

/*방식1  assertThrows(IllegalStateException.class, () -> memberService.join(member2))   // 메세지 검증 전 방식
        // () -> : "람다 방식"의 로직을 실행할꺼야~ 그럼 멤버2 예외가(Illeg~tion) 터져야해!
*/      //깔끔 전체 주석 단축키) 옵션+커맨드+슬래쉬

/*방식2    try {
            memberService.join(member2);     // 여기 섹션이 터지면 catch로 감
            fail(); // "예외가 발생해야 합니다."
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        } // catch로 오면 얘가 터짐, 정상적으로 성공한 것
*/

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}