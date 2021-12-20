// 동적인 동작하는 프로그래밍 시작
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 이 메서드를 호출함
    public String hello(Model model) {
        model.addAttribute("data","hello!!"); // model 은 MVC 의 모델을 말함
        // 벨류 값이 변경되면 templates- hello.html의 $data 키의 벨류 값도 변경됨
        return "hello"; // [리턴을 쓰는 이유] templates 에서 hello 를 찾아 렌덩링 하고 이 화면을 실행 시켜라! 라는 것
    }
}
