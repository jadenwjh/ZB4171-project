package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.Graph;
import model.Log;

public class FindCandidate {

    public static int find(HashSet<Integer> set, HashMap<Integer, HashSet<Integer>> parents) {
        if (set.size() == 1) {
            ArrayList<Integer> temp = new ArrayList<>(set);
            return temp.get(0);
        }

        HashSet<Integer> candidates = new HashSet<>();
        int highestRank = 0;
        for (int node : set) {
            int currentRank = Graph.getNeighbors(node).size();
            if (currentRank > highestRank) {
                highestRank = currentRank;
            }
        }
        for (int node : set) {
            if (Graph.getNeighbors(node).size() == highestRank) {
                candidates.add(node);
            }
        }

        // No tie, only 1 highest connected node in set
        ArrayList<Integer> temp = new ArrayList<>(candidates);
        if (temp.size() == 1) {
            return temp.get(0);
        }

        // Find candidates adjacent to their parent
        ArrayList<Integer> adjacent = new ArrayList<>();
        for (int candidate : candidates) {
            for (Map.Entry<Integer, HashSet<Integer>> entry : parents.entrySet()) {
                if (entry.getValue().contains(candidate)) {
                    int parent = entry.getKey();
                    if (Graph.getNeighbors(parent).contains(candidate)) {
                        adjacent.add(candidate);
                    }
                }
            }
        }

        // Tie, but all candidates are not adjacent with parent. Filter by cell count instead
        if (adjacent.isEmpty()) {
            int cellCount = -1;
            int finalCandidate = -1;
            for (int candidate : candidates) {
                int currentCount = Log.getCellCount(candidate);
                if (currentCount > cellCount) {
                    cellCount = currentCount;
                    finalCandidate = candidate;
                }
            }
            return finalCandidate;
        }

        // Tie, but there is only 1 candidate adjacent with parent
        if (adjacent.size() == 1) {
            return adjacent.get(0);
        }

        // Tie and some candidates are adjacent with parent. Filter these candidates by cell count
        int cellCount = -1;
        int finalCandidate = -1;
        for (int candidate : adjacent) {
            int currentCount = Log.getCellCount(candidate);
            if (currentCount > cellCount) {
                cellCount = currentCount;
                finalCandidate = candidate;
            }
        }

        return finalCandidate;
    }
}
