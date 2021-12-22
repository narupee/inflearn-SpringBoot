package hello.hellospring.repository; // 회원 리포지토리(저장소) 인터페이스 만듬, 회원 객체 저장소 만듬

import hello.hellospring.domain.Member;  // 이 부분 바로 뜨게하는 단축키(1)

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장 기능 구현(회원 저장 == 저장소 저장) , 단축키(1): 옵션+엔터
    Optional<Member> findById(Long id); // 방금 만든 회원 아이디를 찾는 아이디를 넣음(fin~id , 아이디 또는 네임을 통해 찾음)
    Optional<Member> findByName(String name); // Optional : 자바8에 들어간 기능
    List<Member> findAll(); // 모든 회원 리스트를 찾아 반환
} // 4가지 기능 구현
