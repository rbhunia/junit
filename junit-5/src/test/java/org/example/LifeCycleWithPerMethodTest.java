package org.example;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class LifeCycleWithPerMethodTest {

    public LifeCycleWithPerMethodTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    @Disabled
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
    static void afterAll() {
        System.out.println("afterAll");
    }
}
