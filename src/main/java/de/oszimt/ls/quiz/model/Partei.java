package de.oszimt.ls.quiz.model;

public class Partei {

	// Anfang Attribute
	private String name;
	private int punkte;
	// Ende Attribute

	/**
	 * Create Partei
	 * 
	 * @param name
	 * @param pkt
	 */
	public Partei(String name, int pkt) {
		this.name = name;
		this.punkte = pkt;
	}

	// Anfang Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPunkte() {
		return punkte;
	}

	public void gewonnen() {
		this.punkte++;
	}

	// Ende Methoden
}
