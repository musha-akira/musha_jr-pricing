package com.example.demo.component.zhello;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class HelloRequest {

    @Autowired
    private HelloForm helloForm;

    @Autowired
    private AgeForm ageForm;

    @Data
    @Component
    public static class HelloForm{

        private String str;
    }

    public Hello getHello(){
        return new Hello(this.helloForm.getStr());
    }

    @Data
    @Component
    public static class AgeForm{
        private String age;
    }

    public Age getAge(){
        return Age.valueOf(this.ageForm.getAge());
    }

}
