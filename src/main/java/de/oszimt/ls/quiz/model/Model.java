package de.oszimt.ls.quiz.model;

import java.util.LinkedList;
import java.util.List;

public class Model {

	private List<Schueler> alleSchueler;
	private Spielstand spielstand;
	private Schueler gewaehlterSchueler;

	public Model() {
		alleSchueler = new LinkedList<Schueler>();
	}

	public Schueler getGewaehlterSchueler() {
		return gewaehlterSchueler;
	}

	public void setGewaehlterSchueler(Schueler gewaehlterSchueler) {
		this.gewaehlterSchueler = gewaehlterSchueler;
	}

	public List<Schueler> getAlleSchueler() {
		return alleSchueler;
	}

	public Spielstand getSpielstand() {
		return spielstand;
	}

	public void setSpielstand(Spielstand spielstand) {
		this.spielstand = spielstand;
	}

	public void heimGewonnen() {
		this.getSpielstand().lehrerGewinnt();
	}

	public void gastGewonnen() {
		this.getSpielstand().schuelerGewinnt();
	}

	public void jokerBenutzt() {
		gewaehlterSchueler.jokerEingesetzt();
	}

	public void frageBeantwortet() {
		gewaehlterSchueler.gefragt();
	}

	public void blamiert() {
		gewaehlterSchueler.blamiert();
	}

	public void nichtDa() {
		gewaehlterSchueler.nichtDa();
	}
}
