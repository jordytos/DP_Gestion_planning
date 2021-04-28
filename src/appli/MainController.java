package appli;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MainController {
    public Label HelloWorld;
    private Tris tris = new TrisDuree();
    

    public void sayHelloWorld(ActionEvent actionEvent) {


        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        // process the file, and limit periods to given time interval
        var teamsProcessor = new TEAMSProcessor(selectedFile,"19/01/2021 à 10:15:00", "19/01/2021 à 11:45:00",tris,"CM Bases de données et programmation Web");

        var allpeople = teamsProcessor.get_allpeople();
        for (People people : allpeople) {
            System.out.println( people );
        }

        //System.out.println( teamsProcessor.toHTMLCode() );
        
        String html = teamsProcessor.toHTMLCode();
        File file = new File("./src/data/file.html");
        
        try {
			
        	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        	bw.write(html);
        	bw.close();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        

    }
}
