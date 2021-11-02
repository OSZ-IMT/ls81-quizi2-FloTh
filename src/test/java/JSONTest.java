import de.oszimt.ls.quiz.controller.Controller;
import de.oszimt.ls.quiz.model.Model;
import de.oszimt.ls.quiz.model.Schueler;
import de.oszimt.ls.quiz.model.Spielstand;
import de.oszimt.ls.quiz.model.file.FileParser;
import de.oszimt.ls.quiz.model.file.JSONParser;
import de.oszimt.ls.quiz.model.file.XMLParser;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JSONTest {

    @Test
    public void parserVorhanden(){
        //can create objects?
        JSONParser j = new JSONParser("json");

        assertNotNull(j);
    }

    @Test
    public void parserVorhandenSpeichern(){
        //can create objects?
        JSONParser j = new JSONParser("json");

        //speichern Methode existiert?
        for(Method method : j.getClass().getDeclaredMethods()){
            if (method.getName().equals("speichern")){
                return;
            }
        }

        fail("Speichern Methode im JSONParser nicht gefunden");
    }

    @Test
    public void parserEingebundenController(){
        //can create objects?
        Controller c = new Controller("xml","csv","json");

        assertNotNull(c);
    }

    @Test
    public void parserEingebundenFileParser(){
        //can create objects?
        FileParser f = new FileParser("xml","csv","json");

        assertNotNull(f);
    }

    @Test
    public void jsonWrite(){
        //create empty Model
        Model model = new Model();
        model.setSpielstand(new Spielstand("Lehrer",0,"Schueler",0));
        model.getAlleSchueler().add(new Schueler("nname","vname",1,2,3));

        //save model
        JSONParser jsonParser = new JSONParser("src/test/java/jsonTestW.json");
        jsonParser.speichern(model);


        //laden zum checken
        try {
            // Datei öffnen
            JsonReader reader = Json.createReader(new FileInputStream("src/test/java/jsonTestW.json"));

            // Objekt einlesen
            JsonObject json = reader.readObject();

            // Schueler auslesen
            JsonArray schuelerArrayJson = json.getJsonArray("Schueler");
            ArrayList<String> autorenArray = new ArrayList<>();

            //Gibt es genau einen Schueler?
            assertEquals(1,schuelerArrayJson.size(),"Testschueler wird nicht richtig eingelesen");

            JsonObject jsonSchueler = schuelerArrayJson.getJsonObject(0);

            //passen die attribute?
            assertEquals(1,jsonSchueler.getJsonNumber("joker").intValue(),"Testschueler Jokeranzahl wird nicht richtig eingelesen");
            assertEquals(2,jsonSchueler.getJsonNumber("blamiert").intValue(),"Testschueler Blamiertanzahl wird nicht richtig eingelesen");
            assertEquals(3,jsonSchueler.getJsonNumber("fragen").intValue(),"Testschueler Fragenanzahl wird nicht richtig eingelesen");

        } catch (FileNotFoundException e) {
            fail(e.getClass()+":"+e.getMessage());
        }
    }
}
