package homeronfire;

import java.util.List;
import java.io.*;

/**
 * creates a lOC file (=toUpload.loc) and writes into it.
 */
public class LocFileWriter {

	private static FileWriter fileWriter;
	private static BufferedWriter out;

	private Helper helper;

	public void writeLocFile() {
		try {
			helper = new Helper();

			List<Locations> locList = helper.entireLocations();

			fileWriter = new FileWriter("toUpload.loc");
			out = new BufferedWriter(fileWriter);

			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			out.write("<loc version=\"1.0\" src=\"Step By Step\">\n");

			for (Locations loc : locList) {
				out.write("<waypoint>\n");
				out.write("<name id=\"" + loc.getId() + "\"><![CDATA["
						+ loc.getCityName() + "]]>\n");
				out.write("</name>\n");
				out.write("<coord lat=\"" + loc.getLat() + "\" lon=\""
						+ loc.getLon() + "\"/>\n");
				out.write("<type>Geocache</type>\n");
				out.write("</waypoint>\n");
			}

			out.write("</loc>");
			out.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
