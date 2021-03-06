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

/**
 * Game Panel class which contains the gameplay visuals and controller
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class GamePanel extends JPanel {
    private Models models;

    private JLabel[][] grid;
    private int length;
    private int height;
    private KeyEventDispatcher keyDispatcher;

    // Music assets
    private String m_footsteps = "MusicFootsteps.wav";
    private String m_moveBox = "MusicMoveBox.wav";
    private String m_winGame = "MusicWinGame.wav";

    // Additional assets for player facing direction
    final static int PLAYER_UP = 7;
    final static int PLAYER_DOWN = 8;
    final static int PLAYER_LEFT = 9;
    final static int PLAYER_RIGHT = 10;
    final static int GOALPLAYER_UP = 11;
    final static int GOALPLAYER_DOWN = 12;
    final static int GOALPLAYER_LEFT = 13;
    final static int GOALPLAYER_RIGHT = 14;
    
    /**
     * 
     * @param wb as the main game container
     * @param map as the map to play
     * @param models as the models handler
     */
    public GamePanel(WarehouseBoss wb, Models models) {
        this.models = models;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        length = models.getLength();
        height = models.getHeight();

        // Create grid layout
        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        grid = new JLabel[length][height];

        // Resize image objects dynamically according to grid size
        double scaledLen = 800 / length;
        double scaledHgt = 500 / height;
        int proportion = (int) Math.min(scaledLen, scaledHgt);

        ImageIcon wall = resizedImage(MapModel.WALL, proportion);
        ImageIcon empty = resizedImage(MapModel.EMPTY, proportion);
        ImageIcon player = resizedImage(MapModel.PLAYER, proportion);
        ImageIcon box = resizedImage(MapModel.BOX, proportion);
        ImageIcon goal = resizedImage(MapModel.GOAL, proportion);
        ImageIcon goalBox = resizedImage(MapModel.GOALBOX, proportion);
        ImageIcon goalPlayer = resizedImage(MapModel.GOALPLAYER, proportion);

        // Draws the map visually
        for (int y = 0; y < height; y++) {
            gbc.gridy = y;
            for (int x = 0; x < length; x++) {
                gbc.gridx = x;
                int tileItem = models.getTile(x, y);
                ImageIcon img = null;
                switch (tileItem) {
                case MapModel.WALL:
                    img = wall;
                    break;
                case MapModel.EMPTY:
                    img = empty;
                    break;
                case MapModel.PLAYER:
                    img = player;
                    break;
                case MapModel.BOX:
                    img = box;
                    break;
                case MapModel.GOAL:
                    img = goal;
                    break;
                case MapModel.GOALBOX:
                    img = goalBox;
                    break;
                case MapModel.GOALPLAYER:
                    img = goalPlayer;
                    break;
                }
                grid[x][y] = new JLabel("", img, JLabel.CENTER);
                grid[x][y].setPreferredSize(new Dimension(proportion, proportion));
                gridPanel.add(grid[x][y], gbc);
            }
        }

        // Creation of button elements
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 2, 2));
        Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
        Border border = new LineBorder(Color.BLUE, 2);

        JButton saveBtn = new JButton("Save");
        saveBtn.setFont(gameFont);
        saveBtn.setBorder(border);
        saveBtn.setPreferredSize(new Dimension(150, 50));

        JButton undoBtn = new JButton("Undo");
        undoBtn.setFont(gameFont);
        undoBtn.setBorder(border);
        undoBtn.setPreferredSize(new Dimension(150, 50));

        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(gameFont);
        resetBtn.setBorder(border);
        resetBtn.setPreferredSize(new Dimension(150, 50));

        JButton quitBtn = new JButton("Main Menu");
        quitBtn.setFont(gameFont);
        quitBtn.setBorder(border);
        quitBtn.setPreferredSize(new Dimension(150, 50));

        JButton tuteBtn = new JButton("?");
        tuteBtn.setBounds(20, 527, 30, 30);
        tuteBtn.setFont(gameFont);
        tuteBtn.setBorder(border);
        tuteBtn.setBackground(Color.white);
        tuteBtn.setOpaque(true);

        ImageIcon tuteImage = new ImageIcon("image/tutorial.png");
        JLabel tuteLabel = new JLabel("", tuteImage, JLabel.CENTER);
        tuteLabel.setBounds(0, 0, 800, 600);

        // Set a border to grid
        gridPanel.setBorder(border);

        // Creation of button action
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveLoad.save(GamePanel.this.models.getMap());
                    JOptionPane.showMessageDialog(null, 
                            "          Your progress has been saved!", "Game Saved!",
                            JOptionPane.PLAIN_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.this.models.undo();
                update("s");
            }
        });

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.this.models.reset();
                update("s");
            }
        });

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableKeys();
                wb.swapPanel("Menu");
            }
        });

        // Add buttons onto buttonPanel
        btnPanel.add(saveBtn);
        btnPanel.add(undoBtn);
        btnPanel.add(resetBtn);
        btnPanel.add(quitBtn);

        // Gets the file names of sounds
        String path = "assets/" + models.getSpriteSet();
        m_footsteps = path + m_footsteps;
        m_moveBox = path + m_moveBox;
        m_winGame = path + m_winGame;

        this.keyDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent key) {
                if (key.getID() == KeyEvent.KEY_PRESSED) {
                    String keyPressed = null;
                    //System.out.println(key.getKeyCode());

                    if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_UP) {
                        GamePanel.this.models.moveUp();
                        //System.out.println("music is " + models.isEnableMusic());
                        playSound(m_footsteps);
                        keyPressed = "w";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_S || key.getKeyCode() == KeyEvent.VK_DOWN) {
                        GamePanel.this.models.moveDown();
                        playSound(m_footsteps);
                        keyPressed = "s";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_A || key.getKeyCode() == KeyEvent.VK_LEFT) {
                        GamePanel.this.models.moveLeft();
                        playSound(m_footsteps);
                        keyPressed = "a";
                    }
                    if (key.getKeyCode() == KeyEvent.VK_D || key.getKeyCode() == KeyEvent.VK_RIGHT) {
                        GamePanel.this.models.moveRight();
                        playSound(m_footsteps);
                        keyPressed = "d";
                    }
                    update(keyPressed);

                    if (GamePanel.this.models.winState()) {
                        disableKeys();
                        playSound(m_winGame);
                        int level = GamePanel.this.models.getMapLevel();
                        if (level == -1) {
                            Object[] options = { "Play Again?", "Main Menu" };
                            int n = JOptionPane.showOptionDialog(null, "              Congratulations on winning!",
                                    "You have won!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null,
                                    options, options[1]);
                            JOptionPane.getRootFrame().dispose();
                            if (n == JOptionPane.NO_OPTION) {
                                //System.out.println("Clicked Main Menu!");
                                wb.swapPanel("Menu");
                            }
                            if (n == JOptionPane.YES_OPTION) {
                                //System.out.println("Clicked Play Again!");
                                wb.swapPanel("Random");
                            }
                        } else {
                            GamePanel.this.models.setNumLevelsCleared(level);

                            // Recreate StoryLevelSelector and SettingsPanel to update number of
                            // to update number of completed levels
                            wb.addPanel(new StoryLevelSelector(wb, GamePanel.this.models), "Story");
                            wb.addPanel(new SettingsPanel(wb, GamePanel.this.models), "Settings");
                            
                            // Save progress
                            try {
                                SaveLoad.saveSettings(GamePanel.this.models.getSettings());
                            } catch (IOException e1) {
                            }

                            Object[] options = { "Play Next?", "Main Menu" };
                            int n = JOptionPane.showOptionDialog(null, 
                                    "              Congratulations on winning!",
                                    "You have won!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null,
                                    options, options[1]);
                            JOptionPane.getRootFrame().dispose();
                            if (n == JOptionPane.NO_OPTION) {
                                wb.swapPanel("Menu");
                            }
                            if (n == JOptionPane.YES_OPTION) {
                                wb.swapPanel("Story");
                            }
                        }
                    }
                }
                return false;
            }
        };

        // add in keyboard controls
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyDispatcher);

        // Add bg image
        ImageIcon bgImage = new ImageIcon("image/" + models.getSpriteSet() + "bg.png");
        JLabel bgLabel = new JLabel("", bgImage, JLabel.CENTER);
        bgLabel.setBounds(0, 0, 800, 600);
        bgLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        this.add(tuteBtn);
        // Add bg to the view - Game Panel
        this.add(bgLabel);

        // Add grid and buttons to bg
        bgLabel.add(gridPanel);
        bgLabel.add(btnPanel);

        tuteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, tuteLabel, 
                        "Tutorial", JOptionPane.PLAIN_MESSAGE, null);
            }
        });

    }

    /**
     * Plays the movement sound file
     * 
     * @param filename as the file path of the sound file
     */
    private void playSound(String filename) {
        Thread musicThread = new Thread() {
            @Override
            public void run() {
                try {
                    // Open an audio input stream.
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filename));
                    // Get a sound clip resource.
                    Clip clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input
                    // stream.
                    clip.open(audioIn);
                    clip.start();
                } catch (Exception e) {
                }
            }
        };
        if (models.isEnableSFX()) {
            //System.out.println("SFX enabled");
            musicThread.start();
        }
    }

    /**
     * Resizes the image based on the map size to fit on window
     * 
     * @param item as the map element
     * @param size as the size to scale to
     * @return the rescaled image
     */
    private ImageIcon resizedImage(int item, int size) {

        String imgLoc = null;
        if (models.isSkin1()) {
            imgLoc = imageSelector("player", item);
        } else {
            imgLoc = imageSelector("player2", item);
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
     * Method for updating the sprites displayed by the grid
     * 
     * @param direction as the direction the sprite is facing
     */
    public void update(String direction) {
        double scaledLen = 800 / length;
        double scaledHgt = 500 / height;

        int proportion = (int) Math.min(scaledLen, scaledHgt);
        ImageIcon wall = resizedImage(MapModel.WALL, proportion);
        ImageIcon empty = resizedImage(MapModel.EMPTY, proportion);
        ImageIcon box = resizedImage(MapModel.BOX, proportion);
        ImageIcon goal = resizedImage(MapModel.GOAL, proportion);
        ImageIcon goalBox = resizedImage(MapModel.GOALBOX, proportion);
        ImageIcon goalPlayer = resizedImage(MapModel.GOALPLAYER, proportion);
        ImageIcon player = resizedImage(MapModel.PLAYER, proportion);

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

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                int tileItem = models.getTile(x, y);
                ImageIcon img = null;
                switch (tileItem) {
                case MapModel.WALL:
                    img = wall;
                    break;
                case MapModel.EMPTY:
                    img = empty;
                    break;
                case MapModel.PLAYER:
                    img = player;
                    break;
                case MapModel.BOX:
                    img = box;
                    break;
                case MapModel.GOAL:
                    img = goal;
                    break;
                case MapModel.GOALBOX:
                    img = goalBox;
                    break;
                case MapModel.GOALPLAYER:
                    img = goalPlayer;
                    break;
                }
                grid[x][y].setIcon(img);
            }
        }
    }

    /**
     * Method to select images of objects according to skin and spriteSet active
     * 
     * @param skin as current active skin
     * @param item as the item
     * @return String as the path for the image object
     */
    private String imageSelector(String skin, int item) {
        String path = "image/" + models.getSpriteSet();
        String imgLoc = null;
        switch (item) {
        case MapModel.WALL:
            imgLoc = path + "Wall.png";
            break;
        case MapModel.EMPTY:
            imgLoc = path + "Empty.png";
            break;
        case MapModel.PLAYER:
            imgLoc = path + skin + "Down.png";
            break;
        case MapModel.BOX:
            imgLoc = path + "Box.png";
            break;
        case MapModel.GOAL:
            imgLoc = path + "Goal.png";
            break;
        case MapModel.GOALBOX:
            imgLoc = path + "GoalBox.png";
            break;
        case MapModel.GOALPLAYER:
            imgLoc = path + skin + "DownGoal.png";
            break;
        case PLAYER_UP:
            imgLoc = path + skin + "Up.png";
            break;
        case PLAYER_DOWN:
            imgLoc = path + skin + "Down.png";
            break;
        case PLAYER_LEFT:
            imgLoc = path + skin + "Left.png";
            break;
        case PLAYER_RIGHT:
            imgLoc = path + skin + "Right.png";
            break;
        case GOALPLAYER_UP:
            imgLoc = path + skin + "UpGoal.png";
            break;
        case GOALPLAYER_DOWN:
            imgLoc = path + skin + "DownGoal.png";
            break;
        case GOALPLAYER_LEFT:
            imgLoc = path + skin + "LeftGoal.png";
            break;
        case GOALPLAYER_RIGHT:
            imgLoc = path + skin + "RightGoal.png";
            break;
        }
        return imgLoc;
    }

    /**
     * Disables the key controller
     */
    public void disableKeys() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyDispatcher);
    }

}
