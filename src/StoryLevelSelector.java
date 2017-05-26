import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * Story Level Selector Panel to handle the story levels and progression 
 * 
 * @author Group 1 Tutorial H14A
 *
 */
public class StoryLevelSelector extends JPanel {
    private Models models;

    /**
     * Constructor for Story Level Panel
     * 
     * @param wb as the main game container
     * @param models as the models handler
     */
    public StoryLevelSelector(WarehouseBoss wb, Models models) {
        this.setLayout(new BorderLayout());
        this.models = models;
        ImageIcon bgImage = new ImageIcon("image/" + models.getSpriteSet() + "bg.png");
        JLabel bgLabel = new JLabel("", bgImage, JLabel.CENTER);
        bgLabel.setBounds(0, 0, 800, 600);
        bgLabel.setLayout(new GridBagLayout());

        // Set constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 30, 5);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;

        // Set font and border settings
        Font gameFont = new Font("Myriad Pro Light", Font.BOLD, 20);
        
        Color easyColor = new Color(0, 130, 15);
        Color mediumColor = new Color(215,90,0);
        Color hardColor = new Color(215,0,0);
        
        Border easyBorder = new LineBorder(easyColor, 2);
        Border mediumBorder = new LineBorder(mediumColor, 2);
        Border hardBorder = new LineBorder(hardColor, 2);
        
        Border buttonBorder = new LineBorder(Color.BLUE, 2);


        int counter = 0;
        int y = -1;
        while (counter < 30) {
            if (counter % 10 == 0)
                y++;
            
            gbc.gridx = counter % 10;
            gbc.gridy = y;

            JButton level = new JButton(Integer.toString(counter + 1));
            level.setPreferredSize(new Dimension(60, 60));
            level.setFont(gameFont);
            if (counter < 10){
            	level.setBorder(easyBorder);
                level.setForeground(easyColor);
                
            } else if (counter < 20){
            	level.setBorder(mediumBorder);
                level.setForeground(mediumColor);

            } else {
            	level.setBorder(hardBorder);
                level.setForeground(hardColor);

            }
            
            final int levelNum = counter+1;

            // Use to determine which levels can user play and thus create game
            if (counter <= models.getNumLevelsCleared()) {
                level.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        MapModel newMap = new MapModel(levelNum);
                        StoryLevelSelector.this.models.setMap(newMap);
                        GamePanel gamePanel = new GamePanel(wb, models);

                        StoryLevelSelector.this.models.setGamePanel(gamePanel);
                        wb.addPanel(gamePanel, "Game");
                        wb.swapPanel("Game");

                    }
                });
            } else {
                level.setEnabled(false);
            }

            // Add level button the label
            bgLabel.add(level, gbc);

            counter++;
        }

        // Add bg to the view - Game Panel
        this.add(bgLabel);

        // Add backBtn
        JButton backBtn = new JButton("Back");
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 3;
        gbc.gridwidth = 4;
        gbc.gridy = 4;
        bgLabel.add(backBtn, gbc);

        // Add font and border
        backBtn.setFont(gameFont);
        backBtn.setBorder(buttonBorder);
        backBtn.setPreferredSize(new Dimension(200,50));

        // Add listener
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Clicked Back Button!");
                wb.swapPanel("Menu");
            }
        });
    }
}
