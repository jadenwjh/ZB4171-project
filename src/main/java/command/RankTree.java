package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Graph;
import model.Log;
import model.ParentChildren;
import model.Ufds;

public class RankTree {

    static int testSize = 5;

    private static void initTestGraph() {
        // same as paper
        // 0 = 41
        // 1 = 20
        // 2 = 75
        // 3 = 86
        // 4 = 33
        Graph.clear();
        Graph.get(testSize);
        Graph.join(0, 1);
        Graph.join(0, 2);
        Graph.join(0, 3);
        Graph.join(0, 4);
        Graph.join(2, 3);
        Graph.join(3, 4);
    }

    /**
     * Key - Rank
     * Value - Parent | Children
     * @return
     */
    public static HashMap<Integer, List<ParentChildren>> rankTree() {
        HashMap<Integer, List<ParentChildren>> output = new HashMap<>();
        int rank = 0;

        //TODO: change once data is ready
        initTestGraph();

        HashSet<Integer> candidates = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();
        while (Graph.getRemoved().size() < testSize) {
            Ufds.update();
            output.put(rank, new ArrayList<>());

            for (HashSet<Integer> set : Ufds.getSets()) {

                if (rank == 0) {
                    int rootNode = 0; // Use Log when data is ready
                    candidates.add(rootNode);
                } else {
                    for (int node : FindCandidates.find(set)) {
                        for (Map.Entry<Integer, HashSet<Integer>> entry : neighbors.entrySet()) {
                            if (entry.getValue().contains(node)) {
                                candidates.add(node);
                                int parent = entry.getKey();
                                for (ParentChildren pc : output.get(rank - 1)) {
                                    if (pc.parent == parent) {
                                        pc.children.add(node);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int candidate : candidates) {
                neighbors.put(candidate, Graph.getNeighbors(candidate));
                output.get(rank).add(new ParentChildren(candidate, new HashSet<>()));
            }

            for (int candidate : candidates) {
                Graph.remove(candidate);
            }

            rank += 1;
            candidates.clear();
        }

        return output;
    }

    public static String print(HashMap<Integer, List<ParentChildren>> tree) {
        Set<Integer> ranks = tree.keySet();
        int maxRank = 0;
        for (int i : ranks) {
            if (i > maxRank) {
                maxRank = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int rank = 0; rank <= maxRank; rank++) {
            List<ParentChildren> pcs = tree.get(rank);
            for (ParentChildren pc : pcs) {
                sb.append("Rank ");
                sb.append(rank);
                sb.append(": ");
                sb.append(pc.print());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
