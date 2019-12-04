package tiparire.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import tiparire.enums.EnumTipTransport;

public class HandleJSONReceivedData {

	private String JsonString;
	private JSONArray jsonObject;

	public HandleJSONReceivedData(String JsonString) {
		this.JsonString = JsonString;
	}

	public void decodeLogonInfo() {

		UserInfo userInfo = UserInfo.getInstance();
		try {

			JSONObject jsonObject = new JSONObject(JsonString);

			userInfo.setLogonStatus(jsonObject.get("logonStatus").toString());
			userInfo.setDepart(jsonObject.get("departament").toString());
			userInfo.setFiliala(jsonObject.get("filiala").toString());
			userInfo.setTipAcces(jsonObject.get("tipAcces").toString());
			userInfo.setId(jsonObject.get("codUser").toString());
			userInfo.setNume(jsonObject.get("numeUser").toString());
			userInfo.setUnitLog(getFiliala(userInfo.getFiliala()));
			userInfo.setInitDepart(jsonObject.get("departament").toString());
			userInfo.setDepozit(jsonObject.get("depozit").toString());
			userInfo.setExtraDep(jsonObject.get("extraDep").toString());

		} catch (JSONException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<Articol> decodeDocumentData() {
		Articol unArticol = null;
		ArrayList<Articol> objectsList = new ArrayList<Articol>();

		try {

			Object json = new JSONTokener(JsonString).nextValue();

			if (json instanceof JSONArray) {
				jsonObject = new JSONArray(JsonString);

				for (int i = 0; i < jsonObject.length(); i++) {
					JSONObject articolObject = jsonObject.getJSONObject(i);

					unArticol = new Articol();
					unArticol.setDocumentId(articolObject.getString("id"));
					unArticol.setClient(articolObject.getString("client"));
					unArticol.setEmitere(articolObject.getString("emitere"));
					unArticol.setCod(articolObject.getString("codArticol"));
					unArticol.setNume(articolObject.getString("numeArticol"));
					unArticol.setCantitate(articolObject.getString("cantitate"));
					unArticol.setUm(articolObject.getString("um"));
					unArticol.setPozitie(
							String.valueOf(Integer.valueOf(articolObject.getString("pozitieArticol")) / 10));

					unArticol.setPregatit(articolObject.getString("isPregatit").equals("1") ? true : false);
					unArticol.setTiparit(articolObject.getString("isTiparit").equals("1") ? true : false);
					unArticol.setTip(articolObject.getString("tip"));
					unArticol.setDepozit(articolObject.getString("depozit"));
					unArticol.setNumeSofer(articolObject.getString("numeSofer"));
					unArticol.setNrMasina(articolObject.getString("nrMasina"));
					unArticol.setModificare(articolObject.getString("modificare"));
					unArticol.setCantitateModificata(articolObject.getString("cantitateModificata") == null ? ""
							: articolObject.getString("cantitateModificata"));
					unArticol.setInfoStatus(
							articolObject.getString("infoStatus") == null ? "" : articolObject.getString("infoStatus"));
					unArticol.setTipTransport(EnumTipTransport.valueOf(articolObject.getString("tipTransport")));

				
					unArticol.setCantFractie(articolObject.getString("cantFractie"));
					unArticol.setNrPaleti(articolObject.getString("nrPaleti"));
					unArticol.setUmPaleti(articolObject.getString("umPaleti"));
					
					unArticol.setPalBuc(articolObject.getString("palBuc"));

					objectsList.add(unArticol);

				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return objectsList;

	}

	public String getFiliala(String numeFiliala) {
		String fl = "NN10";

		if (numeFiliala.equals("BACAU"))
			fl = "BC10";

		if (numeFiliala.equals("GALATI"))
			fl = "GL10";

		if (numeFiliala.equals("PITESTI") || numeFiliala.equals("AGTOTAL"))
			fl = "AG10";

		if (numeFiliala.equals("TIMISOARA"))
			fl = "TM10";

		if (numeFiliala.equals("ORADEA") || numeFiliala.equals("BIHORTOTAL"))
			fl = "BH10";

		if (numeFiliala.equals("FOCSANI"))
			fl = "VN10";

		if (numeFiliala.equals("GLINA"))
			fl = "BU10";

		if (numeFiliala.equals("ANDRONACHE"))
			fl = "BU13";

		if (numeFiliala.equals("OTOPENI"))
			fl = "BU12";

		if (numeFiliala.equals("CLUJ") || numeFiliala.equals("CLUJTOTAL"))
			fl = "CJ10";

		if (numeFiliala.equals("BAIA"))
			fl = "MM10";

		if (numeFiliala.equals("MILITARI"))
			fl = "BU11";

		if (numeFiliala.equals("CONSTANTA") || numeFiliala.equals("CTTOTAL"))
			fl = "CT10";

		if (numeFiliala.equals("BV FER CEN"))
			fl = "BV90";

		if (numeFiliala.equals("BRASOV") || numeFiliala.equals("BVTOTAL"))
			fl = "BV10";

		if (numeFiliala.equals("PLOIESTI"))
			fl = "PH10";

		if (numeFiliala.equals("PIATRA"))
			fl = "NT10";

		if (numeFiliala.equals("MURES"))
			fl = "MS10";

		if (numeFiliala.equals("IASI") || numeFiliala.equals("IASITOTAL"))
			fl = "IS10";

		if (numeFiliala.equals("CRAIOVA"))
			fl = "DJ10";

		if (numeFiliala.equals("BUZAU"))
			fl = "BZ10";

		if (numeFiliala.equals("SIBIU"))
			fl = "SB10";

		if (numeFiliala.equals("DEVA"))
			fl = "HD10";

		return fl;

	}

}
