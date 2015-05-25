import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class RemoteMain extends JFrame
{
   private String imagePath = "/Users/ryangreen/Desktop/CS Final Project/Images/";
   /**
   *Remote object that will hold the selected remote
   */
   public Remote r;
   /**
   *Button used to open the currently selected remote
   */
   private JButton select = new JButton("Select");
   /**
   *Radio Button for the led remote
   */
   private JRadioButton led = new JRadioButton("LED Remote");
   /**
   *Radio Button for the TV remote
   */
   private JRadioButton tv = new JRadioButton("TV Remote");
   /**
   *Radio Button for the Apple remote
   */
   private JRadioButton apple = new JRadioButton("Apple Remote");
   
   /**
   *Image holding the menu icon for the led remote
   */
   private ImageIcon ledIcon = new ImageIcon(imagePath + "ledmenuremote.jpg");
   /**
   *Image holding the menu icon for the tv remote
   */
   private ImageIcon tvIcon = new ImageIcon(imagePath + "tvmenuremote.png");
   /**
   *Image holding the menu icon for the apple remote
   */
   private ImageIcon appleIcon = new ImageIcon(imagePath + "applemenuremote.png");
   /**
   *JLabel which will display the currently selected button
   */
   private JLabel image = new JLabel();
   
   /**
   *constructor method for the main remote object
   */
   public RemoteMain()
   {  
      //create new JFrame
      super("Remote Menu");
      
      //set characteristics of the menu display
      setSize(350, 500);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      //ButtonGroup makes it so only one of the buttons in the group can be selected at once
      ButtonGroup group = new ButtonGroup();
      group.add(led);
      group.add(tv);
      group.add(apple);
      
      //Make the buttons bigger and easier to read
      led.setFont(new Font("Arial", Font.PLAIN, 20));
      tv.setFont(new Font("Arial", Font.PLAIN, 20));
      apple.setFont(new Font("Arial", Font.PLAIN, 20));
      select.setFont(new Font("Arial", Font.PLAIN, 16));
      
      //set tv to be selected by default
		tv.setSelected(true);
      image.setSize(125, 286);
		image.setIcon(tvIcon);

      //Set layout of the JFrame to be a grid layout
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10); //create empty space between elements on grid
      
      //Add three radio buttons to the menu
      constraints.anchor = GridBagConstraints.SOUTHWEST;
		add(tv, constraints);
      constraints.gridy = 1;
		add(led, constraints);
      constraints.anchor = GridBagConstraints.NORTHWEST;
      constraints.gridy = 2;
      add(apple, constraints);
      
      //add image of selected remote
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
      constraints.anchor = GridBagConstraints.NORTH;
		add(image, constraints);
      
      //add select button
		constraints.gridy = 1;
		add(select, constraints);

      //create new listeners for the three radio buttons
      RadioButtonActionListener listener = new RadioButtonActionListener();
      led.addActionListener(listener);
      tv.addActionListener(listener);
      apple.addActionListener(listener);
      
      //Create listener on the select button
      //when pressed, the currently selected remote will be opened
      select.addActionListener(new ActionListener() 
      {
			public void actionPerformed(ActionEvent event) {
            String selectedOption = "/Users/ryangreen/Desktop/CS Final Project/Config/";
				if (tv.isSelected()) 
            {
					selectedOption += "tvremote.txt";
				} 
            else if (led.isSelected()) 
            {
					selectedOption += "ledremote.txt";
				}
            else if (apple.isSelected())
            {
               selectedOption += "appleremote.txt";
            }  
            //close menu
            setVisible(false);
            
            //create new remote
            r = new Remote(selectedOption);            
            DrawingPanel panel = new DrawingPanel(r.getWidth(), r.getHeight());
            Graphics g = panel.getGraphics();
            //open image of the remote
            BufferedImage img = null;
            try
            {
               img = ImageIO.read(new File(r.getImageFile()));
            }
            catch (IOException e)
            {    
               System.out.print(System.err); 
            }
            g.drawImage(img, 0, 0, Color.WHITE, null);
            //create new listener for the opened remote
            MouseListener listener = new MouseListener(panel, r);
            panel.addMouseListener(listener);
         }
		});
   } 
   
   /**
   *ActionListener class that specifically listens to radio buttons
   */
	class RadioButtonActionListener implements ActionListener 
   {
      /**
      *What will happen when a radio button is pressed (i.e. set new menu image)
      */
		public void actionPerformed(ActionEvent event) 
      {
			JRadioButton button = (JRadioButton) event.getSource(); //get button that was pressed
			//set image of new selection
         if (button == tv) 
         {
				image.setIcon(tvIcon);
			} 
         else if (button == led) 
         {
				image.setIcon(ledIcon);
			} 
         else if (button == apple)
         {
            image.setIcon(appleIcon);
         }
		}
	}
   /**
   *Creates a new RemoteMain object and sets it to true
   *Structuring the program this way allows for the RemoteMain class to extend JFrame
   */
   public static void main(String[] args)
   {
	   new RemoteMain().setVisible(true);
   }   
}