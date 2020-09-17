package com.example.demo.zhello

import com.example.demo.component.zhello.Hello
import com.example.demo.component.zhello.HelloController
import com.example.demo.component.zhello.HelloService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@SpringBootTest
class HelloTest extends Specification {

    def "api test hello"() {
        given:
        def target = new HelloController()
        //def hello = new Hello("hello")
        target.helloService = Mock(HelloService)
        target.helloService.getHello(str) >> str + "s"
        target.hello = Mock(Hello)
        target.hello.getHello() >> str
        target.hello.getAge() >> age
        def mockMvc = MockMvcBuilders.standaloneSetup(target).build()
        def getRequest =
                MockMvcRequestBuilders.get("/hello")


        when:
        def response = mockMvc.perform(getRequest)

        then:
        def actual = response.andReturn().getResponse().getContentAsString()
        assert actual == result

        where:

        str     |age| result
        "hello" |"s"| "helloss"
        "dd"    |"d"|  "ddsd"

    }

//    def "method test hello"() {
//        given:
//        def target = new HelloController()
//
//        when:
//        def response = target.hello()
//
//        then:
//        assert response == "Hello"
//    }
}
