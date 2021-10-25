package model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighborsTest {

    @BeforeEach
    void setUp() {
        Graph.clear();
        Graph.get(5);
        Graph.join(0, 1);
        Graph.join(2, 3);
        Neighbors.clear();
    }

    @Test
    void test() {
        Neighbors.update();
        Assert.assertTrue(Neighbors.getNeighbors(0).contains(1));
        Assert.assertTrue(Neighbors.getNeighbors(3).contains(2));
        Assert.assertFalse(Neighbors.getNeighbors(0).contains(3));
        Graph.join(1, 2);
        Neighbors.update();
        Assert.assertTrue(Neighbors.getNeighbors(0).contains(1));
        Assert.assertTrue(Neighbors.getNeighbors(1).contains(2));
        Assert.assertFalse(Neighbors.getNeighbors(0).contains(3));
    }

}