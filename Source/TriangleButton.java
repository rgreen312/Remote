import java.awt.Point;
public class TriangleButton extends Button
{
   /**
   *One of the vertices of the triangle
   */
   private Point p1;
   /**
   *Another one of the vertices of the triangle
   */
   private Point p2;
   /**
   *The last of the vertices of the triangle
   */
   private Point p3;
   
   /**
   *Constructor method for the triangle button
   *@param p1     One of the vertices of the triangle
   *@param p2     One of the vertices of the triangle
   *@param p3     One of the vertices of the triangle
   *@param command   The command corresponding to this button
   *@param name      The name of the remote associated with this button
   */
   public TriangleButton(Point p1, Point p2, Point p3, String command, String name)
   {
      super(command, name);
      this.p1 = p1;
      this.p2 = p2;
      this.p3 = p3;
   }
   
   //Couldn't get the first two ways to implement the isHit method to work
   // Because I spent several hours trying to implement these methods,
   //I will leave them here commented out in anger
   /*public boolean isHit(int x, int y)
   {
      double m1 = -(p1.getY()-p2.getY())/(p1.getX()-p2.getX());
      double m3 = -(p2.getY()-p3.getY())/(p2.getX()-p3.getX());
      double m2 = -(p1.getY()-p3.getY())/(p1.getX()-p3.getX());
      
      double l1 = (m1*(x-p1.getX()) + p1.getY());
      double l2 = (m2*(x-p1.getX()) + p1.getY());
      double l3 = (m3*(x-p2.getX()) + p2.getY());
      
      boolean yGood = (y>l1) && (y>l2) && (y<l3);
      
      double maxX = p1.getX();
      double minX = p1.getX();
      if (p2.getX() > maxX)
         maxX = p2.getX();
      if (p3.getX() > maxX)
         maxX = p3.getX();
      if (p2.getX() < minX)
         minX = p2.getX();
      if (p3.getX() < minX)
         minX = p3.getX();
      boolean xGood =  (x > minX) && (x < maxX);
      return (xGood && yGood);       
   }*/
   /*public boolean isHit(int x, int y)
   {
      Point v1 = new Point((int)(p1.getX() - p2.getX()), (int)(p1.getY() - p2.getY()));
      Point v2 = new Point((int)(p1.getX() - p3.getX()), (int)(p1.getY() - p3.getY()));
      Point v3 = new Point((int)(p3.getX() - p2.getX()), (int)(p3.getY() - p2.getY()));
      double angle1 =Math.acos((v1.getX()*v2.getX() + v1.getY()*v2.getY())*Math.sqrt(Math.pow(v1.getX(),2) + Math.pow(v1.getY(),2))*Math.sqrt(Math.pow(v2.getX(),2) + Math.pow(v2.getY(),2)));
      double angle2 =Math.acos((v1.getX()*v3.getX() + v1.getY()*v3.getY())*Math.sqrt(Math.pow(v1.getX(),2) + Math.pow(v1.getY(),2))*Math.sqrt(Math.pow(v3.getX(),2) + Math.pow(v3.getY(),2)));
      double angle3 =Math.acos((v2.getX()*v3.getX() + v2.getY()*v3.getY())*Math.sqrt(Math.pow(v2.getX(),2) + Math.pow(v2.getY(),2))*Math.sqrt(Math.pow(v3.getX(),2) + Math.pow(v3.getY(),2)));
      return (Math.abs((angle1 + angle2 + angle3)-(2*Math.PI)) < .1);
   }*/
   /**
   *Returns whether a given point is inside of the button
   *@param x      The x coordinate of the click
   *@param y      The y coordinate of the click
   *@return       Whether or not the point is within the triangle
   */
   public boolean isHit(int x, int y)
   {
      double x1 = p1.getX(), y1 = p1.getY();
      double x2 = p2.getX(), y2 = p2.getY();
      double x3 = p3.getX(), y3 = p3.getY();
      
      // get area of the triangle, then of all the triangles formed with the point of the users click
      double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
      double ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
      double APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
      double PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));
      
      return ( ABP + APC + PBC == ABC);
   }
    
}