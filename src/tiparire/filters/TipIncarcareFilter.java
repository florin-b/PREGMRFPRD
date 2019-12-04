package tiparire.filters;

import java.util.ArrayList;
import java.util.List;

import tiparire.model.Articol;

public class TipIncarcareFilter {

	public List<Articol> getArticoleFractie(List<Articol> listArticole) {
		List<Articol> articoleFractie = new ArrayList<>();

		for (Articol articol : listArticole) {
			if (Double.parseDouble(articol.getNrPaleti()) == 0)
				articoleFractie.add(articol);
		}

		return articoleFractie;
	}

	public List<Articol> getArticolePaleti(List<Articol> listArticole) {
		List<Articol> articolePaleti = new ArrayList<>();

		for (Articol articol : listArticole) {
			if (Double.parseDouble(articol.getNrPaleti()) != 0) {
				articol.setCantitate(articol.getNrPaleti() + "*" + articol.getPalBuc());
				articol.setCantitateModificata(articol.getCantitate());
				articolePaleti.add(articol);
			}
		}

		return articolePaleti;

	}

}
