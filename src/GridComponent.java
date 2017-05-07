import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridComponent extends JPanel{
	private final int x;
	private final int y;
	private static final int SIZE=42;
	
	public GridComponent(int xPos, int yPos, int val) {
		this.x=xPos;
		this.y=yPos;
		JButton btn = new JButton(Integer.toString(val));
		//btn.setPreferredSize(new Dimension(SIZE,SIZE));
		this.add(btn);
	}
}

