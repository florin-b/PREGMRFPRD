package tiparire.view;

import javax.swing.JLabel;

public class StatusBar extends JLabel {

	public StatusBar() {

	}

	public StatusBar(String message) {
		setMessage(" " + message);
	}

	public void setMessage(String message) {
		setText(" " + message);
	}
}
