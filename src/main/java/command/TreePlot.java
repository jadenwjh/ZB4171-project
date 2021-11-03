package command;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.BorderLayout;
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
                    parentNode = graph.insertVertex(p, null, parent, width, height, nodeWidth, nodeHeight);
                    nodes.put(parent, parentNode);
                } else {
                    parentNode = nodes.get(parent);
                }

                height += (15 * (children.size() + 1));
                for (int child : children) {
                    Object childNode = graph.insertVertex(p, null, child, width, height, nodeWidth, nodeHeight);
                    nodes.put(child, childNode);
                    graph.insertEdge(p, null, rank, parentNode, childNode);
                    width += nodeWidth + 20;
                }
            }

            rank++;
        }

        graph.getModel().endUpdate();

        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(graph.getDefaultParent());

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
    }
}
