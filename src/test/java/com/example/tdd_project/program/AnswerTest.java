package com.example.tdd_project.program;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AnswerTest {

    @Test
    public void matchAgainstNullAnswerReturnFalse() {
        assertFalse(new Answer(new BooleanQuestion(0, ""), Bool.TRUE).match(null));
    }
}
