package com.example.springboot;

import com.example.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    private Person person;
    @Test//测试yaml赋值
    void contextLoads() {
        System.out.println(person);
    }

    @Test//测试数据源
    void testDataSource() throws SQLException {
        //查看默认数据源  com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());//class com.zaxxer.hikari.HikariDataSource
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

}
