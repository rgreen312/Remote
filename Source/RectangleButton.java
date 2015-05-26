// Ryan Green
// CS 3 Final Project
// May 27, 2015
// RectangleButton.java

import java.awt.Point;
public class RectangleButton extends Button
{
   /**
   *The top left point of the rectangle
   */
   private Point p;
   /**
   *The width of the rectangle
   */
   private int width;
   /**
   *The height of the rectangle
   */
   private int height;
   /**
   *Constructor method for the rectangle button
   *@param p      The top left point of the rectangle
   *@param width  The width of the rectangle
   *@param height The height of the rectangle
   *@param command   The command corresponding to this button
   *@param name      The name of the remote associated with this button
   */  
   public RectangleButton(Point p, int width, int height, String command, String name)
   {
      super(command, name);
      this.p = p;
      this.width = width;
      this.height = height;
   }
   /**
   *Returns whether a given point is inside of the button
   *@param x      The x coordinate of the click
   *@param y      The y coordinate of the click
   *@return       Whether or not the point is within the rectangle
   */
   public boolean isHit(int x, int y)
   {
     return ((p.getX() <= x && p.getX() + width >= x) && (p.getY() <= y && p.getY() + height >= y));
   }
}