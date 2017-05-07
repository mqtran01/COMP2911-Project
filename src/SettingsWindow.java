import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.IOException;

public class SettingsWindow extends JFrame {
	private LinkedList<String> options = new LinkedList<String>();
	private Menu menu;
	private String[] text = {"Music", "SFX", "Back"};

	public SettingsWindow(Menu menu) {
		this.menu = menu;
		
		setTitle("Settings");
		setLocation(200, 100);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);//we dont want to close the program when we close this
		Box box = Box.createVerticalBox();
		box.add(new SettingsPanel());
		add(box);
		
		pack();
		setVisible(true);
	}
	
	private void closeWindow(){
		this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
	}

	private class SettingsPanel extends JPanel {
		public SettingsPanel() {
			//setLayout(new BorderLayout());
			
			//Make new check boxes and button
			JCheckBox musicBox = new JCheckBox(text[0]);
			JCheckBox SFXBox = new JCheckBox(text[1]);
			JButton backBtn = new JButton(text[2]);

			//Make checkBox selected initially
			musicBox.setSelected(true);
			SFXBox.setSelected(true);
			
			//Set the location of each button
			/*int btnWidth = 200;
			int btnHeight = 50;
			int startXPos = 400 - btnWidth/2;
			int startYPos = 75;
			musicBox.setBounds(startXPos, startYPos + 100, btnWidth, btnHeight);
			SFXBox.setBounds(startXPos, startYPos + 200, btnWidth, btnHeight);
			backBtn.setBounds(startXPos, startYPos + 400, btnWidth, btnHeight);*/
			
			//or we could use this to set the size
			musicBox.setSize(100,100);
			SFXBox.setSize(100,100);
			backBtn.setSize(100,100);
			
			//we could just use the one actionlistener class so that the code is in the same place
			musicBox.addActionListener(new ButtonListener());
			SFXBox.addActionListener(new ButtonListener());
			backBtn.addActionListener(new ButtonListener());
			
			//we could add the buttons horizontally or...
			/*Box box = Box.createHorizontalBox();
			box.add(musicBox);
			box.add(SFXBox);
			box.add(backBtn);
			add(box);*/
			
			//we could add them vertically
			Box box = Box.createVerticalBox();
			box.add(musicBox);
			box.add(SFXBox);
			box.add(backBtn);
			add(box);
			//add(titleLabel);//this doesn't go well with the buttons
		}
		
		 private void setSize(JComponent c, int x, int y){
		 	c.setPreferredSize(new Dimension(x, y));
		 	c.setMinimumSize(new Dimension(x, y));
		 	c.setMaximumSize(new Dimension(x, y));
		 }

		private class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand() + " was pressed");
				if (e.getActionCommand().equals("Music")) {
					//TODO: do somthing when music is changed
				}else if (e.getActionCommand().equals("SFX")) {
					// TODO: do something whn SFX is changed
				}
				else if (e.getActionCommand().equals("Back")) {
					closeWindow();
				}
			}
		}
	}
}