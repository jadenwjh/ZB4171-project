package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Ufds {

    private static int[] sets;

    private static void get(int size) {
        if (sets == null) {
            sets = new int[size];
            HashSet<Integer> removed = Graph.getRemoved();
            for (int i = 0; i < sets.length; i++) {
                if (removed.contains(i)) {
                    sets[i] = -1;
                    continue;
                }
                sets[i] = i;
            }
        }
    }

    private static void union(int a, int b) {
        final int rankA = sets[a];
        final int rankB = sets[b];

        for (int i = 0; i < sets.length; i++) {
            if (sets[i] == rankB) {
                sets[i] = rankA;
            }
        }
    }

    public static void update() {
        int[][] graph = Graph.get();
        int size = Graph.getLength();
        clear();
        get(size);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (graph[r][c] == 1) {
                    union(r, c);
                }
            }
        }
    }

    public static ArrayList<HashSet<Integer>> getSets() {
        HashSet<Integer> ranks = new HashSet<>();
        for (int i = 0; i < sets.length; i++) {
            ranks.add(sets[i]);
        }
        ranks.remove(-1);
        ArrayList<HashSet<Integer>> out = new ArrayList<>();
        for (int rank : ranks) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < sets.length; j++) {
                if (sets[j] == rank) {
                    set.add(j);
                }
            }
            out.add(set);
        }
        return out;
    }

    public static String print() {
        return Arrays.toString(sets);
    }

    public static void clear() {
        sets = null;
    }
}
