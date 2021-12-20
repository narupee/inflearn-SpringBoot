package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 어노테이션
public class HelloSpringApplication {

	public static void main(String[] args) { // 자바는 기본으로 main 으로 부터 메서드 실행
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
