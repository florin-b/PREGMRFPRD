package tiparire.filters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import tiparire.enums.EnumTipTransport;
import tiparire.model.Articol;

public class TipIncarcareFilter {

	private NumberFormat formatter = new DecimalFormat("#0.##");

	public List<Articol> getArticoleFractie(List<Articol> listArticole) {
		List<Articol> articoleFractie = new ArrayList<>();

		for (Articol articol : listArticole) {

			if (Double.parseDouble(articol.getCantFractie()) > 0) {

				Articol articolNou = new Articol();

				articolNou.setDocumentId(articol.getDocumentId());
				articolNou.setClient(articol.getClient());
				articolNou.setEmitere(articol.getEmitere());
				articolNou.setCod(articol.getCod());
				articolNou.setNume(articol.getNume());
				articolNou.setUm(articol.getUm());
				articolNou.setPozitie(articol.getPozitie());
				articolNou.setPregatit(articol.isPregatit());
				articolNou.setTiparit(articol.isTiparit());
				articolNou.setTip(articol.getTip());
				articolNou.setDepozit(articol.getDepozit());
				articolNou.setNumeSofer(articol.getNumeSofer());
				articolNou.setNrMasina(articol.getNrMasina());
				articolNou.setModificare(articol.getModificare());
				articolNou.setInfoStatus(articol.getInfoStatus());
				articolNou.setTipTransport(articol.getTipTransport());
				articolNou.setCantFractie(articol.getCantFractie());
				articolNou.setNrPaleti(articol.getNrPaleti());
				articolNou.setUmPaleti(articol.getUmPaleti());
				articolNou.setPalBuc(articol.getPalBuc());

				articolNou.setCantitate(articol.getCantFractie());
				articolNou.setCantitateModificata(articol.getCantFractie());

				articoleFractie.add(articolNou);
			}

		}

		return articoleFractie;
	}

	public List<Articol> getArticolePaleti(List<Articol> listArticole) {
		List<Articol> articolePaleti = new ArrayList<>();

		for (Articol articol : listArticole) {
			if (Double.parseDouble(articol.getNrPaleti()) > 0) {

				Articol articolNou = new Articol();

				articolNou.setDocumentId(articol.getDocumentId());
				articolNou.setClient(articol.getClient());
				articolNou.setEmitere(articol.getEmitere());
				articolNou.setCod(articol.getCod());
				articolNou.setNume(articol.getNume());
				articolNou.setUm(articol.getUm());
				articolNou.setPozitie(articol.getPozitie());
				articolNou.setPregatit(articol.isPregatit());
				articolNou.setTiparit(articol.isTiparit());
				articolNou.setTip(articol.getTip());
				articolNou.setDepozit(articol.getDepozit());
				articolNou.setNumeSofer(articol.getNumeSofer());
				articolNou.setNrMasina(articol.getNrMasina());
				articolNou.setModificare(articol.getModificare());
				articolNou.setInfoStatus(articol.getInfoStatus());
				articolNou.setTipTransport(articol.getTipTransport());
				articolNou.setCantFractie(articol.getCantFractie());
				articolNou.setNrPaleti(articol.getNrPaleti());
				articolNou.setUmPaleti(articol.getUmPaleti());
				articolNou.setPalBuc(articol.getPalBuc());

				articolNou.setCantitate(formatter
						.format(Double.parseDouble(articol.getNrPaleti()) * Double.parseDouble(articol.getPalBuc())));

				articolNou.setCantitateModificata(formatter
						.format(Double.parseDouble(articol.getNrPaleti()) * Double.parseDouble(articol.getPalBuc())));
				articolePaleti.add(articolNou);
			}
		}

		return articolePaleti;

	}

}
