import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class ImageTest
{
   public static void main(String[] args)
   {
      DrawingPanel panel = new DrawingPanel(244, 542);
      Graphics g = panel.getGraphics();
      
      BufferedImage img = null;
      try
      {
         img = ImageIO.read(new File("remote.jpg"));
      }
      catch (IOException e)
      {
      
      }
      
      g.drawImage(img, 0, 0, Color.WHITE, null);
      //Thread.sleep(2000);
      //panel.close();
   }
}