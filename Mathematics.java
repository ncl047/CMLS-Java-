import java.awt.Image;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Mathematics {
	//Declaring JFrame and JPanel fields to be used for creating the game's interface
	protected JFrame frame;
	protected JPanel panel_home, panel_lesson1, panel_lesson2, panel_lesson3, panel_congratulations, panel_wrongDialogBox, panel_settings;
	
	//Declaring JButton fields to be used for creating the game's interface
	protected JButton btn_start, btn_music, btn_next, btn_menu, btn_home, btn_mute, btn_yes, btn_no;
	protected JButton btn_q1_ans1, btn_q1_ans2, btn_q1_ans3;
	protected JButton btn_q2_ans1, btn_q2_ans2, btn_q2_ans3;
	protected JButton btn_q3_ans1, btn_q3_ans2, btn_q3_ans3;
	
	// Declaring JButton fields for wrong answer dialog box and quit dialog box
	protected JButton btn_wrong_ok, btn_ok;
	
	// Declaring JLabel fields for background images
	protected JLabel home_bg, video, instruction_bg, q1_bg, q2_bg, q3_bg, wrong_bg, quit_bg, congratulations_bg;
	
	// Declaring an object of the playAudio class to handle audio
	protected playAudio s = new playAudio();
	
	// Declaring Clip fields for audio
	protected Clip video_sound;
	protected Clip bgm;
	protected Clip wrong_audio;
	protected Clip correct_audio;
	protected Clip congrat_audio;
	
	// Abstract methods to be overridden by subclasses to provide functionality for each page
	public abstract void homepage();
	public abstract void lesson1();
	public abstract void lesson2();
	public abstract void lesson3();
	
	// Method to format an image by resizing it to a specified width and height
	public ImageIcon formatImage(String imageName, int width, int height ) {
    	ImageIcon image = new ImageIcon(getClass().getResource(imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
    }
	
	// Method to create a dialog box for when a player answers a question incorrectly
	public void wrongAnswer(JPanel panel) {
		// Creating a JLabel for the background image of the dialog box
		wrong_bg = new JLabel(formatImage("menu/WrongDialogBox.png", 684, 431));
		// Setting the bounds and visibility of the background image
		wrong_bg.setBounds(300, 200, 610, 250);
		wrong_bg.setVisible(false);
		
		// Creating a JButton for the ok button in the dialog box
		btn_wrong_ok = new JButton();
		// Setting the icons for the button
		btn_wrong_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_tryagain_normal.png")));
		btn_wrong_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_tryagain_clicked.png")));
		btn_wrong_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_tryagain_hover.png")));
		// Disabling the default button background and border
		btn_wrong_ok.setContentAreaFilled(false);
		btn_wrong_ok.setBorderPainted(false);
		// Setting the bounds and visibility of the button
		btn_wrong_ok.setBounds(510, 400, 180, 50);
		btn_wrong_ok.setVisible(false);
		
		// Adding the button and background image to the panel
		panel.add(btn_wrong_ok);
		panel.add(wrong_bg);
	}
	
	// Method to create a dialog box for when the player wants to quit the game
	public void quit(JPanel panel) {
		// Creating a JLabel for the background image of the dialog box
		quit_bg = new JLabel(formatImage("menu/QuitDialogBox.png", 580, 310));
		
		// Setting the bounds and visibility of the background image
		quit_bg.setBounds(320, 140, 580, 310);
		quit_bg.setVisible(false);
		
		// Creating JButtons for the yes and no buttons in the dialog box
		btn_yes = new JButton();
		btn_no = new JButton();
		
		// Setting the icons for the buttons
		btn_yes.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_yes_normal.png")));
		btn_yes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_yes_clicked.png")));
		btn_yes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_yes_hover.png")));
		btn_no.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_no_normal.png")));
		btn_no.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_no_clicked.png")));
		btn_no.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_no_hover.png")));
		
		// Disabling the default button background and border
		btn_yes.setContentAreaFilled(false);
		btn_yes.setBorderPainted(false);
		btn_no.setContentAreaFilled(false);
		btn_no.setBorderPainted(false);
		
		// Setting the bounds and visibility of the buttons
		btn_yes.setBounds(440, 385, 150, 50);
		btn_yes.setVisible(false);
		btn_no.setBounds(640, 385, 150, 50);
		btn_no.setVisible(false);
		
		// Adding the buttons and background image to the panel
		panel.add(btn_yes);
		panel.add(btn_no);
		panel.add(quit_bg);
	}
	
	public void init_settings(JPanel panel) {
		//Creating home button
		btn_home = new JButton();
		btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_normal.png")));
		btn_home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_clicked.png")));
		btn_home.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_setting_hover.png")));
		btn_home.setContentAreaFilled(false);
		btn_home.setBorderPainted(false);
		btn_home.setBounds(100, 20, 100, 100);
		btn_home.setVisible(true);
		
		//Creating mute button
		btn_mute = new JButton();
		btn_mute.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_mute_normal.png")));
		btn_mute.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_mute_clicked.png")));
		btn_mute.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_mute_hover.png")));
		btn_mute.setContentAreaFilled(false);
		btn_mute.setBorderPainted(false);
		btn_mute.setBounds(205, 20, 100, 100);
		btn_mute.setVisible(true);
		
		//Adding buttons to panel
		panel.add(btn_home);
		panel.add(btn_mute);
	}

	public void init_lesson1(JPanel panel, String image) {
		//Starting and looping background music
		bgm.start();
		bgm.loop(bgm.LOOP_CONTINUOUSLY);
		
		//Creating background image for the first lesson
		q1_bg = new JLabel(formatImage(image, 1280, 720));
		q1_bg.setBounds(0, -18, 1280, 720);

		//Calling methods to add home, mute, wrong answer, and quit dialog buttons to the panel
		init_settings(panel);
		wrongAnswer(panel);
		quit(panel);
		
		//Creating first answer button
		btn_q1_ans1 = new JButton();
		btn_q1_ans1.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2_normal.png")));
		btn_q1_ans1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2_clicked.png")));
		btn_q1_ans1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2_hover.png")));
		btn_q1_ans1.setContentAreaFilled(false);
		btn_q1_ans1.setBorderPainted(false);
		btn_q1_ans1.setBounds(190, 440, 220, 150);
		btn_q1_ans1.setVisible(false);
		panel.add(btn_q1_ans1);
		
		//Creating second answer button
		btn_q1_ans2 = new JButton();
		btn_q1_ans2.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_5_normal.png")));
		btn_q1_ans2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_5_clicked.png")));
		btn_q1_ans2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_5_hover.png")));
		btn_q1_ans2.setContentAreaFilled(false);
		btn_q1_ans2.setBorderPainted(false);
		btn_q1_ans2.setBounds(510, 440, 220, 150);
		btn_q1_ans2.setVisible(false);
		panel.add(btn_q1_ans2);
		
		//Creating third answer button
		btn_q1_ans3 = new JButton();
		btn_q1_ans3.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_7_normal.png")));
		btn_q1_ans3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_7_clicked.png")));
		btn_q1_ans3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_7_hover.png")));
		btn_q1_ans3.setContentAreaFilled(false);
		btn_q1_ans3.setBorderPainted(false);
		btn_q1_ans3.setBounds(830, 440, 220, 150);
		btn_q1_ans3.setVisible(false);
		panel.add(btn_q1_ans3);

		q1_bg.setVisible(true);
		//Adding all elements to the panel
		panel.add(q1_bg);

		panel.setLayout(null);
	}	
	
	// Initialize lesson 2 on the provided JPanel with a background image
	public void init_lesson2(JPanel panel, String image){	
		// Create a JLabel to set the background image
		q2_bg = new JLabel(formatImage(image, 1280, 720));
		 // Set the bounds of the background image
		q2_bg.setBounds(0, -18, 1280, 720);
		
		// Initialize settings, wrong answer button, and quit button
		init_settings(panel);
		wrongAnswer(panel);
		quit(panel);
		
		 // Initialize answer button 1
		btn_q2_ans1 = new JButton();
		 // Set icons for the button's normal, pressed, and hover states
		btn_q2_ans1.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2(1)_normal.png")));
		btn_q2_ans1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2(1)_clicked.png")));
		btn_q2_ans1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_2(1)_hover.png")));
		// Set button properties
		btn_q2_ans1.setContentAreaFilled(false);
		btn_q2_ans1.setBorderPainted(false);
		btn_q2_ans1.setBounds(190, 440, 220, 150);
		btn_q2_ans1.setVisible(false);
		panel.add(btn_q2_ans1);
		
		// Initialize answer button 2
		btn_q2_ans2 = new JButton();
		// Set icons for the button's normal, pressed, and hover states
		btn_q2_ans2.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_8_normal.png")));
		btn_q2_ans2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_8_clicked.png")));
		btn_q2_ans2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_8_hover.png")));
		// Set button properties
		btn_q2_ans2.setContentAreaFilled(false);
		btn_q2_ans2.setBorderPainted(false);
		btn_q2_ans2.setBounds(510, 440, 220, 150);
		btn_q2_ans2.setVisible(false);
		panel.add(btn_q2_ans2);
		
		  // Initialize answer button 3
		btn_q2_ans3 = new JButton();
		// Set icons for the button's normal, pressed, and hover states
		btn_q2_ans3.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_9_normal.png")));
		btn_q2_ans3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_9_clicked.png")));
		btn_q2_ans3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_9_hover.png")));
		btn_q2_ans3.setContentAreaFilled(false);
		btn_q2_ans3.setBorderPainted(false);
		btn_q2_ans3.setBounds(830, 440, 220, 150);
		btn_q2_ans3.setVisible(false);
		panel.add(btn_q2_ans3);

		q2_bg.setVisible(true);
		panel.add(q2_bg);

		panel.setLayout(null);			
	} //end of init_lesson2
	
	// Initialize lesson 3 on the provided JPanel with a background image
	public void init_lesson3(JPanel panel, String image) {
		
		// Initialize settings, wrong answer button, and quit button
		init_settings(panel);
		wrongAnswer(panel);
		quit(panel);
		
		// Create a JLabel to set the background image
		q3_bg = new JLabel(formatImage(image, 1280, 720));
		// Set the bounds of the background image
		q3_bg.setBounds(0, -18, 1280, 720);
		
		// Initialize answer button 1
		btn_q3_ans1 = new JButton();
		// Set icons for the button's normal, pressed, and hover states
		btn_q3_ans1.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_4_normal.png")));
		btn_q3_ans1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_4_clicked.png")));
		btn_q3_ans1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_4_hover.png")));
		// Set button properties
		btn_q3_ans1.setContentAreaFilled(false);
		btn_q3_ans1.setBorderPainted(false);
		btn_q3_ans1.setBounds(190, 440, 220, 150);
		btn_q3_ans1.setVisible(false);
		panel.add(btn_q3_ans1);
		
		// Initialize answer button 2
		btn_q3_ans2 = new JButton();
		// Set icons for the button's normal, pressed, and hover states
		btn_q3_ans2.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_12_normal.png")));
		btn_q3_ans2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_12_clicked.png")));
		btn_q3_ans2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_12_hover.png")));
		// Set button properties
		btn_q3_ans2.setContentAreaFilled(false);
		btn_q3_ans2.setBorderPainted(false);
		btn_q3_ans2.setBounds(510, 440, 220, 150);
		btn_q3_ans2.setVisible(false);
		panel.add(btn_q3_ans2);
		
		// Initialize answer button 3
		btn_q3_ans3 = new JButton();
		// Set icons for the button's normal, pressed, and hover states
		btn_q3_ans3.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_6_normal.png")));
		btn_q3_ans3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_6_clicked.png")));
		btn_q3_ans3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_6_hover.png")));
		// Set button properties
		btn_q3_ans3.setContentAreaFilled(false);
		btn_q3_ans3.setBorderPainted(false);
		btn_q3_ans3.setBounds(830, 440, 220, 150);
		btn_q3_ans3.setVisible(false);
		panel.add(btn_q3_ans3);

		q3_bg.setVisible(true);
		panel.add(q3_bg);

		panel.setLayout(null);			
		frame.add(panel_lesson3);
	} 

	// Initialize the congratulations screen on the provided JPanel
	public void congratulations(JPanel panel) {
	    // Create a JLabel to set the congratulations gif as the background image
	    ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("menu/congratulations.gif"));
	    congratulations_bg = new JLabel(imgIcon);
	    // Set the bounds of the background image
	    congratulations_bg.setBounds(0, -18, 1280, 720);
	    congratulations_bg.setVisible(false);
			
	    // Initialize the OK button
	    btn_ok = new JButton();
	    // Set icons for the button's normal, pressed, and hover states
	    btn_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_normal.png")));
	    btn_ok.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_clicked.png")));
	    btn_ok.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("menu/button_ok_hover.png")));
	    // Set button properties
	    btn_ok.setContentAreaFilled(false);
	    btn_ok.setBorderPainted(false);
	    btn_ok.setBounds(510, 470, 230, 70);
	    btn_ok.setVisible(false);
	    panel.add(btn_ok);
			
	    // Make the background visible and add it to the panel
	    congratulations_bg.setVisible(true);
	    panel.add(congratulations_bg);
	    // Set the panel's layout to null
	    panel.setLayout(null);
	} 
}
