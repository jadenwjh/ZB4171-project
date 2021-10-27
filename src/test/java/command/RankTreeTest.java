package command;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import model.Graph;
import model.ParentChildren;

import static org.junit.jupiter.api.Assertions.*;

class RankTreeTest {

    @BeforeEach
    void setUp() {
        Graph.clear();
    }

    @Test
    void paperExample() {
        // same as paper
        // 0 = 41
        // 1 = 20
        // 2 = 75
        // 3 = 86
        // 4 = 33
        Graph.get(5);
        Graph.join(0, 1);
        Graph.join(0, 2);
        Graph.join(0, 3);
        Graph.join(0, 4);
        Graph.join(2, 3);
        Graph.join(3, 4);

        StringBuilder sb = new StringBuilder();
        sb.append("Rank 0: 0 - [1, 3]");
        sb.append("\n");
        sb.append("Rank 1: 1 - []");
        sb.append("\n");
        sb.append("Rank 1: 3 - [2, 4]");
        sb.append("\n");
        sb.append("Rank 2: 2 - []");
        sb.append("\n");
        sb.append("Rank 2: 4 - []");
        sb.append("\n");
        Assert.assertEquals(sb.toString(), RankTree.print(RankTree.rankTree(5)));
    }

    @Test
    void parentWithMoreThanOneChildren() {
        Graph.get(6);
        Graph.join(0, 1);
        Graph.join(0, 2);
        Graph.join(0, 3);
        Graph.join(0, 4);
        Graph.join(0, 5);
        Graph.join(2, 3);
        Graph.join(3, 4);
        Graph.join(4, 5);

        StringBuilder sb = new StringBuilder();
        sb.append("Rank 0: 0 - [1, 3, 4]");
        sb.append("\n");
        sb.append("Rank 1: 1 - []");
        sb.append("\n");
        sb.append("Rank 1: 3 - [2]");
        sb.append("\n");
        sb.append("Rank 1: 4 - [5]");
        sb.append("\n");
        sb.append("Rank 2: 2 - []");
        sb.append("\n");
        sb.append("Rank 2: 5 - []");
        sb.append("\n");
        Assert.assertEquals(sb.toString(), RankTree.print(RankTree.rankTree(6)));
    }
}