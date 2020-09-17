package com.example.demo.component.zhello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

//    @Autowired
//    Hello hello;
    @Autowired
    HelloRequest request;

    @ModelAttribute
    public HelloRequest createRequest(){
        return this.request;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(HelloRequest request) {
        String s = request.getHello().getStr() + "s";
        return "hello";
    }

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String input(Model model){
//        // 空のフォームオブジェクトをModelに設定
//        model.addAttribute("helloRequest", new HelloRequest.HelloForm());
//        // 遷移先を返す(この場合はinput.htmlが遷移先となる)
//        return "hello";
//    }

    @RequestMapping(value = "/helloInfo", method = RequestMethod.POST)
    public String sayHello(HelloRequest request) {
        String s = request.getHello().getStr() + "s";
        System.out.println(s);
        System.out.println(request.getAge());
        if (s.equals("ss")){
            return "helloInfo";
        } else {
            return "hello";
        }
    }

//    @RequestMapping(value = "/helloInfo", method = RequestMethod.POST)
//    public String confirm() {
//        // 遷移先を返す(この場合はconfirm.htmlが遷移先となる)
//        return "helloInfo";
//    }
}