package hello.hellospring.service;  // 회원 서비스 개발 로직

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {  // 단축키) 커맨드+쉬프트+T : 테스트를 자동으로 바로 만들어줌

    private final MemberRepository memberRepository; // 회원 리포리토지

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }/* 단축키) 커맨드+N : Constructor
        이건 외부에서 넣어주게 바꿔줌 == "DI : Dependency Injection(디펜던시 인젝션,의존성주입)" 이라함
        코드상 객체를 직접적으로 만드는게아닌 객체의 밖에서 객체를 넣어주는(주입) 방식이 의존성 주입
        여기 사용된 것 "생성자 이용" 가장 권장함!
        ( 다른 방식-> "Field변수, setter (이전까지의 권장 방식)" ) */

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member);   // 중복 회원 검증 , (단축키 옵션+커맨드+M) 해서 이렇게 변경
        memberRepository.save(member); // save 호출
        return member.getId(); // 임의로 Id 반환
    } // 조인 -> 중복회원검증 -> 통과하면 저장함

    // 같은 이름이 있는 중복 회원X
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
    // m(멤버)에 값이 있으면 throw
    // 널이 아닌 이미 값이 있음 동작함 (Optional 이기 때문에 가능한 것)
    // [추가] result.orElseGet : '값이 있음 꺼내고 없으면 여기 있는 메서드를 실행해'(디폴트 값을 꺼내) 라는 뜻
    // 메서드를 뽑는게 좋음 , 단축키) 옵션+T 누름 -> method 치면 -> Extract Method 나옴 (단축키 옵션+커맨드+M)

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
