package model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @BeforeEach
    void setUp() {
        Graph.clear();
    }

    @Test
    void init() {
        Graph.init(3);
        Assert.assertEquals("[0, 0, 0][0, 0, 0][0, 0, 0]", Graph.print());
        Assert.assertEquals(3, Graph.getLength());
    }

    @Test
    void join() {
        Graph.init(3);
        Graph.join(1,2);
        Assert.assertEquals("[0, 0, 0][0, 0, 1][0, 1, 0]", Graph.print());
    }

    @Test
    void remove() {
        Graph.init(3);
        Graph.join(0,1);
        Graph.join(1,2);
        Assert.assertEquals(0, Graph.getRemoved().size());
        Graph.remove(1);
        Assert.assertEquals("[0, -1, 0][-1, -1, -1][0, -1, 0]", Graph.print());
        Assert.assertEquals(1, Graph.getRemoved().size());
    }

    @Test
    void neighbors() {
        Graph.init(3);
        Graph.join(1,2);
        Assert.assertEquals(1, Graph.getNeighbors(2).size());
        Graph.join(1,0);
        Assert.assertEquals(2, Graph.getNeighbors(1).size());
        Assert.assertEquals(1, Graph.getNeighbors(2).size());
    }
}