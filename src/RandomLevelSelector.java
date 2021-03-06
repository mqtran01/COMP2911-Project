import java.awt.BorderLayout;
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

/**
 * Level Selector class which contains layout and controller Used to choose a
 * stage
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class RandomLevelSelector extends JPanel {
    private static final String[] BUTTON_TEXT = { "Easy", "Medium", "Hard", "Back"};
    private Models models;

    /**
     * Constructor of the Level Selector object
     * 
     * @param wb as the main game container
     * @param models as the model controller
     */
    public RandomLevelSelector(WarehouseBoss wb, Models models) {
        this.setLayout(new BorderLayout());
        this.models = models;
        
        // Make selection buttons
        JButton easyBtn = new JButton(BUTTON_TEXT[0]);
        JButton medBtn = new JButton(BUTTON_TEXT[1]);
        JButton hardBtn = new JButton(BUTTON_TEXT[2]);
        JButton backBtn = new JButton(BUTTON_TEXT[3]);

        // Set the location of each button
        int btnWidth = 200;
        int btnHeight = 50;
        int startXPos = 400 - btnWidth / 2;
        int startYPos = 150;
        easyBtn.setBounds(startXPos, startYPos + 50, btnWidth, btnHeight);
        medBtn.setBounds(startXPos, startYPos + 120, btnWidth, btnHeight);
        hardBtn.setBounds(startXPos, startYPos + 190, btnWidth, btnHeight);
        backBtn.setBounds(startXPos, startYPos + 290, btnWidth, btnHeight);

        // Set button fonts
        Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
        easyBtn.setFont(gameFont);
        medBtn.setFont(gameFont);
        hardBtn.setFont(gameFont);
        backBtn.setFont(gameFont);


        // Set button border
        Border buttonBorder = new LineBorder(Color.BLUE, 2);
        easyBtn.setBorder(buttonBorder);
        medBtn.setBorder(buttonBorder);
        hardBtn.setBorder(buttonBorder);
        backBtn.setBorder(buttonBorder);

        // Set button actions
        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapModel newMap = new MapModel('e');
                RandomLevelSelector.this.models.setMap(newMap);
                GamePanel newGame = new GamePanel(wb, RandomLevelSelector.this.models);
                wb.addPanel(newGame, "Game");
                wb.swapPanel("Game");

            }
        });

        medBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapModel newMap = new MapModel('m');
                RandomLevelSelector.this.models.setMap(newMap);
                GamePanel newGame = new GamePanel(wb, RandomLevelSelector.this.models);
                wb.addPanel(newGame, "Game");
                wb.swapPanel("Game");
            }
        });

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapModel newMap = new MapModel('h');
                RandomLevelSelector.this.models.setMap(newMap);
                GamePanel newGame = new GamePanel(wb, RandomLevelSelector.this.models);
                wb.addPanel(newGame, "Game");
                wb.swapPanel("Game");
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wb.swapPanel("Menu");
            }
        });

        // Add buttons to this
        this.add(easyBtn);
        this.add(medBtn);
        this.add(hardBtn);
        this.add(backBtn);

        ImageIcon bgImage = new ImageIcon("image/" + models.getSpriteSet() + "Level_Select_Bg.png");
        JLabel bgLabel = new JLabel("", bgImage, JLabel.CENTER);
        this.add(bgLabel);
        bgLabel.setBounds(0, 0, 800, 600);
    }

}
