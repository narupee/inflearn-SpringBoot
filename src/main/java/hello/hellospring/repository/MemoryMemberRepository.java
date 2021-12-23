package hello.hellospring.repository; // 구현체 만듬

import hello.hellospring.domain.Member;

import java.sql.Array;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository { // 임포트(옵션+엔터 해서 가져오기)

    private static Map<Long, Member> store = new HashMap<>(); // 저장: Map을 만들었음! (키: 로그, 값: 멤버)
    private static long sequence = 0L; // 시퀀스: 0,1,2 키 캆을 생성 해주는 것(동시성 문제로 원래 다르게 해줌, 지금은 배우는거니까 이렇게함)

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 시퀀스 값을 하나 올려줌, Id값은 이미 넘어온 상태
        store.put(member.getId(), member); // 스토어에 넣기전 멤버에 아이디 값을 세팅해줌 -> 스토어 저장함(맵에 저장됨)
        return member; // 저장된 값 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Nall값 이여도 감싸서 반환 ->클라이언트에서 뭘 할 수 있음(뒤에 설명 있음)
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 같은 것을 찾으면 반환
                .filter(member -> member.getName().equals(name)) // 람다 사용, equals:파라미터로 넘어온 네임과 겟네임이 같은지 확인
                                                                 // -> 같은 경우에만 필터링 됨
                .findAny(); // 하나로도 찾는 것을 뜻함
    } // 하나라도 찾는 것이 없음 옵션널에서 널값으로 반환된뎅~

    @Override
    public List<Member> findAll() { // 자바 실무에서 리스트 많이 쓰임!!!
        return new ArrayList<>(store.values()); // 스토어에 있는 벨류(멤버) 반환
    }
}
// 구현 끝
// 동작 검증 방법 -> "테스트 케이스 작성" 하는 것 !!!
// 자바는 JUnit(제이유닛) 프레임워크로 테스트를 실행(실행시간,반복실행,여러 테스트를 한번에 실행하는 불가한 문제를 해결)