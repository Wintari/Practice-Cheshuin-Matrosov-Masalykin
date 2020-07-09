package application.graphviz;

import java.io.File;
import java.util.LinkedList;


public class Proba
{
    /**
     * Construct a DOT graph in memory, convert it
     * to image and store the image in the file system.
     */
    public static String makeGraph(LinkedList<String> param)
    {
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        for (String Var : param)
        {
            gv.addln(Var + ";");
        }
        gv.addln(gv.end_graph());
        //System.out.println(gv.getDotSource());

        gv.increaseDpi();   // 106 dpi

        String type = "png";
        //      String type = "gif";
        //      String type = "dot";
        //      String type = "fig";    // open with xfig
        //      String type = "pdf";
        //      String type = "ps";
        //      String type = "svg";    // open with inkscape
        //      String type = "png";
        //      String type = "plain";

        String repesentationType= "dot";
        //		String repesentationType= "dot";
        //		String repesentationType= "neato";
        //		String repesentationType= "fdp";
        //		String repesentationType= "sfdp";
        // 		String repesentationType= "twopi";
        // 		String repesentationType= "circo";

        String path = "temp" + "."+ type;
        File out = new File(path);
        gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );

        return path;
    }
}