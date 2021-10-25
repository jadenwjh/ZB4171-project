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
        Graph.get(3);
        Assert.assertEquals("[0, 0, 0][0, 0, 0][0, 0, 0]", Graph.print());
        Assert.assertEquals(3, Graph.getLength());
    }

    @Test
    void join() {
        Graph.get(3);
        Graph.join(1,2);
        Assert.assertEquals("[0, 0, 0][0, 0, 1][0, 1, 0]", Graph.print());
    }

    @Test
    void remove() {
        Graph.get(3);
        Graph.join(0,1);
        Graph.join(1,2);
        Assert.assertEquals(0, Graph.getRemoved().size());
        Graph.remove(1);
        Assert.assertEquals("[0, -1, 0][-1, -1, -1][0, -1, 0]", Graph.print());
        Assert.assertEquals(1, Graph.getRemoved().size());
    }
}