package next_pacage;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RightPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RightPanel() {
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

	}

}
