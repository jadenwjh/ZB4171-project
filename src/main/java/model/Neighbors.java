package model;

import java.util.HashMap;
import java.util.HashSet;

public class Neighbors {

    private static HashMap<Integer, HashSet<Integer>> log;

    public static HashMap<Integer, HashSet<Integer>> get() {
        if (log == null) {
            log = new HashMap<>();
        }
        return log;
    }
}
