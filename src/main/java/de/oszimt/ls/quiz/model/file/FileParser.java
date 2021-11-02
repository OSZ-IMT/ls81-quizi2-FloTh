package de.oszimt.ls.quiz.model.file;

import java.io.File;

import de.oszimt.ls.quiz.model.Model;

public class FileParser {

	private String csvPfad;
	private String xmlPfad;
	private XMLParser xmlParser;

	/**
	 * Create FileParser
	 * 
	 * @param xmlPfad
	 * @param csvPfad
	 */
	public FileParser(String xmlPfad, String csvPfad) {
		this.csvPfad = csvPfad;
		this.xmlPfad = xmlPfad;
		xmlParser = new XMLParser(xmlPfad);
	}

	/**
	 * Prüft ob die XML geladen werden kann, alternativ die CSV-Datei
	 * 
	 * @return Model
	 */
	public Model laden() {
		File datei = new File(xmlPfad);

		// Datei existiert
		if (datei.exists() && datei.length() != 0) {
			// XML laden
			return xmlParser.laden();
		}

		// Alternativ CSV laden
		CSVParser csvParser = new CSVParser(csvPfad);
		return csvParser.laden();
	}

	/**
	 * speichert alle Nutzereingaben in eine XML-Datei
	 * 
	 * @param model, Model
	 */
	public void speichern(Model model) {
		xmlParser.speichern(model);
	}

	public String getXmlPfad() {
		return xmlPfad;
	}

}
