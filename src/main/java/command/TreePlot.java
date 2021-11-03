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

        HashMap<Integer, Object> nodes = new HashMap<>();

        mxGraph graph = new mxGraph();
        Object p = graph.getDefaultParent();
        graph.getModel().beginUpdate();

        while (tree.get(rank) != null) {
            for (ParentChildren pc : tree.get(rank)) {
                int parent = pc.parent;
                HashSet<Integer> children = pc.children;

                Object parentNode = null;
                if (rank == 0) {
                    parentNode = graph.insertVertex(p, null, Log.getScar(parent), 0, 0, nodeWidth, nodeHeight);
                    nodes.put(parent, parentNode);
                } else {
                    parentNode = nodes.get(parent);
                }

                for (int child : children) {
                    Object childNode = graph.insertVertex(p, null, Log.getScar(child), 0, 0, nodeWidth, nodeHeight);
                    nodes.put(child, childNode);
                    graph.insertEdge(p, null, null, parentNode, childNode);
                }
            }

            rank++;
        }

        graph.getModel().endUpdate();

        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setInterRankCellSpacing(nodeHeight * rank);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(graphComponent, BorderLayout.CENTER);
        layout.execute(graph.getDefaultParent());
    }
}
