package visual;

import homeronfire.Helper;
import homeronfire.Locations;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

public class TopPanel extends JPanel {

	private static final long serialVersionUID = -8818978876142605444L;

	Helper link = new Helper();

	private String[] cityNames;
	private ArrayList<String> names;
	private ArrayList<Double> lats;
	private ArrayList<Double> lons;
	private ArrayList<Long> ids;

	public Long id;

	public JComboBox combo;
	public DefaultComboBoxModel model;
	private JTextField latArea;
	private JTextField lonArea;
	private JLabel latLabel;
	private JLabel lonLabel;

	private EventListenerList listenerList = new EventListenerList();

	/**
	 * Create the top panel.
	 */
	public TopPanel() {

		Dimension size = getPreferredSize();
		size.height = 200;
		setPreferredSize(size);

		// set border
		setBorder(BorderFactory.createTitledBorder("Locations Information"));

		// create components of TopPanel
		model = new DefaultComboBoxModel(comboItems());
		combo = new JComboBox(model);

		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Locations selectedLoc = new Locations();

				ids = new ArrayList<Long>();
				ids = link.getIds();

				int index = combo.getSelectedIndex();

				if (index == 0) {
					JOptionPane.showMessageDialog(null, "Please choose a loc");
				} else {
					lats = new ArrayList<Double>();
					lats = link.getLats();
					latArea.setText(Double.toString(lats.get(index - 1)));

					lons = new ArrayList<Double>();
					lons = link.getLons();
					lonArea.setText(Double.toString(lons.get(index - 1)));

					id = ids.get(index - 1);
					selectedLoc = link.findLocationById(id);

					fireTopEvent(new TopEvent(this, selectedLoc));
				}
			}
		});

		latArea = new JTextField(10);
		lonArea = new JTextField(10);

		latLabel = new JLabel("Latitude value");
		lonLabel = new JLabel("Longitude value");

		// set layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.ipadx = 200;
		gc.ipady = 30;
		gc.gridwidth = 2;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		add(combo, gc);

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.ipady = 10;
		gc.gridwidth = 1;

		gc.gridx = 0;
		gc.gridy = 3;
		add(latLabel, gc);

		gc.gridx = 1;
		gc.gridy = 3;
		add(lonLabel, gc);

		gc.gridx = 0;
		gc.gridy = 4;
		add(latArea, gc);

		gc.gridx = 1;
		gc.gridy = 4;
		add(lonArea, gc);

	}

	public void fireTopEvent(TopEvent event) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == TopListener.class) {
				((TopListener) listeners[i + 1]).topEventOccurred(event);
			}
		}
	}

	public void addTopListener(TopListener listener) {
		listenerList.add(TopListener.class, listener);
	}

	public void removeTopListener(TopListener listener) {
		listenerList.remove(TopListener.class, listener);
	}

	public String[] comboItems() {
		names = new ArrayList<String>();
		names = link.getNames();
		cityNames = new String[names.size() + 1];
		cityNames[0] = "Please select a location";
		for (int i = 0; i < names.size(); i++) {
			cityNames[i + 1] = names.get(i);
		}
		return cityNames;
	}

}
