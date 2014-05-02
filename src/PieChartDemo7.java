


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JPanel;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.demo.Graphic;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing four pie charts.
 */
public class PieChartDemo7 extends ApplicationFrame {

    public PieChartDemo7(String title) {

        super(title);
        JPanel panel = new JPanel();
     
       //     ScrollPane scroll = new ScrollPane();
    
        
   //  Graphic U_T = new Graphic("U от T");
        Graphic U_U = new Graphic("U от U");
        
        
        JPanel scr = new JPanel();
        scr.add(U_U.get_ChartPanel(100, 50));
      //  scr.add(U_T.get_ChartPanel( 50, 50));
     //   scr.setSize(100,100);
       panel.add(scr);
     //   scroll.setBounds(0, 0, 800,600);
    
      //  panel.add(scroll);


       // panel.setPreferredSize(new Dimension(1000, 600));
        setContentPane(panel);

    }

    public static void main(String[] args) {
        PieChartDemo7 demo = new PieChartDemo7("Pie Chart Demo 7");
        //
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
