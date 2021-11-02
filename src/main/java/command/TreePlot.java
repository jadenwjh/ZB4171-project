package command;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;

import model.Log;
import model.ParentChildren;

public class TreePlot extends JFrame {

    private static final int nodeWidth = 130;
    private static final int nodeHeight = 20;

    public TreePlot (HashMap<Integer, List<ParentChildren>> tree) {
        int rank = 0;
        int height = 10;
        int width = 80;

        HashMap<Integer, Object> nodes = new HashMap<>();

        mxGraph graph = new mxGraph();
        Object p = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        while (tree.get(rank) != null) {
            width = 80;
            for (ParentChildren pc : tree.get(rank)) {
                int parent = pc.parent;
                HashSet<Integer> children = pc.children;

                Object parentNode = null;
                if (rank == 0) {
                    parentNode = graph.insertVertex(p, null, Log.getScar(parent), width, height, nodeWidth, nodeHeight);
                    nodes.put(parent, parentNode);
                } else {
                    parentNode = nodes.get(parent);
                }

                height += (15 * (children.size() + 1));
                for (int child : children) {
                    Object childNode = graph.insertVertex(p, null, Log.getScar(child), width, height, nodeWidth, nodeHeight);
                    nodes.put(child, childNode);
                    graph.insertEdge(p, null, rank, parentNode, childNode);
                    width += nodeWidth + 20;
                }
            }

            rank++;
        }


        graph.getModel().endUpdate();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }
}