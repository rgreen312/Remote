// Ryan Green
// CS 3 Final Project
// May 27, 2015
// Button.java

import com.jcraft.jsch.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Button
{
   /**
   *String holding the command that will be sent to the raspberry pi
   */
   private String command;
   /**
   *IP address of the raspberry pi
   */
   public static final String HOSTNAME = "192.168.1.114";
   /**
   *Username to use to log into the pi
   */
   public static final String USERNAME = "pi";
   /**
   *Password used to log into the raspberry pi
   */
   public static final String PASSWORD = "pi";
   /**
   *Frame that will display an error message if necessary
   */
   private JFrame frame = new JFrame();
    
   /**
   *Constructor method for button
   *@param command   The command of the button
   *@param name      The name of the remote this button is on
   */
   public Button(String command, String name)
   {
      //command that will activate the infrared remote on the pi when this is sent to the command line
      this.command = "irsend SEND_ONCE " + name + " KEY_" + command.toUpperCase(); 
   }
   /**
   *Abstract method to be implemented by different shaped buttons that tells whether or not they are hit
   */
   public abstract boolean isHit(int x, int y);
   
   /**
   *Transmit this buttons command to the raspberry pi
   */
   public void transmit()
   {     
      try
      {
         JSch jsch=new JSch(); //object that allows for making ssh connections
         //Log into the pi
         Session session=jsch.getSession(USERNAME, HOSTNAME, 22); //pi defaults to using port 22 
         session.setPassword(PASSWORD);
         session.setConfig("StrictHostKeyChecking", "no"); //necessary to access the command line easily
      
         session.connect(1000);//connect to the pi
      
         Channel channel = session.openChannel("exec");//set session to be a command line 
         ((ChannelExec)channel).setCommand(this.command); //send command to the pi
      
         channel.setInputStream(System.in);     
         channel.setOutputStream(System.out);
         //connect to the pi so the command can go through
         channel.connect(1000);
      }
      catch(Exception e){
         JOptionPane.showMessageDialog(frame, "Error connecting to infrared light", "Error Message", JOptionPane.ERROR_MESSAGE);
      }
   }
}