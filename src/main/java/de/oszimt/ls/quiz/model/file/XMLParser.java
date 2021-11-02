package de.oszimt.ls.quiz.model.file;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.oszimt.ls.quiz.StartQuiz;
import de.oszimt.ls.quiz.model.Model;
import de.oszimt.ls.quiz.model.Schueler;
import de.oszimt.ls.quiz.model.Spielstand;

public class XMLParser {

	private File datei;

	/**
	 * Erstelle XML Datei
	 * 
	 * @param pfad
	 */
	public XMLParser(String pfad) {
		this.datei = new File(pfad);

		// Datei existiert noch nicht
		if (datei.exists() && datei.length() != 0) {
			// Dateiinhalt laden
			laden();

		} else {
			// Datei exisitert nicht
			try {
				// Anlegen der Datei
				this.datei.createNewFile();
			} catch (Exception e) {
				StartQuiz.showException(e, "Dokument konnte nicht erzeugt werden.");
			}
		}
	}

	/**
	 * Lädt die XML Datei
	 * 
	 * @return Model der XML-Datei
	 */
	public Model laden() {
		Model model = new Model();
		try {
			// Auslesen vorbereiten
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// Datei auswaehlen
			Document doc = dBuilder.parse(this.datei);
			doc.getDocumentElement().normalize();

			// Auslesen

			// Spielstand laden
			createSpielstand(doc, model);

			// Ebene Schueler / Mitspieler
			Element mitspieler = (Element) doc.getElementsByTagName("Mitspieler").item(0);
			NodeList schuelerList = mitspieler.getElementsByTagName("Schueler");

			// Schueler auslesen
			for (int i = 0; i < schuelerList.getLength(); i++) {
				Element schueler = (Element) schuelerList.item(i);
				String name = schueler.getAttribute("name");
				String vorname = schueler.getAttribute("vorname");

				int joker = Integer.parseInt(schueler.getElementsByTagName("Joker").item(0).getTextContent());
				int blamiert = Integer.parseInt(schueler.getElementsByTagName("Blamiert").item(0).getTextContent());
				int fragen = Integer.parseInt(schueler.getElementsByTagName("Fragen").item(0).getTextContent());

				model.getAlleSchueler().add(new Schueler(name, vorname, joker, blamiert, fragen));
			}

		} catch (Exception e) {
			StartQuiz.showException(e, "Lesen der Datei fehlgeschlagen!");
		}
		return model;
	}

	/**
	 * Create the spielstand
	 * 
	 * @param model
	 * @param doc
	 */
	private void createSpielstand(Document doc, Model model) {
		// Ebene Spielstand
		Element spielstand = (Element) doc.getElementsByTagName("Spielstand").item(0);

		// Partei Lehrer einlesen
		Element parteiLehrer = (Element) spielstand.getElementsByTagName("Partei").item(0);
		String lehrerName = parteiLehrer.getAttribute("name");
		int lehrerPkt = Integer.parseInt(parteiLehrer.getTextContent());

		// Partei Schueler einlesen
		Element parteiSchueler = (Element) spielstand.getElementsByTagName("Partei").item(1);
		String schuelerName = parteiSchueler.getAttribute("name");
		int schuelerPkt = Integer.parseInt(parteiSchueler.getTextContent());

		// Spielstand bauen
		model.setSpielstand(new Spielstand(lehrerName, lehrerPkt, schuelerName, schuelerPkt));
	}

	/**
	 * Speichert alle Nutzereingaben in die XML-Datei
	 * 
	 * @param model, Model
	 */
	public void speichern(Model model) {
		try {
			// XML-Dokument vorbereiten
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// XML-Dokument mit Daten fuellen
			Element klasse = doc.createElement(datei.getName());
			doc.appendChild(klasse);

			// Spielstand speichern
			Element sstand = doc.createElement("Spielstand");
			klasse.appendChild(sstand);

			// Partei Lehrer speichern
			Element partei = doc.createElement("Partei");
			partei.setAttribute("name", model.getSpielstand().getLehrerName());
			sstand.appendChild(partei);

			String pktLehrer = String.valueOf(model.getSpielstand().getPunkteSchueler());
			partei.appendChild(doc.createTextNode(pktLehrer));

			// Partei Schueler speichern
			partei = doc.createElement("Partei");
			partei.setAttribute("name", model.getSpielstand().getSchuelerName());
			sstand.appendChild(partei);

			String pktSchueler = String.valueOf(model.getSpielstand().getPunkteSchueler());
			partei.appendChild(doc.createTextNode(pktSchueler));

			// Mitspieler speichern
			Element mitspieler = doc.createElement("Mitspieler");
			klasse.appendChild(mitspieler);

			// Schueler eintragen
			for (Schueler schueler : model.getAlleSchueler()) {
				Element schuelerXml = doc.createElement("Schueler");

				schuelerXml.setAttribute("name", schueler.getName());
				schuelerXml.setAttribute("vorname", schueler.getVorname());
				mitspieler.appendChild(schuelerXml);

				Element joker = doc.createElement("Joker");
				String pktJoker = String.valueOf(schueler.getJoker());
				joker.appendChild(doc.createTextNode(pktJoker));
				schuelerXml.appendChild(joker);

				Element blamiert = doc.createElement("Blamiert");
				String pktBlamiert = String.valueOf(schueler.getBlamiert());
				blamiert.appendChild(doc.createTextNode(pktBlamiert));
				schuelerXml.appendChild(blamiert);

				Element fragen = doc.createElement("Fragen");
				String pktFragen = String.valueOf(schueler.getFragen());
				fragen.appendChild(doc.createTextNode(pktFragen));
				schuelerXml.appendChild(fragen);
			}

			// XML-File schreiben vorbereiten
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			// Ziel setzen
			StreamResult result = new StreamResult(this.datei);
			// Datei schreiben
			transformer.transform(source, result);

		} catch (Exception e) {
			StartQuiz.showException(e, "Datei konnte nicht gespeichert werden!");
		}
	}

}
