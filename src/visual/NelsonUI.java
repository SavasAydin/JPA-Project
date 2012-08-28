package visual;

import homeronfire.XMLReader;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class NelsonUI extends JFrame {

	private static final long serialVersionUID = 6465673678998511967L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XMLReader.fileChoose();

					MainFrame mainFrame = new MainFrame("Nelson's Project");
					mainFrame.setVisible(true);
					mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					mainFrame.setSize(500, 450);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
