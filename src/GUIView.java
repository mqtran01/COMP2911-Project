import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Method to switch between different views:
 * Menu, Game, Settings, etc
 * Idea is to for each action item in each view should
 * check the currentPanel using getCurrentPanelKey and if it
 * matches the view it getCurrentPanel and add it to the frame
 *
 */
public class GUIView {

	private static GUIView view;
	private HashMap<String, JPanel> panels;
	private String currentPanel = "";
	
	/**
	 * Constructor to create HashMap
	 */
	private GUIView() {
		panels = new HashMap<String, JPanel>();
	}
	
	/**
	 * Method to obtain the get the current view
	 * @return GUIView
	 */
	public static GUIView getInstance() {
		if (view == null) {
			view = new GUIView();
		}
		return view;
	}
	
	/**
	 * Method to add a panel to the library of panels
	 * @param panelName The key for the panel
	 * @param panel The panel
	 */
	public void add(String panelName, JPanel panel) {
		panels.put(panelName, panel);
		setCurrentPanel(panelName);
	}
	
	/**
	 * Method to change the current panel
	 * @param panelName The key which will replace 
	 * the current panel
	 */
	public void setCurrentPanel(String panelName) {
		currentPanel = panelName;
	}
	
	/**
	 * Method to get the current Panel being displayed
	 * @return JPanel
	 */
	public JPanel getCurrentPanel() {
		if (panels.get(currentPanel) != null) {
			return panels.get(currentPanel);
		}
		return null;
	}
	
	/**
	 * Method to get the key of the Panel currently been displayed
	 * @return String
	 */
	public String getCurrentPanelKey() {
		return currentPanel;
	}

}
