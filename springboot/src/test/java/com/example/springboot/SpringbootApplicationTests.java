package com.example.springboot;

import com.example.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    private Person person;
    @Test//测试yaml赋值
    void contextLoads() {
        System.out.println(person);
    }

}
