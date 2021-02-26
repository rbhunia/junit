package org.example;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifeCycleWithPerClassTest {

    public LifeCycleWithPerClassTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @Test
    void test3() {
        System.out.println("test3");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll
    void afterAll() {
        System.out.println("afterAll");
    }
}
