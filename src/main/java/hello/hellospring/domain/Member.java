package hello.hellospring.domain;  // 회원관리 예제 시작 (회원 도메인, 리포지토리 만들기)

public class Member {

    private Long id;     // 아이디 식별자 (임의의 값, 고객이 아닌 시스템이 정하는 id(데이터 구분을 위함))
    private String name; // 이름

    public Long getId() {  // 단순하게 겟또셋또를 넣어서 만듬~
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
