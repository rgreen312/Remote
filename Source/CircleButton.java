import java.awt.Point;

public class CircleButton extends Button
{
   /**
   *The center point of the circle
   */
   private Point center;
   /**
   *The radius of the circle
   */
   private int radius;
   
   /**
   *Constructor method for the circle button
   *@param center     The center point of the triangle
   *@param radius     The radius of the circle
   *@param command   The command corresponding to this button
   *@param name      The name of the remote associated with this button
   */
   public CircleButton(Point center, int radius, String command, String name)
   {
      super(command, name);
      this.center = center;
      this.radius = radius;
   }
   /**
   *Returns whether of the users click is within this button
   *@param x      The x coordinate of the click
   *@param y      The y coordinate of the click
   *@return       Whether or not the point is within the circle
   */
   public boolean isHit(int x, int y)
   {
      return ((int)Math.sqrt(Math.pow((this.center.getX() - x), 2) + Math.pow((this.center.getY() - y), 2)) < radius); //Distance formula
   }
}