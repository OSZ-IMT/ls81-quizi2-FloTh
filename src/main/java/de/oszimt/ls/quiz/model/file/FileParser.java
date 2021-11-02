package de.oszimt.ls.quiz.model.file;

import java.io.File;

import de.oszimt.ls.quiz.model.Model;

	private String jsonPfad;
	private String csvPfad;
	private String xmlPfad;
	private XMLParser xmlParser;
	private JSONParser jsonParser;

		/
		* Create FileParser
		*
		* @param xmlPfad
		* @param csvPfad
		*/
	public FileParser(String xmlPfad, String csvPfad, String jsonPfad) {
		this.csvPfad = csvPfad;
		this.xmlPfad = xmlPfad;
		this.jsonPfad = jsonPfad;
		xmlParser = new XMLParser(xmlPfad);
		jsonParser = new jsonParser(jsonPfad);
		}

		/
		* Prüft ob die XML geladen werden kann, alternativ die CSV-Datei
		*
		* @return Model
		*/
	public Model laden() {
		File datei = new File(xmlPfad);
		File datei = new File(jsonPfad);

		// Datei existiert
		if (datei.exists() && datei.length() != 0) {
		// XML laden
		return xmlParser.laden();
		}
		if (datei.exists() && datei.length() != 0) {
		// XML laden
		return jsonParser.laden();
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
		jsonParser.speichern(model);
	}

	public String getXmlPfad() {
		return xmlPfad;
	}
	public String getJsonPfad() {
		return jsonPfad;
	}

}
