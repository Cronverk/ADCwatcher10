import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.MemoryImageSource;

import javax.swing.JScrollBar;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.jfree.util.ObjectList;
import org.jfree.util.SortOrder;

public class ScrollableCategoryPlot extends CategoryPlot implements ScrollablePlot
{   
   private static final long serialVersionUID = 4760746506864409126L;
   private static final int DEFAULT_BACKGROUND_IMAGE_HEIGHT = 316;
   private static final int DEFAULT_BACKGROUND_IMAGE_WIDTH = 558;
   private static final int DEFAULT_BACKGROUND_TAB_COLOR = 0x00bfffff;
   private static final int DEFAULT_BACKGROUND_IMAGE_COLOR = 0x00ffffff;
   private static final int DEFALUT_BAR_OFFSET_PIXELS = 8;
   private static final double DEFAULT_BAR_WIDTH_PERCENT = 0.06;
    private static final double DEFAULT_BAR_SPACE_PERCENT = 0.02;
    private static final double DEFAULT_CATEGORY_SPACE_PERCENT = 0.05;
    private static final double DEFAULT_TOP_MARGIN_PERCENT = 0.05;
           
    private JScrollBar scrollBar;
   private ObjectList totalDatasets;    
    private int numberOfCategoriesDisplayed;
    private int numberOfCategoriesSkipped;
    
    private int backgroundImageHeight;
    private int backgroundImageWidth;
    private int backgroundTabColor;
    private int backgroundImageColor;
    private int barOffsetPixels;
    private double barWidthPercent;
    private double barSpacePercent;
    private double categorySpacePercent;
    private double topMarginPercent;

    public ScrollableCategoryPlot()
    {
       this(null, null, null, null);
    }
    
    public ScrollableCategoryPlot(CategoryDataset dataset, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryItemRenderer renderer)
    {
       super(dataset, domainAxis, rangeAxis, renderer);
       this.totalDatasets = new ObjectList();
        this.totalDatasets.set(0, dataset);
        this.numberOfCategoriesDisplayed = -1; //all
        this.numberOfCategoriesSkipped = 0;
        this.backgroundImageHeight = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_HEIGHT;
        this.backgroundImageWidth = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_WIDTH;
        this.backgroundTabColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_TAB_COLOR;
        this.backgroundImageColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_COLOR;
        this.barOffsetPixels = ScrollableCategoryPlot.DEFALUT_BAR_OFFSET_PIXELS;
        this.barWidthPercent = ScrollableCategoryPlot.DEFAULT_BAR_WIDTH_PERCENT;
        this.barSpacePercent = ScrollableCategoryPlot.DEFAULT_BAR_SPACE_PERCENT;
        this.categorySpacePercent = ScrollableCategoryPlot.DEFAULT_CATEGORY_SPACE_PERCENT;
        this.topMarginPercent = ScrollableCategoryPlot.DEFAULT_TOP_MARGIN_PERCENT;   
        
        this.setOrientation(PlotOrientation.HORIZONTAL);
      this.setRowRenderingOrder(SortOrder.DESCENDING);
        this.setColumnRenderingOrder(SortOrder.DESCENDING);
        this.setOutlinePaint(null);
      this.setForegroundAlpha((float)1.0);
      this.setBackgroundPaint(Color.WHITE);
      
      //position the y axis labels
      CategoryLabelPosition left = new CategoryLabelPosition
      (
         RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, 
           TextAnchor.CENTER_LEFT, 0.0,
           CategoryLabelWidthType.RANGE, 0.30f
       );
       this.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(this.getDomainAxis().getCategoryLabelPositions(), left));
      
    }
    
    public void setScrollBar(JScrollBar scrollBar)
    {
       this.scrollBar = scrollBar;
    }
    
    public int getNumberOfCategoriesDisplayed()
    {
       return numberOfCategoriesDisplayed;
    }
    
    public void setNumberOfCategoriesDisplayed(int categoriesDisplayed)
    {
       if (categoriesDisplayed < 0)
       {
          this.numberOfCategoriesDisplayed = -1;
       }
       else
       {
          this.numberOfCategoriesDisplayed = categoriesDisplayed;
       }
    }
    
    public int getNumberOfCategoriesSkipped()
    {
       return this.numberOfCategoriesSkipped;
    }
    
    public void setNumberOfCategoriesSkipped(int categoriesSkipped)
    {
       if (categoriesSkipped > 0)
       {
          this.numberOfCategoriesSkipped = categoriesSkipped;          
       }
       else
       {
          this.numberOfCategoriesSkipped = 0;
       }
    }
    
    public CategoryDataset getTotalDataset() 
    {
        CategoryDataset result = null;
        if (this.totalDatasets.size() > 0) 
        {
            result = (CategoryDataset) this.totalDatasets.get(0);
        }
        return result;
    }    
    
    public void setTotalDataset(CategoryDataset totalDataset)
    {
       this.setNumberOfCategoriesSkipped(0);
       this.totalDatasets.set(0, totalDataset);
       this.setDisplayedDataset();      
          
       if (scrollBar != null)
      {
         scrollBar.setMinimum(this.getScrollMinimumValue());
         scrollBar.setMaximum(this.getScrollMaximumValue());
         if (this.getNumberOfCategoriesDisplayed() != -1)
         {
            scrollBar.setVisibleAmount(this.getNumberOfCategoriesDisplayed());
         }
      }
    }
        
    private void setDisplayedDataset()
    {
       DefaultCategoryDataset displayedDataset = new DefaultCategoryDataset();
       int categoriesDisplayed = 0;
       if (this.getTotalDataset() != null)
       {          
          if ((this.numberOfCategoriesDisplayed != -1)&&(this.numberOfCategoriesSkipped + this.numberOfCategoriesDisplayed <= this.getTotalDataset().getColumnCount()))
          {
             categoriesDisplayed = this.numberOfCategoriesSkipped + this.numberOfCategoriesDisplayed;
          }
          else
          {
             categoriesDisplayed = this.getTotalDataset().getColumnCount();
          }
          if (this.numberOfCategoriesSkipped < categoriesDisplayed)
          {
             for (int column = this.numberOfCategoriesSkipped; column < categoriesDisplayed; column++)
              {
                 for (int row = 0; row < this.getTotalDataset().getRowCount(); row++)
                 {
                    displayedDataset.addValue(this.getTotalDataset().getValue(row, column), this.getTotalDataset().getRowKey(row), this.getTotalDataset().getColumnKey(column));
                 }
              }
          }
       }       
       this.setDataset(displayedDataset);
       this.adjustDisplay();
    }    
    
    public int getBackgroundImageHeight()
    {
       return this.backgroundImageHeight;
    }
    
    public void setBackgroundImageHeight(int backgroundHeight)
    {
       if (backgroundHeight > 0)
       {
          this.backgroundImageHeight = backgroundHeight;
       }
       else
       {
          this.backgroundImageHeight = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_HEIGHT;
       }
    }
    
    public int getBackgroundImageWidth()
    {
       return this.backgroundImageWidth;
    }
    
    public void setBackgroundImageWidth(int backgroundWidth)
    {
       if (backgroundWidth > 0)
       {
          this.backgroundImageWidth = backgroundWidth;
       }
       else
       {
          this.backgroundImageWidth = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_WIDTH;
       }
    }
    
    public int getBackgroundTabColor()
    {
       return this.backgroundTabColor;
    }
    
    public void setBackgroundTabColor(int tabColor)
    {
       if (tabColor < 0)
       {
          this.backgroundTabColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_TAB_COLOR;
       }
       else
       {
          this.backgroundTabColor = tabColor;
       }       
    }
    
    public void setBackgroundTabColor(int alpha, int red, int green, int blue)
    {
       if ((alpha >= 0)&&(alpha <= 255)&&(red >= 0)&&(red <= 255)&&(green >= 0)&&(green <= 255)&&(blue >= 0)&&(blue <= 255))
       {
          int tabColor = (alpha << 24) + (red << 16) + (green << 8) + (blue);          
          this.setBackgroundTabColor(tabColor);
       }
       else
       {
          this.backgroundTabColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_TAB_COLOR;
       } 
    }
    
    public int getBackgroundImageColor()
    {
       return this.backgroundImageColor;
    }
    
    public void setBackgroundImageColor(int imageColor)
    {
       if (imageColor < 0)
       {
          this.backgroundImageColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_COLOR;
       }
       else
       {
          this.backgroundImageColor = imageColor;
       }   
    }
    
    public void setBackgroundImageColor(int alpha, int red, int green, int blue)
    {
       if ((alpha >= 0)&&(alpha <= 255)&&(red >= 0)&&(red <= 255)&&(green >= 0)&&(green <= 255)&&(blue >= 0)&&(blue <= 255))
       {
          int imageColor = (alpha << 24) + (red << 16) + (green << 8) + (blue);          
          this.setBackgroundImageColor(imageColor);
       }
       else
       {
          this.backgroundImageColor = ScrollableCategoryPlot.DEFAULT_BACKGROUND_IMAGE_COLOR;
       } 
    }
    
    public int getBarOffsetPixels()
    {
       return this.barOffsetPixels;
    }
    
    public void setBarOffsetPixels(int offsetPixels)
    {
       if (offsetPixels >= 0)
       {
          this.barOffsetPixels = offsetPixels;
       }
       else
       {
          this.barOffsetPixels = ScrollableCategoryPlot.DEFALUT_BAR_OFFSET_PIXELS;
       }
    }
    
    public double getBarWidthPercent()
    {
       return this.barWidthPercent;
    }
    
    public void setBarWidthPercent(double barWidth)
    {
       if ((barWidth > 0.0)&&(barWidth < 1.0))
       {
          this.barWidthPercent = barWidth;
       }
       else
       {
          this.barWidthPercent = ScrollableCategoryPlot.DEFAULT_BAR_WIDTH_PERCENT;
       }
    }
    
    public double getBarSpacePercent()
    {
       return this.barSpacePercent;
    }
    
    public void setBarSpacePercent(double barSpace)
    {
       if ((barSpace > 0.0)&&(barSpace < 1.0))
       {
          this.barSpacePercent = barSpace;
       }
       else
       {
          this.barSpacePercent = ScrollableCategoryPlot.DEFAULT_BAR_SPACE_PERCENT;
       }
    }
    
    public double getCategorySpacePercent()
    {
       return this.categorySpacePercent;
    }
    
    public void setCategorySpacePercent(double categorySpace)
    {
       if ((categorySpace > 0.0)&&(categorySpace < 1.0))
       {
          this.categorySpacePercent = categorySpace;
       }
       else
       {
          this.categorySpacePercent = ScrollableCategoryPlot.DEFAULT_CATEGORY_SPACE_PERCENT;
       }
    }
    
    public double getTopMarginPercent()
    {
       return this.topMarginPercent;
    }
    
    public void setTopMarginPercent(double topMargin)
    {
       if ((topMargin > 0.0)&&(topMargin < 1.0))
       {
          this.topMarginPercent = topMargin;
       }
       else
       {
          this.topMarginPercent = ScrollableCategoryPlot.DEFAULT_TOP_MARGIN_PERCENT;
       }
    }
    
    private void adjustDisplay()
    {
       if (this.getDataset().getColumnCount() > 0)
       {
          double extraSpace = 0.0;
          BarRenderer3D renderer = (BarRenderer3D)this.getRenderer();
          renderer.setMaximumBarWidth(this.getBarWidthPercent());
          renderer.setItemMargin(this.getBarSpacePercent() * this.getDataset().getColumnCount() * (this.getDataset().getRowCount() - 1));
          this.getDomainAxis().setLowerMargin(this.getTopMarginPercent());
          this.getDomainAxis().setCategoryMargin(this.getCategorySpacePercent() * (this.getDataset().getColumnCount() - 1)); 

          extraSpace = 1 - (this.getDomainAxis().getLowerMargin() + this.getDomainAxis().getCategoryMargin() + renderer.getItemMargin() + (renderer.getMaximumBarWidth() * this.getDataset().getColumnCount() * this.getDataset().getRowCount()));
          this.getDomainAxis().setUpperMargin(extraSpace);
          this.setBackgroundImage(this.getTabbedBackgroundImage());
       }
       else
       {
          this.setBackgroundImage(this.getColoredBackgroundImage());
       }
    }
    
    private Image getColoredBackgroundImage()
   {      
        Image image;
        int imageHeight = this.getBackgroundImageHeight(); 
        int imageWidth = this.getBackgroundImageWidth();            
        int[] imagePixels = new int[imageHeight * imageWidth];                
        for (int r = 0, i = 0; r < imageHeight; r++)
        {           
           for (int c = 0; c < imageWidth; c++)
           {                           
              imagePixels[i++] = this.getBackgroundImageColor(); 
           }           
        }
        Container container = new Container();
        image = container.createImage(new MemoryImageSource(imageWidth, imageHeight, imagePixels, 0, imageWidth));
        return image;
   }
    
    private Image getTabbedBackgroundImage()
   {      
        Image image;
        int imageHeight = this.getBackgroundImageHeight(); 
        int imageWidth = this.getBackgroundImageWidth();           
        int color = 0; 
        int count = 0;
        int[] imagePixels = new int[imageHeight * imageWidth];   
        double pixelStart = this.getTopMarginPercent() * imageHeight;
        double pixelEnd = ((this.getBarWidthPercent() * this.getTotalDataset().getRowCount()) + (this.getBarSpacePercent() * (this.getTotalDataset().getRowCount() - 1))) * imageHeight + pixelStart + this.getBarOffsetPixels();
        boolean changePixelRange = false;
        for (int r = 0, i = 0; r < imageHeight; r++)
        {           
           for (int c = 0; c < imageWidth; c++)
           {
              if ((r >= pixelStart)&&(r <= pixelEnd)&&(count < this.getDataset().getColumnCount()))
              {                 
                 color = this.getBackgroundTabColor();
                 changePixelRange = true;                 
              }
              else
              {
                 color = this.getBackgroundImageColor();                 
              }              
              imagePixels[i++] = color; 
           }
           if ((changePixelRange == true)&&(r > pixelEnd))
           {
              count++;
              pixelStart = pixelEnd + (this.getCategorySpacePercent() * imageHeight) - this.getBarOffsetPixels();
              pixelEnd = pixelStart + ((this.getBarWidthPercent() * this.getTotalDataset().getRowCount()) + (this.getBarSpacePercent() * (this.getTotalDataset().getRowCount() - 1))) * imageHeight + this.getBarOffsetPixels();   
              changePixelRange = false;              
           }
        }
        Container container = new Container();
        image = container.createImage(new MemoryImageSource(imageWidth, imageHeight, imagePixels, 0, imageWidth));
        return image;         
   }
        
    private int getScrollMaximumValue()
    {
       int value = 0;
       if (this.getTotalDataset() != null)
       {
          value = this.getTotalDataset().getColumnCount();
       }       
       return value;
    }
    
    private int getScrollMinimumValue()
    {
       return 0;
    }    

   public void mouseWheelScrolled(MouseWheelEvent event) 
   {
      int value = 0;      
      if (event.getWheelRotation() < 0)
      {
         value = -1;
      }
      else
      {
         value = 1;
      }
      value += this.getNumberOfCategoriesSkipped();
      if ((this.getTotalDataset() != null)&&(value <= this.getTotalDataset().getColumnCount() - this.getNumberOfCategoriesDisplayed()))
      {         
         this.scrollBar.setValue(value);
      }            
   }

   public void scrollBarValueChanged(AdjustmentEvent event) 
   {
      this.setNumberOfCategoriesSkipped(event.getValue());
      this.setDisplayedDataset();                  
   }      
}