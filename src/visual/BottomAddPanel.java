package visual;

import homeronfire.Helper;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

/*
 * creates  where users type
 * new location name with coordinates
 * to be saved.
 */
public class BottomAddPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7657589203307346567L;

	private JTextField addName;
	private JTextField addLat;
	private JTextField addLon;
	private JLabel latLabel;
	private JLabel lonLabel;
	private JLabel nameLabel;
	public JButton save;
	public JButton back;

	private EventListenerList listenerList = new EventListenerList();

	public BottomAddPanel() {
		Dimension size = getPreferredSize();
		size.height = 200;
		setPreferredSize(size);

		// set border
		setBorder(BorderFactory.createTitledBorder("Add new Location"));

		// create swing components
		addName = new JTextField(20);
		addLat = new JTextField(20);
		addLon = new JTextField(20);

		latLabel = new JLabel("Enter latitude value");
		lonLabel = new JLabel("Enter longitude value");
		nameLabel = new JLabel("Enter city name");

		save = new JButton("Save");
		save.setToolTipText("Click to save locations");
		back = new JButton("Back");
		back.setToolTipText("Click to go back to main menu");

		// listen save button
		save.addActionListener(this);

		// set layout
		setLayout(new GridBagLayout());
		GridBagConstraints gcAdd = new GridBagConstraints();
		gcAdd.weightx = 0.5;
		gcAdd.weighty = 0.5;
		gcAdd.ipady = 10;
		gcAdd.anchor = GridBagConstraints.LINE_START;
		// add nameLabel
		gcAdd.gridx = 0;
		gcAdd.gridy = 0;
		add(nameLabel, gcAdd);
		// add name field
		gcAdd.anchor = GridBagConstraints.LINE_END;
		gcAdd.gridx = 1;
		gcAdd.gridy = 0;
		add(addName, gcAdd);
		// add latitude label
		gcAdd.ipadx = 0;
		gcAdd.anchor = GridBagConstraints.LINE_START;
		gcAdd.gridx = 0;
		gcAdd.gridy = 1;
		add(latLabel, gcAdd);
		// add latitude field
		gcAdd.anchor = GridBagConstraints.LINE_END;
		gcAdd.gridx = 1;
		gcAdd.gridy = 1;
		add(addLat, gcAdd);
		// add longitude label
		gcAdd.anchor = GridBagConstraints.LINE_START;
		gcAdd.gridx = 0;
		gcAdd.gridy = 2;
		add(lonLabel, gcAdd);
		// add longitude field
		gcAdd.anchor = GridBagConstraints.LINE_END;
		gcAdd.gridx = 1;
		gcAdd.gridy = 2;
		add(addLon, gcAdd);
		// add back button to mainmenu
		gcAdd.gridx = 1;
		gcAdd.gridy = 3;
		add(back, gcAdd);
		// add save button
		gcAdd.gridx = 2;
		gcAdd.gridy = 3;
		add(save, gcAdd);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (isDouble(addLat.getText()) == false
					|| isDouble(addLon.getText()) == false) {
				JOptionPane.showMessageDialog(null,
						"Please enter double for latitude and longitute");
			} else {
				String name = addName.getText();
				double lat = Double.parseDouble(addLat.getText());
				double lon = Double.parseDouble(addLon.getText());

				Helper.persistLocations(name, lat, lon);

				JOptionPane.showMessageDialog(null, "Location name: " + name
						+ " has been added");
				fireInsertEvent(new InsertEvent(this, name));
			}

			addLat.setText("");
			addLon.setText("");
			addName.setText("");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fireInsertEvent(InsertEvent event) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == InsertListener.class) {
				((InsertListener) listeners[i + 1]).insertEventOccurred(event);
			}
		}
	}

	public void addInsertListener(InsertListener listener) {
		listenerList.add(InsertListener.class, listener);
	}

	public void removeTopListener(InsertListener listener) {
		listenerList.remove(InsertListener.class, listener);
	}

	public boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}