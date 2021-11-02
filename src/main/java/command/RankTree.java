package command;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * For unit test
     * Key - Rank
     * Value - Parent | Children
     * Remove testSize after data is ready
     * @return
     */
    public static HashMap<Integer, List<ParentChildren>> rankTree(int testSize) {
        HashMap<Integer, List<ParentChildren>> output = new HashMap<>();
        int rank = 0;

        HashSet<Integer> candidates = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();
        while (Graph.getRemoved().size() < testSize) {
            Ufds.update();
            output.put(rank, new ArrayList<>());

            for (HashSet<Integer> set : Ufds.getSets()) {

                if (rank == 0) {
                    int rootNode = 0;
                    candidates.add(rootNode);
                } else {
                    HashSet<Integer> temp = new HashSet<>();
                    for (HashSet<Integer> n : neighbors.values()) {
                        temp.addAll(n);
                    }
                    temp.retainAll(set);
                    for (int node : FindCandidates.find(temp)) {
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

    public static HashMap<Integer, List<ParentChildren>> rankTree(String dataFile) {
        HashMap<Integer, List<ParentChildren>> output = new HashMap<>();
        int rank = 0;

        Log.init(dataFile);

        final int dataSize = Log.getNumber();

        System.out.println("Size: " + dataSize);

        Graph.get(dataSize);
        CreateGraph.createGraph();

        HashSet<Integer> candidates = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> neighbors = new HashMap<>();
        for (int k = 0; k < 10; k++) {
            Ufds.update();
            output.put(rank, new ArrayList<>());

            for (HashSet<Integer> set : Ufds.getSets()) {

                if (rank == 0) {
                    int rootNode = Log.getRoot();
                    candidates.add(rootNode);
                } else {
                    HashSet<Integer> temp = new HashSet<>();
                    for (HashSet<Integer> n : neighbors.values()) {
                        temp.addAll(n);
                    }
                    temp.retainAll(set);
                    for (int node : FindCandidates.find(temp)) {
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
