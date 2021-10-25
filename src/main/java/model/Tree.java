package model;

import com.mxgraph.view.mxGraph;

import java.util.HashMap;

public class Tree {

    private static mxGraph graph;

    private static HashMap<String, Object> log;

    public static mxGraph get() {
        if (graph == null) {
            graph = new mxGraph();
            log = new HashMap<>();
        }
        return graph;
    }

    public static boolean isEmptyLog() {
        if (log == null) {
            return true;
        }
        return log.isEmpty();
    }

    public static Object find(String id) {
        if (isEmptyLog()) {
            return graph.getDefaultParent();
        }
        return log.get(id);
    }

    public static void addLog(String id, Object o) {
        log.put(id, o);
    }
}
