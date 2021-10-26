package model;

import java.util.HashSet;

public class ParentChildren {

    public final int parent;
    public HashSet<Integer> children;

    public ParentChildren(int parent, HashSet<Integer> children) {
        this.parent = parent;
        this.children = children;
    }

    /**
     * "1 - [0, 2, 1]"
     * @return
     */
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(parent);
        sb.append(" - ");
        sb.append(children.toString());
        return sb.toString();
    }
}
