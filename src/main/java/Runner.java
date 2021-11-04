import javax.swing.JFrame;

import command.TreePlot;
import model.Graph;
import model.Log;
import model.Ufds;

public class Runner extends JFrame {

    public static void main(String[] args) {
        Log.clear();
        Graph.clear();
        Ufds.clear();

        TreePlot frame = new TreePlot("test1.csv");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100000, 100000);
        frame.setVisible(true);
    }

}
