package visual;

import homeronfire.Helper;
import homeronfire.Locations;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Main  frame includes TopPanel and BottomPanel.
 * BottomPanel has CardLayout where you can switch
 * between panels added in; BottomMainPanel and BottomAddPanel 
 */
public class MainFrame extends JFrame implements TopListener, InsertListener {

	private static final long serialVersionUID = 9118079743164571702L;

	private TopPanel topPanel;
	private BottomMainPanel bottomMainPanel;
	private BottomAddPanel bottomAddPanel;
	private CardLayout cardLayout;
	private JPanel bottomPanel;

	private Helper helper;

	private Locations loc;
	private String nameAdded;

	public MainFrame(String title) {
		super(title);

		setLayout(new BorderLayout());

		topPanel = new TopPanel();
		bottomMainPanel = new BottomMainPanel();
		bottomAddPanel = new BottomAddPanel();
		bottomPanel = new JPanel();
		cardLayout = new CardLayout();
		bottomPanel.setLayout(cardLayout);

		topPanel.addTopListener(this);
		bottomAddPanel.addInsertListener(this);

		bottomPanel.add(bottomMainPanel, "Allbuttons");
		bottomPanel.add(bottomAddPanel, "toAdd");

		bottomMainPanel.addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(bottomPanel, "toAdd");
			}
		});

		bottomAddPanel.back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(bottomPanel, "Allbuttons");
				if (nameAdded != null) {
					topPanel.model.addElement(nameAdded);
				}
			}
		});

		bottomMainPanel.deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Object[] options = { "Yes", "No" };
					int value = JOptionPane.showOptionDialog(null,
							"Do you really want to delete " + loc.getCityName()
									+ "?", "Delete Process",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);

					if (value == JOptionPane.YES_OPTION) {
						helper = new Helper();
						helper.deleteLocation(loc.getId());
						JOptionPane.showMessageDialog(null,
								"Deleted Locations is " + loc.getCityName());
						topPanel.model.removeElement(loc.getCityName());
					}
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null,
							"Please choose a location to delete");
				}
			}
		});

		Container container = getContentPane();

		container.add(topPanel, BorderLayout.NORTH);
		container.add(bottomPanel, BorderLayout.SOUTH);
	}

	@Override
	public void topEventOccurred(TopEvent event) {
		loc = new Locations();
		loc = event.getLoc();
	}

	@Override
	public void insertEventOccurred(InsertEvent event) {
		nameAdded = event.getText();
	}

}
