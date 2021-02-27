package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer.Builder(Gender.MALE, "Raj", "Bhunia")
                .withMiddleName("Kumar")
                .withBecomeCustomer(LocalDate.of(2011, Month.JUNE, 27))
                .build();
    }

    @Test
    void testWithoutSupplier() {
        assertEquals("This is a String.", getCompleteString(), getErrorMessage());
    }

    @Test
        // Use this when you want to defer the creation of message string in case it is expansive to create.
    void testWithSupplier() {
        assertEquals("This is a String.", getCompleteString(), this::getErrorMessage);
    }

    @Test
        // assertAll is useful when test is performed on a single domain object.
    void assertAllTest() {
        assertAll("customer_test",
                () -> assertEquals("Raj", customer.getFirstName()),
                () -> assertEquals("Kumar", customer.getMiddleName()),
                () -> assertEquals("Bhunia", customer.getLastName()),
                () -> {
                    LocalDate joiningDate = LocalDate.of(2011, Month.JUNE, 27);
                    assertEquals(joiningDate, customer.getBecomeCustomer());
                }
        );
    }

    @Test
    void complexAssertAllTest() {
        assertAll("customer_test",
                () -> assertEquals("Raj", customer.getFirstName()),
                () -> {
                    assertNotNull(customer.getMiddleName());
                    var middleName = customer.getMiddleName();
                    assertAll("middle_name_test",
                            () -> assertTrue(middleName.startsWith("K")),
                            () -> assertEquals(5, customer.getMiddleName().length()),
                            () -> assertTrue(middleName.endsWith("r"))
                    );
                },
                () -> {
                    LocalDate joiningDate = LocalDate.of(2011, Month.JUNE, 27);
                    assertEquals(joiningDate, customer.getBecomeCustomer());
                }
        );
    }

    @Test
        // The problem with Juni4 was any code throwing ArrayIndexOutOfBoundsException passed the test
    void testExceptionWithoutMethodReference() {
        List<String> strings = Arrays.asList("This", "is", "a", "list", "of", "string");
        var exception = assertThrows(IndexOutOfBoundsException.class,
                () -> strings.get(99));
        assertThat(exception.getMessage(), containsString("99"));
    }


    @Test
    void assertTimeOutOkay() {
        assertTimeout(Duration.ofMillis(100), () -> System.out.println("Hello"));
    }

    @Test
    @Disabled("Enable this during demo.")
    void assertTimeOutDelayed() {
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(200));
    }

    @Test
    @Disabled("Enable this during demo.")
    void assertTimeoutPreemptive() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(200));
    }

    String getCompleteString() {
        System.out.println("Inside getCompleteString method.");
        return "This " + "is " + "a " + "String.";
    }

    String getErrorMessage() {
        System.out.println("Inside getErrorMessage method.");
        return "This should never happen.";
    }
}
