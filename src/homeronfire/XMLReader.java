package homeronfire;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * This class is to choose a file with .log extension
 * and convert CDATA nodes to text nodes while parsing 
 */

public class XMLReader {
	static double coordLon;
	static double coordLat;
	static String nameId;
	static File path;

	static Helper link;

	/**
	 * this method allows you to choose the .loc file from in your file
	 * directory system.
	 */
	public static int fileChoose() {
		final JFileChooser choose = new JFileChooser();
		int toReturn = -1;
		int open = choose.showOpenDialog(null);
		if (open == JFileChooser.CANCEL_OPTION) {
			System.out.println("closed");
		} else if (open == JFileChooser.APPROVE_OPTION
				&& choose.getSelectedFile().toString().endsWith(".loc")) {
			toReturn = 0;
			path = choose.getSelectedFile();
			geocacheFileParse();
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"The selected file is not a .loc file, Please choose the\n right file and try again");
			fileChoose();
		}
		return toReturn;
	}

	/**
	 * converts LOC File nodes into Text Nodes while parsing and calls Helper to
	 * stores info
	 */
	public static void geocacheFileParse() {
		try {
			link = new Helper();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(path);
			doc.getDocumentElement().normalize();
			NodeList waypointList = doc.getElementsByTagName("waypoint");

			for (int s = 0; s < waypointList.getLength(); s++) {

				Node waypointNode = waypointList.item(s);

				if (waypointNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elment = (Element) waypointNode;

					NodeList nameList = elment.getElementsByTagName("name");
					Element nameElement = (Element) nameList.item(0);
					NodeList nameChildList = nameElement.getChildNodes();
					nameId = ((Node) nameChildList.item(0)).getNodeValue();

					NodeList coordList = elment.getElementsByTagName("coord");
					Element coordElement = (Element) coordList.item(0);
					coordLon = Double.parseDouble(coordElement
							.getAttribute("lon"));
					coordLat = Double.parseDouble(coordElement
							.getAttribute("lat"));

					System.out
							.println("longitude and latitude of the location "
									+ nameId + " is " + coordLat + " and "
									+ coordLon);

					// persist a location given name, latitude and longitude
					Helper.persistLocations(nameId, coordLat, coordLon);
				}
			}
		} catch (Exception e) {
			System.out.println("init XMLReader");
		}
	}

	public static double getCoordLon() {
		return coordLon;
	}

	public static void setCoordLon(double coordLon) {
		XMLReader.coordLon = coordLon;
	}

	public static double getCoordLat() {
		return coordLat;
	}

	public static void setCoordLat(double coordLat) {
		XMLReader.coordLat = coordLat;
	}

	public static String getNameId() {
		return nameId;
	}

	public static void setNameId(String nameId) {
		XMLReader.nameId = nameId;
	}

}