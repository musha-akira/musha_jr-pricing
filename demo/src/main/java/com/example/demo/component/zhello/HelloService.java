package com.example.demo.component.zhello;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getHello(Hello hello){
        return hello.getStr() + "s";
    }
}
