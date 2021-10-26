package model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogTest {

    @BeforeEach
    void setUp() {
        Log.get(3);
    }

    @Test
    void test() {
        Assert.assertEquals("2 1 0 ", Log.print());
    }
}