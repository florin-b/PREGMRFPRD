package tiparire.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Utils {

	public static String getDepartCode(String departName) {
		String depCode = "00";

		if (departName.toLowerCase().substring(0, 4).equals("lemn"))
			depCode = "01";

		if (departName.toLowerCase().substring(0, 4).equals("fero"))
			depCode = "02";

		if (departName.toLowerCase().substring(0, 4).equals("parc"))
			depCode = "03";

		if (departName.toLowerCase().substring(0, 4).equals("mate"))
			depCode = "04";

		if (departName.toLowerCase().substring(0, 4).equals("elec"))
			depCode = "05";

		if (departName.toLowerCase().substring(0, 4).equals("gips"))
			depCode = "06";

		if (departName.toLowerCase().substring(0, 4).equals("chim"))
			depCode = "07";

		if (departName.toLowerCase().substring(0, 4).equals("inst"))
			depCode = "08";

		if (departName.toLowerCase().substring(0, 4).equals("hidr"))
			depCode = "09";

		return depCode;
	}

	public static String getFullDepartName(String departName) {
		String depName = "00";

		if (departName.toLowerCase().substring(0, 4).equals("lemn"))
			depName = "Lemnoase";

		if (departName.toLowerCase().substring(0, 4).equals("fero"))
			depName = "Feronerie";

		if (departName.toLowerCase().substring(0, 4).equals("parc"))
			depName = "Parchet";

		if (departName.toLowerCase().substring(0, 4).equals("mate"))
			depName = "Materiale grele";

		if (departName.toLowerCase().substring(0, 4).equals("elec"))
			depName = "Electrice";

		if (departName.toLowerCase().substring(0, 4).equals("gips"))
			depName = "Gips";

		if (departName.toLowerCase().substring(0, 4).equals("chim"))
			depName = "Chimice";

		if (departName.toLowerCase().substring(0, 4).equals("inst"))
			depName = "Instalatii";

		if (departName.toLowerCase().substring(0, 4).equals("hidr"))
			depName = "Hidroizolatii";

		return depName;
	}

	public static ImageIcon createIcon(URL path) {
		ImageIcon icon = new ImageIcon(path);
		return icon;
	}

	public static Font createFont(String path) {
		URL url = System.class.getClass().getResource(path);

		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		return font;
	}

	public static String[] getUserDepartaments() {

		List<String> departaments = new ArrayList<String>();

		String userDep = UserInfo.getInstance().getInitDepart();

		if (userDep.equals("TOAT")) {
			departaments.add("Lemnoase");
			departaments.add("Feronerie");
			departaments.add("Parchet");
			departaments.add("Materiale grele");
			departaments.add("Electrice");
			departaments.add("Gips");
			departaments.add("Chimice");
			departaments.add("Instalatii");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("CIEL")) {
			departaments.add("Chimice");
			departaments.add("Instalatii");
		}

		if (userDep.equals("FEHI")) {
			departaments.add("Feronerie");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("LEFA")) {
			departaments.add("Lemnoase");
			departaments.add("Feronerie");
		}

		if (userDep.equals("DEP1")) {
			departaments.add("Gips");
			departaments.add("Lemnoase");
			departaments.add("Parchet");
			departaments.add("Chimice");
		}

		if (userDep.equals("DEP2")) {
			departaments.add("Hidroizolatii");
			departaments.add("Instalatii");
		}

		if (userDep.equals("DEP3")) {
			departaments.add("Chimice");
			departaments.add("Electrice");
		}

		if (userDep.equals("DEP4")) {
			departaments.add("Parchet");
			departaments.add("Gips");
		}

		if (userDep.equals("DEP5")) {
			departaments.add("Electrice");
			departaments.add("Instalatii");
		}

		if (userDep.equals("DEP6")) {
			departaments.add("Materiale grele");
			departaments.add("Instalatii");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("DEP7")) {
			departaments.add("Gips");
			departaments.add("Electrice");
		}

		if (userDep.equals("DEP8")) {
			departaments.add("Parchet");
			departaments.add("Chimice");
		}

		if (userDep.equals("DEP9")) {
			departaments.add("Gips");
			departaments.add("Lemnoase");
			departaments.add("Parchet");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("DP10")) {
			departaments.add("Parchet");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("DP11")) {
			departaments.add("Gips");
			departaments.add("Chimice");
		}

		if (userDep.equals("DP13")) {
			departaments.add("Instalatii");
			departaments.add("Chimice");
		}

		if (userDep.equals("DP15")) {
			departaments.add("Electrice");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("DP16")) {
			departaments.add("Lemnoase");
			departaments.add("Chimice");
			departaments.add("Materiale grele");
		}

		if (userDep.equals("DP19")) {
			departaments.add("Hidroizolatii");
			departaments.add("Gips");
		}

		if (userDep.equals("DP20")) {
			departaments.add("Parchet");
			departaments.add("Feronerie");
		}

		if (userDep.equals("DP26")) {
			departaments.add("Feronerie");
			departaments.add("Instalatii");
		}

		if (userDep.equals("DP29")) {
			departaments.add("Feronerie");
			departaments.add("Electrice");
		}

		if (userDep.equals("DP30")) {
			departaments.add("Lemnoase");
			departaments.add("Gips");
		}

		if (userDep.equals("DP37")) {
			departaments.add("Chimice");
			departaments.add("Materiale grele");
		}

		if (userDep.equals("DP38")) {
			departaments.add("Lemnoase");
			departaments.add("Chimice");
			departaments.add("Instalatii");
		}

		if (userDep.equals("DP41")) {
			departaments.add("Parchet");
			departaments.add("Electrice");
		}

		if (userDep.equals("DP40")) {
			departaments.add("Parchet");
			departaments.add("Materiale grele");
		}

		if (userDep.equals("DP42")) {
			departaments.add("Parchet");
			departaments.add("Gips");
			departaments.add("Chimice");
			departaments.add("Instalatii");
		}

		if (userDep.equals("DP43")) {
			departaments.add("Parchet");
			departaments.add("Gips");
			departaments.add("Chimice");
		}

		if (userDep.equals("DP44")) {
			departaments.add("Feronerie");
			departaments.add("Hidroizolatii");
			departaments.add("Electrice");
		}

		if (userDep.equals("DP45")) {
			departaments.add("Electrice");
			departaments.add("Materiale grele");
		}

		if (userDep.equals("DP46")) {
			departaments.add("Materiale grele");
			departaments.add("Gips");
			departaments.add("Chimice");
		}

		if (userDep.equals("DP48")) {
			departaments.add("Materiale grele");
			departaments.add("Gips");
			departaments.add("Chimice");
			departaments.add("Hidroizolatii");
		}

		if (userDep.equals("DP47")) {
			departaments.add("Lemnoase");
			departaments.add("Parchet");
			departaments.add("Electrice");
			departaments.add("Instalatii");
			departaments.add("Feronerie");

		}

		if (userDep.equals("DP51")) {
			departaments.add("Materiale grele");
			departaments.add("Gips");
			departaments.add("Chimice");
			departaments.add("Parchet");
		}

		if (userDep.equals("DP55")) {
			departaments.add("Lemnoase");
			departaments.add("Feronerie");
			departaments.add("Instalatii");
			departaments.add("Hidroizolatii");

		}

		if (userDep.equals("DP58")) {
			departaments.add("Gips");
			departaments.add("Feronerie");
		}

		String[] depArray = departaments.toArray(new String[departaments.size()]);

		return depArray;
	}

	public static String[] getFiliale() {
		return new String[] { "AG10", "BC10", "BH10", "BU10", "BU11", "BU12", "BU13", "BV10", "BZ10", "CJ10", "CT10",
				"DJ10", "GL10", "HD10", "IS10", "MM10", "MS10", "NT10", "PH10", "SB10", "TM10", "VN10" };
	}

}
