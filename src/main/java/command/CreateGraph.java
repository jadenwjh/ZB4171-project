package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Graph;
import model.Log;

public class CreateGraph {

    public static void createGraph() {
        ArrayList<String> edges1 = Log.getEdges1();
        ArrayList<String> edges2 = Log.getEdges2();
        final int size = edges1.size();
        for (int i = 0; i < size; i++) {
            int scar1 = Log.getId(edges1.get(i));
            int scar2 = Log.getId(edges2.get(i));
            Graph.join(scar1, scar2);
        }
    }
}
