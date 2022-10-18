package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        /*
        컨트롤러에서 리턴 값으로 String을 반환하면 viewResolver가 화면을 찾아서 처리한다.
        스프링 부트 템플릿엔진 기본 viewName 매핑
        resources:templates/ + {ViewName} + .html
         */
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        /*
        @RequestParam의 속성값 중 required의 default 값은 true
         */
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
        // html 파일이 아닌 hello + name 문자열 그대로 반환
    }

    @GetMapping("hello-api")
    @ResponseBody
    /*
    @Response 사용 시 HttpMessageConverter가 동작
    기본 문자 처리 : StringHttpMessageConverter
    기본 객체 처리 : MappingJackson2HttpMessageConverter
     */
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 객체로 반환시 JSON 객체 형태로 반환
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
