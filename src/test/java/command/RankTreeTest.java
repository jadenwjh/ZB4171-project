package command;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import model.ParentChildren;

import static org.junit.jupiter.api.Assertions.*;

class RankTreeTest {

    @Test
    void test() {
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
        Assert.assertEquals(sb.toString(), RankTree.print(RankTree.rankTree()));
    }
}