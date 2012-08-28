package visual;

import homeronfire.LocFileWriter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Creates the panel located south and includes 3 buttons; "Add", "Delete",
 * and "Update".
 */
public class BottomMainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3725270582598809572L;

	public JButton addButton;
	public JButton deleteButton;
	public JButton writeButton;

	private LocFileWriter writer;
	
	public BottomMainPanel() {
		Dimension size = getPreferredSize();
		size.height = 200;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Edit Locations"));

		addButton = new JButton("Add");
		addButton.setActionCommand("add");
		addButton.setToolTipText("Click to add new location to db");

		deleteButton = new JButton("Delete");
		deleteButton.setToolTipText("Click to delete location shown from db");

		writeButton = new JButton("Write");
		writeButton
				.setToolTipText("Click to write locations into upload.log file");

		writeButton.addActionListener(this);

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.ipadx = 40;
		gc.ipady = 20;
		gc.gridx = 0;
		gc.gridy = 0;
		add(addButton, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(deleteButton, gc);

		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 1;
		add(writeButton, gc);
	}

	public void actionPerformed(ActionEvent arg0) {
		writer = new LocFileWriter();
		writer.writeLocFile();
		JOptionPane.showMessageDialog(null,
				"toUpload.log file is written under the directory of project");
	}
}
