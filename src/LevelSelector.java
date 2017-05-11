import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LevelSelector extends JPanel {
    private String[] levelText = {"Easy", "Medium", "Hard", "Back"};
    private Game gamePanel;
    private GameSettings settings;

    public LevelSelector(final CardLayout views, final JPanel mainPanel, GameSettings settings) {
        this.setLayout(new BorderLayout());
        
        this.settings = settings;
        this.gamePanel = null;

        // Make three buttons
        JButton easyBtn = new JButton(levelText[0]);
        JButton medBtn = new JButton(levelText[1]);
        JButton hardBtn = new JButton(levelText[2]);
        JButton backBtn = new JButton(levelText[3]);

        //Set the location of each button
        int btnWidth = 200;
        int btnHeight = 50;
        int startXPos = 400 - btnWidth/2;
        int startYPos = 150;
        easyBtn.setBounds(startXPos, startYPos + 50, btnWidth, btnHeight);
        medBtn.setBounds(startXPos, startYPos + 120, btnWidth, btnHeight);
        hardBtn.setBounds(startXPos, startYPos + 190, btnWidth, btnHeight);
        backBtn.setBounds(startXPos, startYPos + 290, btnWidth, btnHeight);

        //Set button fonts
        Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
        easyBtn.setFont(gameFont);
        medBtn.setFont(gameFont);
        hardBtn.setFont(gameFont);
        backBtn.setFont(gameFont);

        //Set button border
        Border buttonBorder = new LineBorder(Color.BLUE, 2);
        easyBtn.setBorder(buttonBorder);
        medBtn.setBorder(buttonBorder);
        hardBtn.setBorder(buttonBorder);
        backBtn.setBorder(buttonBorder);

        //Set button actions
        easyBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked Easy Button!");
                //gamePanel.setMap(new Map('a'));
                Map newMap = new Map('a');
                // TODO ADD THIS
                Game newGame = new Game(views, mainPanel, newMap, settings);
                try {
                    gamePanel.disableKeys();
                    mainPanel.remove(gamePanel);
                } catch (Exception g) {
                    System.out.println("Nothing to remove");
                }
                gamePanel = newGame;
                mainPanel.add(newGame, "Game");
                views.show(mainPanel, "Game");
                
            }
        });

        medBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked Medium Button!");
                // TODO ADD THIS
                Map newMap = new Map(0,3,2);
                Game newGame = new Game(views, mainPanel, newMap, settings);
                try {
                    mainPanel.remove(gamePanel);
                } catch (Exception g) {
                    System.out.println("Nothing to remove");
                }
                gamePanel = newGame;
                mainPanel.add(newGame, "Game");
                views.show(mainPanel, "Game");
            }
        });

        hardBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked Hard Button!");
                // TODO ADD THIS
                Map newMap = new Map(0,10,5);
                Game newGame = new Game(views, mainPanel, newMap, settings);
                try {
                    mainPanel.remove(gamePanel);
                } catch (Exception g) {
                    System.out.println("Nothing to remove");
                }
                gamePanel = newGame;
                mainPanel.add(newGame, "Game");
                views.show(mainPanel, "Game");
            }
        });

        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked Back Button!");
                views.show(mainPanel, "Menu");
            }
        });

        //Add buttons to this
        this.add(easyBtn);
        this.add(medBtn);
        this.add(hardBtn);
        this.add(backBtn);

        ImageIcon titleImage = new ImageIcon("image/Level_Select_Background.png");
        JLabel  titleLabel = new JLabel("", titleImage, JLabel.CENTER);
        this.add(titleLabel);
        titleLabel.setBounds(0, 0, 800, 600);
    }


}
