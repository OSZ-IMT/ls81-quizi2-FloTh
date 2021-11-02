package de.oszimt.ls.quiz.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.oszimt.ls.quiz.model.Model;
import de.oszimt.ls.quiz.model.Schueler;
import de.oszimt.ls.quiz.model.file.FileParser;

public class Controller {

	private Model model;
	private FileParser fileParser;
	private int frageZeiger;
	public final int FRAGEANZAHL = 10;

	public Controller(String xmlPfad, String csvPfad) {
		model = new Model();
		fileParser = new FileParser(xmlPfad, csvPfad);
		model = fileParser.laden();
	}

	public int getFrageZeiger() {
		return frageZeiger;
	}

	public boolean Spielende() {
		return frageZeiger >= FRAGEANZAHL;
	}

	public Schueler getGewaehlterSchueler() {
		return model.getGewaehlterSchueler();
	}

	public String getSpielstand() {
		return model.getSpielstand().toString();
	}

	public void getZufallsSchueler() {

		Random rand = new Random();
		List<Schueler> alleSchueler = model.getAlleSchueler();
		int maxFragen = 0;

		for (Schueler s : alleSchueler) {
			if (s.getFragen() > maxFragen && s.isAnwesend()) {
				maxFragen = s.getFragen();
			}
		}

		List<Schueler> glueckslos = new LinkedList<Schueler>();

		for (Schueler s : alleSchueler) {
			if (s.isAnwesend()) {
				for (int i = 0; i < (maxFragen + 1) - s.getFragen(); i++) {
					glueckslos.add(s);
				}
			}
		}

		if (glueckslos.size() == 0) {
			return;
		}

		int klassengroesse = glueckslos.size();
		model.setGewaehlterSchueler(glueckslos.get(rand.nextInt(klassengroesse)));
	}

	private void speichernInDateien() {
		fileParser.speichern(model);
	}

	public void heimGewonnen() {
		model.heimGewonnen();
		speichernInDateien();
	}

	public void gastGewonnen() {
		model.gastGewonnen();
		speichernInDateien();
	}

	public void jokerBenutzt() {
		model.jokerBenutzt();
		speichernInDateien();
	}

	public void frageBeantwortet() {
		model.frageBeantwortet();
		frageZeiger++;
		speichernInDateien();
	}

	public void blamiert() {
		model.blamiert();
		speichernInDateien();
	}

	public void nichtDa() {
		model.nichtDa();
		speichernInDateien();
	}

	public FileParser getFileParser() {
		return fileParser;
	}

}
