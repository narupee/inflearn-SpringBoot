// 동적인 동작하는 프로그래밍 시작
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 이 메서드를 호출함
    public String hello(Model model) {
        model.addAttribute("data","hello!!"); // model 은 MVC 의 모델을 말함
        // 벨류 값이 변경되면 templates- hello.html의 $data 키의 벨류 값도 변경됨
        return "hello"; // [리턴을 쓰는 이유] templates 에서 hello 를 찾아 렌덩링 하고 이 화면을 실행 시켜라! 라는 것
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // 외부에서 이름을 웹사이트로 url파라미터로 변경
        // model로 넘겨줌 == 모델에 담으면 View 에서 렌더링해서 쓰임!
        model.addAttribute("name", name); // 파라미터로 넘어온 name 이다
        return "hello-template";                       // model(키: name , 값: spring)
    } // localhost:8080/hello-mvc?name=spring 으로 치면 나옴! (정적일때는 변환X, 동적 html 변환 후 웹브라우저에 반환)
    // MVC 방식 정리
    //  - 뷰 찾고 템플릿 엔진을 통해 화면을 렌더링 후 HTML를 웹 브라우저에 넘겨줌

    // API 방식 (데이터 바로 넘김, 문자 방식)
    @GetMapping("hello-string")
    @ResponseBody // 얘가 진짜 중요!!! , ResponseBody란?: html의 body의 데이터를 직접 넣어준다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // "hello spring" , 요 문자의 뜻- 내가 요청한 클라이언트가 그대로 넘겨줌!
        // mvc 방식 하고는 다르며, 직관적인 친구인듯! 바로 직진행 하심! 내가 적은 그대로 나가는거!
    }

    // API 방식 (진심 중요한 부분은 요기부터!!!)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 커맨드+쉬프트+엔터 : code Completion (괄호 끝나고 ; 의 마무리를 해주는 단축키)
        hello.setName(name);
        return hello;
        // {"name":"spring!!!"} -> json(제이슨) 방식으로 나옴!
        //                          - 제이슨은 키-값 으로 이루어진 데이터 오브젝트를 전달
    } // 이건 문자가 아닌 "객체를 넣는 방식"

    static class Hello { // 객체로 만들어줌
        private String name; // Hello의 Key는 name, 값은 아까 넣은 스트링

        public String getName() {  // 커맨드+N : 겟터셋터 불르는 방식 단축키)
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
