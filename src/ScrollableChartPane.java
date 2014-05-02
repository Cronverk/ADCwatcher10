import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.Plot;

public class ScrollableChartPane extends JPanel implements AdjustmentListener, MouseWheelListener
{   
   private static final long serialVersionUID = 4126132915024047763L;
   private Box chartPaneBox = null;
   private ChartPanel chartPanel = null;
   private JScrollBar scrollBar = null;

   public ScrollableChartPane(ChartPanel chartPanel) throws SQLException
   {   
      super(new GridBagLayout());
      this.chartPanel = chartPanel;
      
      GridBagConstraints constraints = new GridBagConstraints();
      constraints = getConstraints(0, 0, 1, 1, GridBagConstraints.NONE, 0, 0, new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0, 0);
      this.add(getChartBox(), constraints);
      
      this.setBorder(BorderFactory.createCompoundBorder
      (
         BorderFactory.createEmptyBorder(4, 4, 4, 4),
         BorderFactory.createEtchedBorder()
      ));
      
      Plot plot = chartPanel.getChart().getPlot();
      if (plot instanceof ScrollableCategoryPlot)
      {
         ((ScrollableCategoryPlot)plot).setScrollBar(this.getScrollBar());
      }      
      this.chartPanel.addMouseWheelListener(this);
   }
   
   private GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int fill, int ipadx, int ipady, Insets insets, int anchor, int weightx, int weighty)
   {
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.gridx = gridx;
      constraints.gridy = gridy;
      constraints.gridwidth = gridwidth;
      constraints.gridheight = gridheight;
      constraints.fill = fill;
      constraints.ipadx = ipadx;
      constraints.ipady = ipady;
      constraints.insets = insets;
      constraints.anchor = anchor;
      constraints.weightx = weightx;
      constraints.weighty = weighty;
      return constraints;      
   }
   
   private Box getChartBox() throws SQLException
   {
      if (chartPaneBox == null)
      {
         chartPaneBox = Box.createHorizontalBox();
         chartPaneBox.add(getChartPanel());         
         chartPaneBox.add(getScrollBar());
         chartPaneBox.setAlignmentX(Component.LEFT_ALIGNMENT);
         chartPaneBox.setAlignmentY(Component.TOP_ALIGNMENT);
      }
      return chartPaneBox;
   }
   
   private ChartPanel getChartPanel()
   {
      chartPanel.setMaximumSize(new Dimension(650, 450));
      chartPanel.setMinimumSize(new Dimension(650, 450));   
      return chartPanel;
   }
   
   public JScrollBar getScrollBar()
   {
      if (scrollBar == null)
      {
         scrollBar = new JScrollBar(SwingConstants.VERTICAL);
         scrollBar.setMinimum(0);
         scrollBar.setMaximum(100);         
            scrollBar.addAdjustmentListener(this);
            scrollBar.addMouseWheelListener(this);
      }
      return scrollBar;
   }

   public void adjustmentValueChanged(AdjustmentEvent event) 
   {
      Plot plot = null;      
      if ((this.chartPanel != null)&&(this.chartPanel.getChart() != null)&&(this.chartPanel.getChart().getPlot() != null))
      {
         plot = this.chartPanel.getChart().getPlot();
         if (plot instanceof ScrollableCategoryPlot)
         {
            ((ScrollableCategoryPlot)plot).scrollBarValueChanged(event);            
         }
      }      
   }

   public void mouseWheelMoved(MouseWheelEvent event) 
   {
      Plot plot = null;      
      if ((this.chartPanel != null)&&(this.chartPanel.getChart() != null)&&(this.chartPanel.getChart().getPlot() != null))
      {
         plot = this.chartPanel.getChart().getPlot();
         if (plot instanceof ScrollableCategoryPlot)
         {
            ((ScrollableCategoryPlot)plot).mouseWheelScrolled(event);            
         }
      }            
   }   
}