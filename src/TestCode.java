import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestCode extends JFrame
{   
   private static final long serialVersionUID = 1L;

    public TestCode()
    {
       super();
       CategoryAxis3D yAxis = new CategoryAxis3D("Y Axis Title");
      ValueAxis xAxis = new NumberAxis3D("X Axis Title");
      BarRenderer3D renderer = new BarRenderer3D();         
            
      ScrollableCategoryPlot categoryPlot = new ScrollableCategoryPlot(new DefaultCategoryDataset(), yAxis, xAxis, renderer);
      JFreeChart chart = new JFreeChart("Chart Title", JFreeChart.DEFAULT_TITLE_FONT, categoryPlot, true);
      ChartPanel chartPanel = new ChartPanel(chart);      
      
      try 
      {
         ScrollableChartPane scrollableChartPane = new ScrollableChartPane(chartPanel);
         this.setContentPane(scrollableChartPane);
         setSize(710, 540);
         setVisible(true);      
         setResizable(false);      
         addWindowListener(new CloseListener());
         
         //set the dataset at a later time...
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         categoryPlot.setNumberOfCategoriesDisplayed(3);
         dataset.addValue(32, "Series1", "Category1");
         dataset.addValue(8, "Series2", "Category1");
         dataset.addValue(14, "Series3", "Category1");
         dataset.addValue(9, "Series1", "Category2");
         dataset.addValue(24, "Series2", "Category2");
         dataset.addValue(15, "Series3", "Category2");
         dataset.addValue(30, "Series1", "Category3");
         dataset.addValue(7, "Series2", "Category3");
         dataset.addValue(26, "Series3", "Category3");
         dataset.addValue(19, "Series1", "Category4");
         dataset.addValue(10, "Series2", "Category4");
         dataset.addValue(21, "Series3", "Category4");
         dataset.addValue(12, "Series1", "Category5");
         dataset.addValue(31, "Series2", "Category5");
         dataset.addValue(12, "Series3", "Category5");
         dataset.addValue(5, "Series1", "Category6");
         dataset.addValue(6, "Series2", "Category6");
         dataset.addValue(14, "Series3", "Category6");
         categoryPlot.setTotalDataset(dataset);
      } 
      catch (SQLException e) 
      {         
         e.printStackTrace();
      }
    }
    
    private class CloseListener extends WindowAdapter
   {
      public void windowClosing(WindowEvent event)
      {         
         dispose();
         System.exit(0);
      }      
   }
   
   public static void main(String[] args)
   {
      new TestCode();      
   }
} 