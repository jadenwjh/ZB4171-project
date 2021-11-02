import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.JFrame;

import command.RankTree;
import command.TreePlot;
import model.Graph;
import model.Log;
import model.Ufds;

public class Runner extends JFrame {

    public static void main(String[] args) {
//        Graph.clear();
//        Log.clear();
//        Ufds.clear();
//        Graph.get(11);
//        Graph.join(0, 1);
//        Graph.join(0, 2);
//        Graph.join(0, 3);
//        Graph.join(0, 4);
//        Graph.join(0, 5);
//        Graph.join(2, 3);
//        Graph.join(3, 4);
//        Graph.join(4, 5);
//        Graph.join(4, 7);
//        Graph.join(3, 6);
//        Graph.join(7, 6);
//        Graph.join(6, 8);
//        Graph.join(8, 9);
//        Graph.join(8, 10);

        TreePlot frame = new TreePlot(RankTree.rankTree("test.csv"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100000, 100000);
        frame.setVisible(true);
    }

}
