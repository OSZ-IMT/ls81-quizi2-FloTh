package de.oszimt.ls.quiz.model.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import de.oszimt.ls.quiz.model.Model;
import de.oszimt.ls.quiz.model.Schueler;
import de.oszimt.ls.quiz.model.Spielstand;

public class CSVParser {
	private File datei;

	/**
	 * Erstelle XML Datei
	 *
	 * @param pfad, Pfad zur Datei
	 */
	public CSVParser(String pfad) {
		this.datei = new File(pfad);
	}

	/**
	 * Lädt die XML Datei
	 * 
	 * @return Model der XML-Datei
	 */
	public Model laden() {
		Model model = new Model();
		// spielstand setzen
		model.setSpielstand(new Spielstand("Lehrer", 0, "Schüler", 0));

		// Datei laden
		try {

			// Dateizugriff öffnen
			FileReader fr = new FileReader(datei);
			// in src Ordner? new FileReader("src/artikelliste.csv");

			// Als Textdatei öffnen
			BufferedReader br = new BufferedReader(fr);

			br.readLine(); // überspringen der Kopfzeile

			String line;

			// Zeile in line speichern und dann prüfen, ob Sie nicht null ist/existiert
			while ((line = br.readLine()) != null) {
				// trennen der Zeile
				String[] getrennt = line.split(";");

				// Variablen ermitteln
				String nachname = getrennt[1];
				String vorname = getrennt[0];
				int joker = Integer.parseInt(getrennt[2]);
				int blamiert = Integer.parseInt(getrennt[3]);
				int fragen = Integer.parseInt(getrennt[4]);

				// Schüler erstellen
				Schueler s = new Schueler(nachname, vorname, joker, blamiert, fragen);
				model.getAlleSchueler().add(s);

				// Ausgeben
			}

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return model;
	}
}
