package tiparire.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import DateUtils.DateUtils;

public class AlertaEmitere {

	public int getAlertaEmitere(Document document) {
		int nrOre = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm", new Locale("ro"));

		try {
			long diff = getStopProgram(document.getFiliala(), new Date()).getTime()
					- sdf.parse(document.getDataEmiterii()).getTime();

			long diffDays = diff / 86400000L;

			Date iDate = null;
			for (int i = 0; i < (int) diffDays; i++) {

				iDate = DateUtils.addDays(sdf.parse(document.getDataEmiterii()), i);

				if (i == 0) {
					nrOre = DateUtils.diffHours(sdf.parse(document.getDataEmiterii()),
							getStopProgram(UserInfo.getInstance().getUnitLog().substring(0, 2),
									sdf.parse(document.getDataEmiterii())));
				} else {
					nrOre += DateUtils.diffHours(getStartProgram(iDate),
							getStopProgram(UserInfo.getInstance().getUnitLog().substring(0, 2), iDate));
				}
			}

			if (diffDays == 0L) {
				nrOre += DateUtils.diffHours(sdf.parse(document.getDataEmiterii()), new Date());
			} else {
				nrOre += DateUtils.diffHours(getStartProgram(new Date()), new Date());
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Document: " + document);
		}

		return nrOre;
	}

	private Date getStartProgram(Date data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String stringStart = sdf.format(data) + " 08:00";
		SimpleDateFormat sdfFinal = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

		Date dataStart = sdfFinal.parse(stringStart);

		return dataStart;
	}

	private Date getStopProgram(String filiala, Date data) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", new Locale("ro"));

		String oraSfarsit = "";

		Calendar cal = Calendar.getInstance(new Locale("ro"));
		cal.setTime(data);

		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			oraSfarsit = " 13:00";
		else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			oraSfarsit = " 08:00";
		else {
			switch (filiala) {
			case "GL":
			case "NT":
			case "CJ":
			case "TM":
			case "DJ":
				oraSfarsit = " 22:30";
				break;
			case "PH":
			case "MM":
			case "BV":
				oraSfarsit = " 20:30";
				break;
			default:
				oraSfarsit = " 18:00";

			}
		}

		String stringStop = sdf.format(data) + oraSfarsit;
		SimpleDateFormat sdfFinal = new SimpleDateFormat("dd-MMM-yyyy HH:mm", new Locale("ro"));
		Date dataStop = sdfFinal.parse(stringStop);

		return dataStop;
	}

}
