package model;

import java.util.HashMap;
import java.util.HashSet;

public class Neighbors {

    private static HashMap<Integer, HashSet<Integer>> log;

    public static HashMap<Integer, HashSet<Integer>> get() {
        if (log == null) {
            int size = Graph.getLength();
            log = new HashMap<>();
            for (int i = 0; i < size; i++) {
                log.put(i, new HashSet<>());
            }
        }
        return log;
    }

    public static void update() {
        int[][] graph = Graph.get();
        int size = Graph.getLength();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (graph[r][c] == 1) {
                    add(r, c);
                    add(c, r);
                }
            }
        }
    }

    private static void add(int node, int neighbor) {
        get().get(node).add(neighbor);
    }

    public static HashSet<Integer> getNeighbors(int key) {
        return get().get(key);
    }

    public static void clear() {
        log = null;
    }
}
