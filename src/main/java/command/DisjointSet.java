package command;

import model.Node;

public class DisjointSet {

    private static Node[] set = null;

    public static void init(int size) {
        set = new Node[size];
    }


}
