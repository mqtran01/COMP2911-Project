import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Game extends JPanel {
	private JLabel[][] grid;
	private int length; 
	private int height;
	private Map map;
	private Map previousMap;//i.e. the map used for undo
	private GameSettings settings;
	private KeyEventDispatcher keyDispatcher;

	
	private final String m_footsteps = "assets/MusicFootsteps.wav";
	private final String m_moveBox = "assets/MusicMoveBox.wav";
	private final String m_winGame = "assets/MusicWinGame.wav";
	
	final static int PLAYER_UP = 7;
	final static int PLAYER_DOWN = 8;
	final static int PLAYER_LEFT = 9;
	final static int PLAYER_RIGHT = 10;
	final static int GOALPLAYER_UP = 11;
	final static int GOALPLAYER_DOWN = 12;
	final static int GOALPLAYER_LEFT = 13;
	final static int GOALPLAYER_RIGHT = 14;
	

	public Game(CardLayout views, JPanel mainPanel, Map map, GameSettings settings)  {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(800,600));
		this.map = map;
		this.settings = settings;
		length = map.getLength();
		height = map.getHeight();
		JPanel gridPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		grid = new JLabel[length][height];

		double scaledLen = 800/length;
		double scaledHgt = 500/height;
		int proportion = (int) Math.min(scaledLen, scaledHgt);

		ImageIcon wall = resizedImage(Map.WALL, proportion);
		ImageIcon empty = resizedImage(Map.EMPTY, proportion);
		ImageIcon player = resizedImage(Map.PLAYER, proportion);
		ImageIcon box = resizedImage(Map.BOX, proportion);
		ImageIcon goal = resizedImage(Map.GOAL, proportion);
		ImageIcon goalBox = resizedImage(Map.GOALBOX, proportion);
		ImageIcon goalPlayer = resizedImage(Map.GOALPLAYER, proportion);



		for (int y=0; y<height; y++){
			gbc.gridy = y;
			for (int x=0; x<length; x++){
				gbc.gridx = x;
				int tileItem = map.getTile(x, y);
				ImageIcon img = null;
				switch (tileItem) {
				case Map.WALL:
					img = wall;
					break;
				case Map.EMPTY:
					img = empty;
					break;
				case Map.PLAYER:
					img = player;
					break;
				case Map.BOX:
					img = box;
					break;
				case Map.GOAL:
					img = goal;
					break;
				case Map.GOALBOX:
					img = goalBox;
					break;
				case Map.GOALPLAYER:
					img = goalPlayer;
					break;
				}
				grid[x][y] = new JLabel("", img, JLabel.CENTER);
				grid[x][y].setPreferredSize(new Dimension(proportion,proportion));
				gridPanel.add(grid[x][y], gbc);
			}
		}

		JPanel btnPanel = new JPanel(new GridLayout(1,3));
		Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
		Border buttonBorder = new LineBorder(Color.BLUE, 2);

		JButton saveBtn = new JButton("Save");
		saveBtn.setFont(gameFont);
		saveBtn.setBorder(buttonBorder);
		saveBtn.setPreferredSize(new Dimension((800-3)/3, 71));

		JButton undoBtn = new JButton("Undo");
		undoBtn.setFont(gameFont);
		undoBtn.setBorder(buttonBorder);
		undoBtn.setPreferredSize(new Dimension((800-3)/3, 71));

		JButton quitBtn = new JButton("Main Menu");
		quitBtn.setFont(gameFont);
		quitBtn.setBorder(buttonBorder);
		quitBtn.setPreferredSize(new Dimension((800-3)/3, 71));


		saveBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Save!");
				try {
					SaveLoad.save(Game.this.map);
				} catch (IOException e1) {
					System.out.println("Failed to save");
					e1.printStackTrace();
				}
			}
		});

		undoBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Undo!");
				if (previousMap != null){
					setMap(Game.this.previousMap);
					update("w");
				}
			}
		});
		quitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Main Menu!");
				disableKeys();
				views.show(mainPanel, "Menu");
			}
		});

		btnPanel.add(saveBtn);
		btnPanel.add(undoBtn);
		btnPanel.add(quitBtn);

		this.add(gridPanel);
		this.add(btnPanel);
		
		
		this.keyDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent key) {
                if (key.getID() == KeyEvent.KEY_PRESSED){
                    String keyPressed = null;
                    System.out.println(key.getKeyCode());
                    if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_UP){
                    	Game.this.previousMap = Game.this.map.clone();
                        Game.this.map.moveUp();
                        System.out.println("music is " + settings.isEnableMusic());
                        playSound(m_footsteps);
                        keyPressed = "w";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_S || key.getKeyCode() == KeyEvent.VK_DOWN){
                    	Game.this.previousMap = Game.this.map.clone();
                        Game.this.map.moveDown();
                        playSound(m_footsteps);
                        keyPressed = "s";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_A || key.getKeyCode() == KeyEvent.VK_LEFT){
                    	Game.this.previousMap = Game.this.map.clone();
                        Game.this.map.moveLeft();
                        playSound(m_footsteps);
                        keyPressed = "a";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_D || key.getKeyCode() == KeyEvent.VK_RIGHT){
                    	Game.this.previousMap = Game.this.map.clone();
                        Game.this.map.moveRight();
                        playSound(m_footsteps);
                        keyPressed = "d";
                    }
                    update(keyPressed);
                    if (Game.this.map.winState()){
                    	disableKeys();
                        playSound(m_winGame);
                        Object[] options = {"Play Again?", "Main Menu"};
                        int n = JOptionPane.showOptionDialog(null, "              Congratulations on winning!", 
                                                             "You have won!", JOptionPane.YES_NO_CANCEL_OPTION, 
                                                             JOptionPane.DEFAULT_OPTION, null,
                                                             options,
                                                             options[1]);
                        JOptionPane.getRootFrame().dispose(); 
                        if (n == JOptionPane.NO_OPTION){
                            System.out.println("Clicked Main Menu!");
                            views.show(mainPanel, "Menu");
                        }
                        if (n == JOptionPane.YES_OPTION){
                            System.out.println("Clicked Play Again!");
                            views.show(mainPanel, "Level");
                        }
                    }
                }
                return false;
            }
        };
		
		//add in keyboard controls
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyDispatcher);
		
		previousMap = null;
	}

	private void playSound(String filename) {
		Thread musicThread = new Thread() {
			@Override
			public void run() {
				try{
					// Open an audio input stream.
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
					// Get a sound clip resource.
					Clip clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					clip.start();
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		if (settings.isEnableSFX()) {
			System.out.println("SFX enabled");
			musicThread.start();
		}
	}

	/*
	private void playSound(String filename){
  		try{
			// Open an audio input stream.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}*/
	

	private ImageIcon resizedImage(int item, int size) {

		String imgLoc = null;
		if (settings.isCharacterColorRed()){
			imgLoc = characterImageSelector("player", item);
		} else {
			imgLoc = characterImageSelector("player2", item);
		}

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgLoc));
		} catch (IOException e) {
			return null;
		}

		Image dimg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}
	
	/**
	 * method for setting the map
	 * @param map
	 */
	public void setMap(Map map){
		this.map = map;
		String s = null;
		update(s);
	}
	
	/**
	 * method for updating the sprites displayed by the grid
	 */
	public void update(String direction){
		System.out.println("updating");
		double scaledLen = 800/length;
		double scaledHgt = 500/height;
		
		int proportion = (int) Math.min(scaledLen, scaledHgt);
		ImageIcon wall = resizedImage(Map.WALL, proportion);
		ImageIcon empty = resizedImage(Map.EMPTY, proportion);
		ImageIcon box = resizedImage(Map.BOX, proportion);
		ImageIcon goal = resizedImage(Map.GOAL, proportion);
		ImageIcon goalBox = resizedImage(Map.GOALBOX, proportion);
		ImageIcon goalPlayer = resizedImage(Map.GOALPLAYER, proportion);
		ImageIcon player = resizedImage(Map.PLAYER, proportion);
		
		if (direction != null) {
			switch (direction) {
			case "w":
				player = resizedImage(PLAYER_UP, proportion);
				goalPlayer = resizedImage(GOALPLAYER_UP, proportion);
				break;
			case "s":
				player = resizedImage(PLAYER_DOWN, proportion);
				goalPlayer = resizedImage(GOALPLAYER_DOWN, proportion);
				break;
			case "a":
				player = resizedImage(PLAYER_LEFT, proportion);
				goalPlayer = resizedImage(GOALPLAYER_LEFT, proportion);
				break;
			case "d":
				player = resizedImage(PLAYER_RIGHT, proportion);
				goalPlayer = resizedImage(GOALPLAYER_RIGHT, proportion);
				break;
			}
		}
		
		for (int y=0; y<height; y++){
			for (int x=0; x<length; x++){
				int tileItem = map.getTile(x, y);
				ImageIcon img = null;
				switch (tileItem) {
				case Map.WALL:
					img = wall;
					break;
				case Map.EMPTY:
					img = empty;
					break;
				case Map.PLAYER:
					img = player;
					break;
				case Map.BOX:
					img = box;
					break;
				case Map.GOAL:
					img = goal;
					break;
				case Map.GOALBOX:
					img = goalBox;
					break;
				case Map.GOALPLAYER:
					img = goalPlayer;
					break;
				}
				grid[x][y].setIcon(img);
				System.out.print(tileItem);
			}
			System.out.println();
		}
	}
	
	/**
	 * Method to select images according to player type
	 * @param player
	 * @param item
	 * @return String
	 */
	private String characterImageSelector(String player, int item) {
		String imgLoc = null;
		switch (item) {
		case Map.WALL:
			imgLoc = "image/Wall.png";
			break;
		case Map.EMPTY:
			imgLoc = "image/Empty.png";
			break;
		case Map.PLAYER:
		    imgLoc = "image/" + player + "Down.png";
			break;
		case Map.BOX:
            imgLoc = "image/Box.png";
            //imgLoc = "image/james.png";
			break;
		case Map.GOAL:
			imgLoc = "image/Goal.png";
			break;
		case Map.GOALBOX:
			imgLoc = "image/GoalBox.png";
			break;
		case Map.GOALPLAYER:
			imgLoc = "image/" + player + "DownGoal.png";
			break;
		case PLAYER_UP:
			imgLoc = "image/" + player + "Up.png";
			break;
		case PLAYER_DOWN:
			imgLoc = "image/" + player + "Down.png";
			break;
		case PLAYER_LEFT:
			imgLoc = "image/" + player + "Left.png";
			break;
		case PLAYER_RIGHT:
			imgLoc = "image/" + player + "Right.png";
			break;
		case GOALPLAYER_UP:
			imgLoc = "image/" + player + "UpGoal.png";
			break;
		case GOALPLAYER_DOWN:
			imgLoc = "image/" + player + "DownGoal.png";
			break;
		case GOALPLAYER_LEFT:
			imgLoc = "image/" + player + "LeftGoal.png";
			break;
		case GOALPLAYER_RIGHT:
			imgLoc = "image/" + player + "RightGoal.png";
			break;
		}
		return imgLoc;
	}
	
	public void disableKeys() {
	    System.out.println("Disabled the keys");
	    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyDispatcher);
	}
	
}
