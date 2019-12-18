package tiparire.filters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import tiparire.model.Articol;

public class TipIncarcareFilter {

	private NumberFormat formatter = new DecimalFormat("#0.##");

	public List<Articol> getArticoleFractie(List<Articol> listArticole) {
		List<Articol> articoleFractie = new ArrayList<>();

		for (Articol articol : listArticole) {
			if (Double.parseDouble(articol.getCantFractie()) > 0) {

				articol.setCantitate(articol.getCantFractie());
				articol.setCantitateModificata(articol.getCantitate());
				articoleFractie.add(articol);
			}

		}

		return articoleFractie;
	}

	public List<Articol> getArticolePaleti(List<Articol> listArticole) {
		List<Articol> articolePaleti = new ArrayList<>();

		for (Articol articol : listArticole) {
			if (Double.parseDouble(articol.getNrPaleti()) > 0) {
				articol.setCantitate(formatter
						.format(Double.parseDouble(articol.getNrPaleti()) * Double.parseDouble(articol.getPalBuc())));
				articol.setCantitateModificata(articol.getCantitate());
				articolePaleti.add(articol);
			}
		}

		return articolePaleti;

	}

}
