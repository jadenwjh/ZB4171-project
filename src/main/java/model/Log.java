package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Log {

    private static class Node {
        final int key;
        final int id;
        final int rank;

        Node(int key, int id, int rank) {
            this.key = key;
            this.id = id;
            this.rank = rank;
        }
    }

    private static ArrayList<Node> log;

    public static ArrayList<Node> get(int size) {
        if (log == null) {
            log = new ArrayList<>();
            init(size);
        }
        return log;
    }

    /**
     * Place holder
     * @param size number of nodes
     */
    private static void init(int size) {
        for (int i = 0; i < size; i++) {
            log.add(new Node(i, i, i));
        }
        log.sort(new Comparator<Node>() {
            @Override
            public int compare(Node lhs, Node rhs) {
                return Integer.compare(rhs.rank, lhs.rank);
            }
        });
    }

    public static String print() {
        StringBuilder sb = new StringBuilder();
        for (Node n : log) {
            sb.append(n.key);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void clear() {
        log = null;
    }
}
