package command;

import java.util.HashSet;

import model.Graph;

public class FindCandidates {

    public static HashSet<Integer> find(HashSet<Integer> neighbors) {
        HashSet<Integer> candidates = new HashSet<>();
        int highestRank = 0;
        for (int node : neighbors) {
            int currentRank = Graph.getNeighbors(node).size();
            if (currentRank > highestRank) {
                highestRank = currentRank;
            }
        }
        for (int node : neighbors) {
            if (Graph.getNeighbors(node).size() == highestRank) {
                candidates.add(node);
            }
        }
        return candidates;
    }
}
