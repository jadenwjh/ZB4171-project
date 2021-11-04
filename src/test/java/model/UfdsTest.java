package model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
class UfdsTest {

    @BeforeEach
    void setUp() {
        Graph.clear();
        Graph.init(5);
        Graph.join(0, 1);
        Graph.join(2, 3);
    }

    @Test
    void update() {
        Ufds.update();
        Assert.assertEquals("[0, 0, 2, 2, 4]", Ufds.print());
        Graph.join(0, 2);
        Ufds.update();
        Assert.assertEquals("[0, 0, 0, 0, 4]", Ufds.print());
        Graph.remove(0);
        Ufds.update();
        Assert.assertEquals("[-1, 1, 2, 2, 4]", Ufds.print());
    }


    @Test
    void getSets() {
        Ufds.update();
        Assert.assertEquals(3, Ufds.getSets().size());
        Graph.remove(0);
        Ufds.update();
        Assert.assertEquals(3, Ufds.getSets().size());
        Graph.remove(1);
        Ufds.update();
        Assert.assertEquals(2, Ufds.getSets().size());
    }
}