import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JScrollBar;

public interface ScrollablePlot 
{
   public void setScrollBar(JScrollBar scrollBar);
   public void scrollBarValueChanged(AdjustmentEvent event);
   public void mouseWheelScrolled(MouseWheelEvent event);
}