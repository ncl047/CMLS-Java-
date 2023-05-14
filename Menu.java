import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.sound.sampled.Clip;

public class Menu {
    // Declare JFrame and JPanel for menu
    private JFrame frame;
    private JPanel panel_menu;
    // Declare JLabel for menu title and mission title
    private JLabel menu, menu_scene, missions, wrong_module;
    // Declare JButton for different missions, play, quit and ok
    private JButton btn_add, btn_subtract, btn_multiply, btn_divide, btn_divide_locked, btn_multiply_locked, btn_subtract_locked, btn_play, btn_quit, btn_ok, btn_home;
    // Declare Timer
    protected Timer timer;
    private double seconds;
    // Declare audio clips for background music and sound effects
    protected Clip homepage_bgm;
    protected playAudio h = new playAudio();
    protected Clip play_audio;
    protected Clip correct_audio;
	
    public static void main(String[] args){
    	// Create instance of Menu class and call start method
        Menu m = new Menu();
        m.start();
    }
	
    // Display main menu & buttons representing different missions (add, sub, mul, div)
    public void menu() {
    	// Add mission title to panel
        panel_menu.add(missions);
        missions.setVisible(true);
        // Set layout to null
        panel_menu.setLayout(null);
        // Call wrongModule method and pass panel_menu as parameter
        wrongModule(panel_menu);
        // Add action listener to ok button
        btn_ok.addActionListener(choice);

        // Create home button
        btn_home = new JButton();
        // Set icon, pressed icon, rollover icon, content area filled, border painted and bounds
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_normal.png")));
        btn_home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_clicked.png")));
        btn_home.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_hover.png")));
        btn_home.setContentAreaFilled(false);
        btn_home.setBorderPainted(false);
        btn_home.setBounds(1050, 40, 100, 100);
        // Set button visible
        btn_home.setVisible(true);
        // Add action listener to home button
        btn_home.addActionListener(choice);

        // Add home button and other mission buttons to panel
        panel_menu.add(btn_home);
        panel_menu.add(btn_add);
        panel_menu.add(btn_subtract);
        // Make subtract button invisible
        btn_subtract.setVisible(false);
        panel_menu.add(btn_subtract_locked);
        // Make locked subtract button visible
        btn_subtract_locked.setVisible(true);
        panel_menu.add(btn_multiply);
        // Make multiply button invisible
        btn_multiply.setVisible(false);
        panel_menu.add(btn_multiply_locked);
        // Make locked multiply button visible
        btn_multiply_locked.setVisible(true);
        panel_menu.add(btn_divide);
        // Make divide button invisible
        btn_divide.setVisible(false);
        panel_menu.add(btn_divide_locked);
        // Make locked divide button visible
        btn_divide_locked.setVisible(true);
        
        // Set z-order of components to control overlapping
        panel_menu.setComponentZOrder(missions, 4);
        panel_menu.setComponentZOrder(btn_add, 3);
        panel_menu.setComponentZOrder(btn_subtract, 2);
        panel_menu.setComponentZOrder(btn_subtract_locked, 2);
        panel_menu.setComponentZOrder(btn_multiply, 1);
        panel_menu.setComponentZOrder(btn_multiply_locked, 1);
        panel_menu.setComponentZOrder(btn_divide, 0);
        panel_menu.setComponentZOrder(btn_divide_locked, 0);
        panel_menu.setComponentZOrder(wrong_module, 1);
        panel_menu.setComponentZOrder(btn_ok, 0);
        panel_menu.setComponentZOrder(btn_home, 0);
		
        // Create file object for completed missions file
		File completedMissionsFile = new File("completed_missions.txt");
				
		FileReader reader = null;
		try {
			reader = new FileReader(completedMissionsFile);
			String completedMissions = "";
			int i;
			while ((i = reader.read()) != -1) {
				completedMissions += (char) i;
			}

			// Check if "addition" mission is completed
			if (completedMissions.contains("addition")) {
				 // If mission is completed, make addition button visible and locked button invisible
				btn_subtract.setVisible(true);
				btn_subtract_locked.setVisible(false);
			}
			// Check if "subtraction" mission is completed
			if (completedMissions.contains("subtraction")) {
				// If mission is completed, make subtraction button visible and locked button invisible
				btn_subtract.setVisible(true);
				btn_subtract_locked.setVisible(false);
				btn_multiply.setVisible(true);
				btn_multiply_locked.setVisible(false);
			}
			 // Check if "multiplication" mission is completed
			if (completedMissions.contains("multiplication")) {
				// If mission is completed, make division button visible and locked button invisible
				btn_subtract.setVisible(true);
				btn_subtract_locked.setVisible(false);
				btn_multiply.setVisible(true);
				btn_multiply_locked.setVisible(false);
				btn_divide.setVisible(true);
				btn_divide_locked.setVisible(false);
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		frame.add(panel_menu);
		frame.setVisible(true);	
	}
	
	// Default Constructor
    public Menu(){
        // Creates a new JFrame with the title "Children Math Learning System"
        frame = new JFrame("Children Math Learning System");
        panel_menu = new JPanel();

        // Create an ImageIcon for the main menu gif
        ImageIcon menu_gif = new ImageIcon(getClass().getResource("menu/Main Page.gif"));
        // Create a JLabel for the gif and set its bounds
        menu = new JLabel(menu_gif);
        menu.setBounds(0, 0, 1280, 720);

        // Create a Clip for correct audio
        play_audio = h.playMusic("Audio/Correct audio.wav");

        // Create a Clip for background music, start it, and set it to loop continuously
        homepage_bgm = h.playMusic("Audio/Background music.wav");
        homepage_bgm.start();
        homepage_bgm.loop(homepage_bgm.LOOP_CONTINUOUSLY);

        // Create a JLabel for the menu scene and set its bounds and visibility
        menu_scene = new JLabel(formatImage("menu/Menu.png", 1280, 720));
        menu_scene.setBounds(0, 0, 1280, 720);
        menu_scene.setVisible(false);
        
        // Create a JLabel for the home page and set its bounds and visibility
        missions = new JLabel(formatImage("menu/homepage.png", 1280, 720));
        missions.setBounds(0, 0, 1280, 720);
        missions.setVisible(false);

        // Create a JButton for addition, set its icon, pressed icon, rollover icon, and bounds
        btn_add = new JButton();
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_add_normal.png")));
        btn_add.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_add_clicked.png")));
        btn_add.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_add_hover.png")));
        btn_add.setContentAreaFilled(false);
        btn_add.setBorderPainted(false);
        btn_add.setBounds(120, 60, 350, 170);
        btn_add.addActionListener(choice);
        
        // Create a JButton for subtraction, set its icon, pressed icon, rollover icon, and bounds
        btn_subtract = new JButton();
        btn_subtract.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_sub_normal.png")));
        btn_subtract.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_sub_clicked.png")));
        btn_subtract.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_sub_hover.png")));
        btn_subtract.setContentAreaFilled(false);
		btn_subtract.setBorderPainted(false);
		btn_subtract.setBounds(180, 400, 350, 170);
		btn_subtract.addActionListener(choice);
		
		btn_subtract_locked = new JButton();
		btn_subtract_locked.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_sub(locked)_normal.png")));
		btn_subtract_locked.setContentAreaFilled(false);
		btn_subtract_locked.setBorderPainted(false);
		btn_subtract_locked.setBounds(180, 400, 350, 170);
		btn_subtract_locked.addActionListener(choice);

		 // Create a JButton for a locked subtraction, set its icon, and bounds
	    btn_subtract_locked = new JButton();
	    btn_subtract_locked.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_sub(locked)_normal.png")));
	    btn_subtract_locked.setContentAreaFilled(false);
	    btn_subtract_locked.setBorderPainted(false);
	    btn_subtract_locked.setBounds(180, 400, 350, 170);
	    btn_subtract_locked.addActionListener(choice);

	    // Create a JButton for multiplication, set its icon, pressed icon, rollover icon, and bounds
	    btn_multiply = new JButton();
	    btn_multiply.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_multiplication_normal.png")));
	    btn_multiply.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_multiplication_clicked.png")));
	    btn_multiply.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_multiplication_hover.png")));
	    btn_multiply.setContentAreaFilled(false);
	    btn_multiply.setBorderPainted(false);
	    btn_multiply.setBounds(670, 130, 350, 170);
	    btn_multiply.addActionListener(choice);

	    // Create a JButton for a locked multiplication, set its icon and bounds
	    btn_multiply_locked = new JButton();
	    btn_multiply_locked.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_multiplication(locked)_normal.png")));
	    btn_multiply_locked.setContentAreaFilled(false);
	    btn_multiply_locked.setBorderPainted(false);
		btn_multiply_locked.setBounds(670, 130, 350, 170);
		btn_multiply_locked.addActionListener(choice);

		// Create a JButton for division, set its icon, pressed icon, rollover icon, and bounds
		btn_divide = new JButton();
		btn_divide.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_division_normal.png")));
		btn_divide.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_division_clicked.png")));
		btn_divide.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_division_hover.png")));
		btn_divide.setContentAreaFilled(false);
		btn_divide.setBorderPainted(false);
		btn_divide.setBounds(740, 460, 350, 170);
		btn_divide.addActionListener(choice);
		
		// Create a JButton for a locked division, set its icon and bounds
		btn_divide_locked = new JButton();
		btn_divide_locked.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_divide(locked)_normal.png")));
		btn_divide_locked.setContentAreaFilled(false);
		btn_divide_locked.setBorderPainted(false);
		btn_divide_locked.setBounds(740, 460, 350, 170);
		btn_divide_locked.addActionListener(choice);
		
		  // Create a JButton for playing the game, set its icon, pressed icon, rollover icon, and bounds
		btn_play = new JButton();
		btn_play.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_start_normal.png")));
		btn_play.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_start_clicked.png")));
		btn_play.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_start_hover.png")));
		btn_play.setContentAreaFilled(false);
		btn_play.setBorderPainted(false);
		btn_play.setBounds(760, 225, 250, 80);
		btn_play.setVisible(false);
		btn_play.addActionListener(choice);
		
		 // Create a JButton for quitting the game, set its icon, pressed icon, rollover icon, and bounds
		btn_quit = new JButton();
		btn_quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_quit_normal.png")));
		btn_quit.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_quit_clicked.png")));
		btn_quit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_quit_hover.png")));
		btn_quit.setContentAreaFilled(false);
		btn_quit.setBorderPainted(false);
		btn_quit.setBounds(760, 405, 250, 80);
		btn_quit.setVisible(false);
		btn_quit.addActionListener(choice);

		frame.pack();
		frame.setSize(1280, 720);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//quit when X is clicked
		frame.setResizable(false);			
		frame.setLocationRelativeTo(null);	
	} //end of constructor
	
    public void main_menu() {
        // Adds the menu_scene, missions, btn_play, and btn_quit JComponents to the panel_menu JPanel
		panel_menu.add(menu_scene);
		panel_menu.add(missions);
		panel_menu.add(btn_play);
		panel_menu.add(btn_quit);
		
		// Calls the wrongModule function to display error message for unavailable game modes
		wrongModule(panel_menu);
		
		// Sets the z-order of the different JComponents to ensure they are displayed in the correct order
		panel_menu.setComponentZOrder(menu_scene, 2);
		panel_menu.setComponentZOrder(btn_play, 1);
		panel_menu.setComponentZOrder(btn_quit, 0);

		// Sets the layout of the panel_menu to null, allowing for manual positioning of components
		panel_menu.setLayout(null);
		
		// Write the "completed_missions.txt" file to empty when in main menu
		File completedMissionsFile = new File("completed_missions.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(completedMissionsFile, false);
			writer.write("");
			writer.close();
		} catch (IOException ex) {
			System.out.println("An error occurred.");
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		// Adds the panel_menu to the frame and sets it to be visible
		frame.add(panel_menu);
		frame.setVisible(true);

		// Sets the visibility of the different JComponents in the menu to true
		menu_scene.setVisible(true);
		btn_play.setVisible(true);
		btn_quit.setVisible(true);
		menu_scene.setVisible(true);
	} //end of main menu
	
	public void start(){
	    // Adds the menu JComponent to the panel_menu JPanel
		panel_menu.add(menu);
			
	    // Adds the missions, btn_play, and btn_quit JComponents to the panel_menu JPanel
		panel_menu.add(missions);
		panel_menu.add(btn_play);
		panel_menu.add(btn_quit);
			
	    // Calls the wrongModule function to display error message for unavailable game modes
		wrongModule(panel_menu);

	    // Sets the z-order of the different JComponents to ensure they are displayed in the correct order
		panel_menu.setComponentZOrder(menu, 0);
		panel_menu.setComponentZOrder(btn_play, 1);
		panel_menu.setComponentZOrder(btn_quit, 2);
		panel_menu.setComponentZOrder(menu_scene, 3);

	    // Sets the layout of the panel_menu to null, allowing for manual positioning of components
		panel_menu.setLayout(null);
			
	    // Adds the panel_menu to the frame and sets it to be visible
		frame.add(panel_menu);

	    // Initializes the seconds variable and creates a new Timer that increments the seconds variable by 0.5 every 0.5 seconds
		seconds = 0;
		timer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				seconds = seconds + 0.5;
					
				// When the seconds variable reaches 2.5, the menu JComponent is set to be
				if(seconds == 2.5){
					menu.setVisible(false);
					menu_scene.setVisible(true);
					btn_play.setVisible(true);
					btn_quit.setVisible(true);
					menu_scene.setVisible(true);
					timer.stop();
				}
			}
			});

		// Write the "completed_missions.txt" file to empty every time at the start of the program
		File completedMissionsFile = new File("completed_missions.txt");
		FileWriter writer = null;
		try {
			writer = new FileWriter(completedMissionsFile, false);
			writer.write("");
			writer.close();
		} catch (IOException ex) {
			System.out.println("An error occurred.");
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

		timer.start();	
		frame.setVisible(true);
	} //end of start
		
	public ImageIcon formatImage(String imageName, int width, int height ) {
    	ImageIcon image = new ImageIcon(getClass().getResource(imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
    }
	
	// Defining an ActionListener object named "choice" that will listen for events on various buttons
	ActionListener choice = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			 // if the "btn_add" button is clicked
			 if(e.getSource() == btn_add){
				 // stop the homepage background music
				homepage_bgm.stop();
				// remove all the existing components from the frame
				frame.removeAll();
				// validate and repaint the frame's content pane
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				// make the panel_menu null
				panel_menu = null;
				// create an Addition object and call its homepage method
				Addition a = new Addition();
				a.homepage();
				// hide and dispose the frame
				frame.setVisible(false);
				frame.dispose();
			} 
			// if the "btn_subtract" button is clicked
			else if(e.getSource() == btn_subtract){
				// stop the homepage background music
				homepage_bgm.stop();
				// validate and repaint the frame's content pane
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				frame.repaint();
				// make the panel_menu null
				panel_menu = null;
				// create a Subtraction object and call its homepage method
				Subtraction s = new Subtraction();
				s.homepage();
				// hide and dispose the frame
				frame.setVisible(false);
				frame.dispose();
			} 
			// if the "btn_multiply" button is clicked
			else if(e.getSource() == btn_multiply){
				// stop the homepage background music
				homepage_bgm.stop();
				// remove all the existing components from the frame
				frame.removeAll();
				// validate and repaint the frame's content pane
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				// make the panel_menu null
				panel_menu = null;
				// create a Multiplication object and call its homepage method
				Multiplication m = new Multiplication();
				m.homepage();
				// hide and dispose the frame
				frame.setVisible(false);
				frame.dispose();
			} 
			// if the "btn_divide" button is clicked
			else if(e.getSource() == btn_divide){
				// stop the homepage background music
				homepage_bgm.stop();
				// remove all the existing components from the frame
				frame.removeAll();
				// validate and repaint the frame's content pane
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
				// make the panel_menu null
				panel_menu = null;
				// create a Division object and call its homepage method
				Division d = new Division();
				d.homepage();
				// hide and dispose the frame
				frame.setVisible(false);
				frame.dispose();
			// if the "btn_play" button is clicked
			} else if(e.getSource() == btn_play){
				// play the "Correct audio.wav" music and start it
				play_audio = h.playMusic("Audio/Correct audio.wav");
				play_audio.start();
				// hide the menu_scene and both buttons
				menu_scene.setVisible(false);
				btn_play.setVisible(false);
				btn_quit.setVisible(false);
				// call the menu method and make the frame visible
				menu();
				frame.setVisible(true);
			// if the "btn_quit" button is clicked
			} else if(e.getSource() == btn_quit){
				// play the "Correct audio.wav" music and start it
				correct_audio = h.playMusic("Audio/Correct audio.wav");
				correct_audio.start();
				// exit the program
				System.exit(0);
			} 
			// if the "btn_subtract_locked", "btn_multiply_locked", or "btn_divide_locked" button is clicked
			else if(e.getSource() == btn_subtract_locked || e.getSource() == btn_multiply_locked || e.getSource() == btn_divide_locked){
				// make the wrong_module and btn_ok visible
				wrong_module.setVisible(true);
				btn_ok.setVisible(true);
			// if the "btn_ok" button is clicked
			} else if(e.getSource() == btn_ok){
				// remove the wrong_module and btn_ok from the panel_menu
				panel_menu.remove(wrong_module);
			    panel_menu.remove(btn_ok);
			    // revalidate and repaint the panel_menu
			    panel_menu.revalidate(); 
			    panel_menu.repaint();
				// call the menu method
				menu();
			// if the "btn_home" button is clicked
			}else if(e.getSource() == btn_home){
				// play the "Correct audio.wav" music and start it
				correct_audio = h.playMusic("Audio/Correct audio.wav");
				correct_audio.start();
				// remove the panel_menu from the frame and make the btn_home invisible
				frame.remove(panel_menu);
				btn_home.setVisible(false);
				main_menu();
			}
		}
	};

	public void wrongModule(JPanel panel) {
		// Creating a JLabel object named "wrong_module" 
		wrong_module = new JLabel(formatImage("menu/Module Locked.png", 684, 431));
		// setting the bounds and visibility of the label
		wrong_module.setBounds(300, 200, 610, 250);
		wrong_module.setVisible(false);
		
		// Creating a JButton object named "btn_ok" 
		btn_ok = new JButton();
		// setting the icon, pressed icon, rollover icon of the button
		btn_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_normal.png")));
		btn_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_clicked.png")));
		btn_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_hover.png")));
		// setting the ContentAreaFilled and BorderPainted properties of the button to false
		btn_ok.setContentAreaFilled(false);
		btn_ok.setBorderPainted(false);
		// setting the bounds and visibility of the button
		btn_ok.setBounds(510, 400, 180, 50);
		btn_ok.setVisible(false);
		
		// Adding the button and label to the panel
		panel.add(btn_ok);
		panel.add(wrong_module);
	}
}