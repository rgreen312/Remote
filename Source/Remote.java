// Ryan Green
// CS 3 Final Project
// May 27, 2015
// Remote.java

import java.io.*;
import java.util.*;
import java.awt.Point;
public class Remote
{
   /**
   *Array holding all  buttons on this remote
   */
   private Button[] buttons;
   /**
   *file name of the configuration file for this remote, which holds all button locations
   */
   private String configuration;
   /**
   *Name of the file holding the image of this remote
   */
   private String imageFile;
   /**
   *The name of this remote, which must be the same as the name in the configuration file on the raspberry pi
   */
   private String name;
   /**
   *The width of the remote image
   */
   private int width;
   /**
   *The height of the remote image
   */
   private int height;
   
   /**
   *Constructor for this remote
   *Creates array of all buttons on this remote
   */
   public Remote(String fileName)
   {
      this.configuration = fileName;
      createButtonList();
   }
   /**
   *Reads information from the configuration file and creates buttons and other information from it
   */
   private void createButtonList()
   {
      //Create scanner on configuration file
      Scanner input = null;
      File f = new File(this.configuration);
      try
      {
         input = new Scanner(f);
      }
      catch (Exception e)
      {
         System.out.println("Could not find file: " + configuration);
      }
      //Get image file name
      this.imageFile = "../Images/" + input.nextLine();
      //Get remote name
      this.name = input.nextLine();
      //Get dimensions of the remote image
      Scanner dimensions = new Scanner(input.nextLine());
      this.width = dimensions.nextInt();
      this.height = dimensions.nextInt();
      //Get number of buttons
      int numButtons = dimensions.nextInt();
      this.buttons = new Button[numButtons];
      Scanner lineScan = null;
      //Create buttons from the rest of the lines of the configuration file
      for (int ii = 0; ii < numButtons; ii++)
      {
         //create line scanner
         String line = input.nextLine();
         lineScan = new Scanner(line);
         
         //Determine the type of button, and create the corresponding button object
         String buttonType = lineScan.next();
         if (buttonType.equals("circle"))
         {
            int x = lineScan.nextInt();
            int y = lineScan.nextInt();
            int radius = lineScan.nextInt();
            String command = input.nextLine();
            buttons[ii] = new CircleButton(new Point(x, y), radius, command, name);
         }
         else if (buttonType.equals("rectangle"))
         {
            int x = lineScan.nextInt();
            int y = lineScan.nextInt();
            int width = lineScan.nextInt();
            int height = lineScan.nextInt();
            String command = input.nextLine();
            buttons[ii] = new RectangleButton(new Point(x, y), width, height, command, name);
         }
         else if (buttonType.equals("triangle"))
         {
            Point[] points = new Point[3];
            for (int jj = 0; jj < 3; jj++)
            {
               int x = lineScan.nextInt();
               int y = lineScan.nextInt();
               points[jj] = new Point(x, y);
            }
            String command = input.nextLine();
            buttons[ii] = new TriangleButton(points[0], points[1], points[2], command, name);
         }
      }
   }
   /**
   *Called by the MouseListener when the user clicks on the remote
   *Checks if each button is hit
   *@param x      The x coordinate of the users click
   *@param y      The y coordinate of the users click
   */
   public void click(int x, int y)
   {
      for (int ii = 0; ii < buttons.length; ii++)
      {
         if (buttons[ii].isHit(x, y))
         {
            buttons[ii].transmit();
            break;
         }
      }
   }  
   /**
   *Method to get the width of the remote's image
   *@return    The width of the remote
   */
   public int getWidth()
   {
      return this.width;
   }
   /**
   *Method to get the height of the remote's image
   *@return    The height of the remote
   */
   public int getHeight()
   {
      return this.height;
   }
   /**
   *Method to get the image file of the remote
   *@return    The name of the image file of the remote
   */
   public String getImageFile()
   {
      return this.imageFile;
   }  
   /**
   *Returns the remote in a string format
   *Only used for testing purposes, never actually called by the program
   *@return    A string format of all the buttons on this remote
   */
   public String toString()
   {
      return (Arrays.toString(buttons));
   }
}