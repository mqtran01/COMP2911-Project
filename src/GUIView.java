import java.util.HashMap;

import javax.swing.JPanel;

public class GUIView {

	private static GUIView view;
	private HashMap<String, JPanel> panels;
	private String currentPanel = "";
	
	private GUIView() {
		panels = new HashMap<String, JPanel>();
	}
	
	public static GUIView getInstance() {
		if (view == null) {
			view = new GUIView();
		}
		return view;
	}
	
	public void add(String panelName, JPanel panel) {
		panels.put(panelName, panel);
	}
	
	public void setCurrentPanel(String panelName) {
		currentPanel = panelName;
	}

}
