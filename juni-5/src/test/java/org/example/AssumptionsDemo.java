package org.example;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionsDemo {

    private final Stack<String> stack = new Stack<>();

    @Test
    void popOnEmptyStack() {
        int size = stack.size();
        assumeTrue(size > 0, "Stack must not be empty.");
        stack.pop();
        assertEquals(size - 1, stack.size());
    }

    @Test
    void popOnNonEmptyStack() {
        stack.push("element");
        int size = stack.size();
        assumeTrue(size > 0, "Stack must not be empty.");
        stack.pop();
        assertEquals(size - 1, stack.size());
    }

    @Test
        // Execute the supplied executable if the assumption is true.
    void assumeThatDemo() {
        assumingThat(stack.size() > 0, stack::pop);
    }


}
