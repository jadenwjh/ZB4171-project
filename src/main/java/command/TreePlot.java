package command;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JFrame;

import model.Graph;
import model.Log;
import model.Ufds;

public class TreePlot extends JFrame {

    private static final int nodeWidth = 130;
    private static final int nodeHeight = 20;

    public TreePlot (String dataFile, int space, int threshold) {
        Log.init(dataFile);
        final int dataSize = Log.getNumber();
        System.out.println("Number of nodes: " + dataSize);
        Graph.init(dataSize);
        CreateGraph.createGraph();

        mxGraph graph = new mxGraph();
        Object graphDefaultParent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        Object rootNode = null;

        HashMap<Integer, HashSet<Integer>> parents = new HashMap<>();
        HashMap<Integer, Object> nodes = new HashMap<>();
        while (Graph.getRemoved().size() < dataSize) {
            Ufds.update();

            for (HashSet<Integer> set : Ufds.getSets()) {
                if (rootNode == null) {
                    int root = FindCandidate.find(set, parents);
                    parents.put(root, set);
                    rootNode = graph.insertVertex(graphDefaultParent, null, Log.getScar(root).equals("0") ? "Root" : Log.getScar(root), 0, 0, nodeWidth, nodeHeight);
                    nodes.put(root, rootNode);
                    Graph.remove(root);
                } else {
                    int node = FindCandidate.find(set, parents);

                    if (Log.getCellCount(node) < threshold) {
                        Graph.remove(node);
                        continue;
                    }

                    // Find the smallest set (most recently formed subtree with its own root as the parent) containing this node
                    int smallestSetSize = Integer.MAX_VALUE;
                    int parent = -1;
                    for (Map.Entry<Integer, HashSet<Integer>> entry : parents.entrySet()) {
                        if (entry.getValue().contains(node) && entry.getValue().size() < smallestSetSize) {
                            parent = entry.getKey();
                            smallestSetSize = entry.getValue().size();
                        }
                    }
                    Object currentNode = graph.insertVertex(graphDefaultParent, null, Log.getScar(node), 0, 0, nodeWidth, nodeHeight);
                    graph.insertEdge(graphDefaultParent, null, null, nodes.get(parent), currentNode);
                    nodes.put(node, currentNode);
                    parents.put(node, set);
                    Graph.remove(node);
                }
            }
        }

        graph.getModel().endUpdate();

        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setInterRankCellSpacing(space);
        layout.execute(graphDefaultParent);

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }
}
