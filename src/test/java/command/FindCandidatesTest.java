package command;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import model.Graph;

import static org.junit.jupiter.api.Assertions.*;

class FindCandidatesTest {

    @BeforeEach
    void setUp() {
        Graph.clear();
        Graph.get(5);
        Graph.join(0, 1);
        Graph.join(0, 2);
        Graph.join(0, 3);
        Graph.join(0, 4);
        Graph.join(3, 4);
        Graph.join(3, 2);
        Graph.join(1, 2);
    }

    @Test
    void test() {
        HashSet<Integer> candidates = FindCandidates.find(Graph.getNeighbors(0));
        Assert.assertTrue(candidates.contains(2));
        Assert.assertTrue(candidates.contains(3));
        Assert.assertFalse(candidates.contains(1));
        Assert.assertFalse(candidates.contains(4));
        Assert.assertFalse(candidates.contains(0));
    }
}