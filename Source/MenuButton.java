import java.awt.*;
public class MenuButton extends Button
{
   private int x;
   private int y;
   private int width;
   private int height;
   private Graphics g;
   
   public MenuButton(int x, int y, int width, int height, Graphics g, String command)
   {
      super(command);
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.g = g;
   }
   public boolean isHit(int x, int y)
   {
      return ((this.x <= x && this.x + width >= x) && (this.y <= y && this.y + height >= y));
   }
}