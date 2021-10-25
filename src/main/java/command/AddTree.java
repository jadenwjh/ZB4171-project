package command;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import model.Tree;

/**
 * insertVertex(parent,id,value,x,y,width,height)
 */
public class AddTree {

    private static int width = 80;
    private static int length = 20;
    private static int x = 20;
    private static int y = 20;

    public static void insert(String parent, String child) {
        mxGraph graph = Tree.get();

        Object p = Tree.find(parent);

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(p, null, child, x, y, width,
                    length);
            graph.insertEdge(p, null, null, p, v1);
            Tree.addLog(child, v1);
        } finally {
            graph.getModel().endUpdate();
            x += 10;
            y += 10;
        }
    }
}
