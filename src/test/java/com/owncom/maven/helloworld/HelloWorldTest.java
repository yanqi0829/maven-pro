package com.owncom.maven.helloworld;

import helloworld.HelloWorld;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {
    @Test
    public void testHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        String s = helloWorld.sayHello();
        assertEquals("Hello Maven", s);
    }
}
