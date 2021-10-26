package model;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 1 == connected
 * 0 == disconnected
 * -1 == removed
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

    public static int remove(int i) {
        removed.add(i);
        int[][] g = get();
        for (int k = 0; k < length; k++) {
            g[i][k] = -1;
            g[k][i] = -1;
        }
        return i;
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

    public static HashSet<Integer> getNeighbors(int key) {
        int[][] g = get();
        HashSet<Integer> neighbors = new HashSet<>();
        for (int i = 0; i < length; i++) {
            if (g[key][i] == 1 && key != i) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }
}
