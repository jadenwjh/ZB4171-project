package model;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 1 == connected
 * 0 == disconnected
 */
public class Graph {

    private static int[][] graph;
    private static HashSet<Integer> removed;
    private static int length = 0;

    public static int[][] get(int size) {
        if (graph == null) {
            length = size;
            graph = new int[size][size];
            removed = new HashSet<>();
        }
        return graph;
    }

    protected static int[][] get() {
        return graph;
    }

    protected static int getLength() {
        return length;
    }

    public static void join(int r, int c) {
        int[][] g = get();
        g[r][c] = 1;
        g[c][r] = 1;
    }

    public static void disjoin(int r, int c) {
        int[][] g = get();
        g[r][c] = 0;
        g[c][r] = 0;
    }

    public static void remove(int i) {
        removed.add(i);
        for (int k = 0; k < length; k++) {
            disjoin(i, k);
            disjoin(k, i);
        }
    }

    public static HashSet<Integer> getRemoved() {
        return removed;
    }

    public static String print() {
        StringBuilder sb = new StringBuilder();
        int[][] g = get();
        for (int[] row : g) {
            sb.append(Arrays.toString(row));
        }
        return sb.toString();
    }

    public static void clear() {
        graph = null;
        length = 0;
    }
}
