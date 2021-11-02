package de.oszimt.ls.quiz.model;

public class Spielstand {

	// Attribute
	private Partei lehrer;
	private Partei schueler;

	/**
	 * Create Spielstand
	 * 
	 * @param lehrerName
	 * @param lehrerPkt
	 * @param schuelerName
	 * @param schuelerPkt
	 */
	public Spielstand(String lehrerName, int lehrerPkt, String schuelerName, int schuelerPkt) {
		this.lehrer = new Partei(lehrerName, lehrerPkt);
		this.schueler = new Partei(schuelerName, schuelerPkt);
	}

	// Methoden
	public String toString() {
		return lehrer.getName() + " " + lehrer.getPunkte() + " : " + schueler.getPunkte() + " " + schueler.getName();
	}

	public int getPunkteLehrer() {
		return lehrer.getPunkte();
	}

	public int getPunkteSchueler() {
		return schueler.getPunkte();
	}

	public String getLehrerName() {
		return lehrer.getName();
	}

	public String getSchuelerName() {
		return schueler.getName();
	}

	public void lehrerGewinnt() {
		this.lehrer.gewonnen();
	}

	public void schuelerGewinnt() {
		this.schueler.gewonnen();
	}

}
