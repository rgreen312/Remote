// Ryan Green
// CS 3 Final Project
// May 27, 2015
// MouseListener.java

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.SwingUtilities;

public class MouseListener extends MouseInputAdapter 
{
   /**
   *Panel this listener will be listening to
   */
   private DrawingPanel panel;
   /**
   *The remote this listener will check against when there is a click
   */
   private Remote remote;
   /**
   *Constructor for the listener object
   *Sets the panel and remote instance variables
   */
   public MouseListener(DrawingPanel panel, Remote remote) 
   {
      this.panel = panel;
      this.remote = remote;
   }
   /**
   *Method that will be called when there is a click
   *@param event     The Event object that holds the coordinates of a mouse click
   */
   public void mousePressed(MouseEvent event) 
   {
      //get coordinates
      int x = event.getX() / panel.getZoom();
      int y = event.getY() / panel.getZoom();  
      //try to press all buttons on the remote
      try 
      {
         remote.click(x, y);
      } 
      catch (RuntimeException e) 
      {
         e.printStackTrace(System.err);
      }
   }
}