package appli;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class MyController{

	private File selectedFile;
	private String date_debut,date_fin;
	private Tris tris = new TrisName();
	private File fileCss = new File("./src/data/visu.css");
	private File saveDirectory = new File("./src/data");
	private SansDisplay sansDisplay = new SansDisplay();


	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button buttonGener;

	@FXML
	private TextField libelle;

	@FXML
	private RadioButton radioID;

	@FXML
	private Rectangle Text;

	@FXML
	private CheckBox boxNom;

	@FXML
	private ToggleGroup radio;

	@FXML
	private RadioButton radioNom;

	@FXML
	private RadioButton radioDuree;

	@FXML
	private Text txtHF;

	@FXML
	private Text txtHD;

	@FXML
	private Text txtdate;

	@FXML
	private CheckBox boxPlanning;

	@FXML
	private Text text;

	@FXML
	private TextField HD;

	@FXML
	private TextField HF;

	@FXML
	private CheckBox boxId;
	@FXML
	private Rectangle fileview;

	private Sortie sortie = new SortieHTML();

	@FXML
	void handleDragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);}
	}


	@FXML
	void handleDrop(DragEvent event) throws FileNotFoundException {

		Dragboard db = event.getDragboard();
		String chemin = "";

		if (db.hasFiles()) {
			chemin = db.getFiles().get(0).toString();
		}

		selectedFile = new File(chemin);


		String t= new String(selectedFile.getName());
		text.setText(t);



		Collection<LocalDateTime> heureDebut= new ArrayList<>();
		ArrayList<LocalDateTime> heureFin= new ArrayList<>();
		var teamsFile = new TEAMSAttendanceList(selectedFile);
		var lines = teamsFile.get_attlist();
		if (lines != null) {
			// convert lines in data structure with people & periods
			var filter = new TEAMSAttendanceListAnalyzer(lines);
			// cut periods before start time and after end time
			// sort
			List<People> peopleList = new ArrayList<>(filter.get_peopleList().values());
			Iterator people = peopleList.iterator();
			while(people.hasNext()){
				People p= (People) people.next();
				Iterator<TEAMSPeriod> periods = p.get_periodList().iterator();
				// System.out.println(periods.next());
				while (periods.hasNext()) {
					var period = periods.next();
					LocalDateTime begin = period.get_start();
					LocalDateTime end = period.get_end();
					heureDebut.add(begin);
					if (end!=null) heureFin.add(end);

				}
				txtdate.setText(p.getDate());
			}
		}

		String heureminimum=heureDebut.stream().min(LocalDateTime::compareTo).toString();
		String heuremaximum=heureFin.stream().max(LocalDateTime::compareTo).toString();
		LocalDateTime heure_min = LocalDateTime.parse(heureminimum.substring(9,heureminimum.length()-1));
		LocalDateTime heure_max = LocalDateTime.parse(heuremaximum.substring(9,heuremaximum.length()-1));
		txtHF.setText(heure_max.toLocalTime().toString());
		txtHD.setText(heure_min.toLocalTime().toString());




		//19/01/2021 ? 10:15:00
		//19/01/2021 ? 11:45:00

	}


	@FXML
	void pressedGenerer(ActionEvent event) throws IOException, InvocationTargetException {


		if(selectedFile != null && date_debut != "" && date_fin != "") {

			//Recuperer date debut et fin du textfield
			date_debut = HD.getText();
			date_fin = HF.getText();


			//CHOIX DU TRI VIA RADIO
			if(radioID.isSelected())
			{
				this.tris = new TrisId();
			}

			if(radioNom.isSelected())
			{
				this.tris = new TrisName();
			}

			if(radioDuree.isSelected())
			{
				this.tris = new TrisDuree();
			}


			//SELECTION DES CHECKBOX


			sansDisplay.noNameDisp(fileCss);
			sansDisplay.noDureeDisp(fileCss);
			sansDisplay.noIdDisp(fileCss);

			Hide sansNom = hideFactory.getInstance("sansNom");
			Hide sansDuree = hideFactory.getInstance("sansPlanning");
			Hide sansId = hideFactory.getInstance("sansId");

			if(boxNom.isSelected()) {
				sansNom.hideCSS(fileCss);
				if(boxPlanning.isSelected()) {
					sansDuree.hideCSS(fileCss);
					if(boxId.isSelected()) {
						sansId.hideCSS(fileCss);
					}
					else {
						sansDisplay.noIdDisp(fileCss);
					}
				}
				else if(boxId.isSelected()) {
					sansId.hideCSS(fileCss);
					if(boxPlanning.isSelected()) {
						sansDuree.hideCSS(fileCss);
					}
					else {
						sansDisplay.noDureeDisp(fileCss);
					}
				}
				else {
					sansDisplay.noDureeDisp(fileCss);
					sansDisplay.noIdDisp(fileCss);
				}
			}

			else if(boxPlanning.isSelected()) {
				sansDuree.hideCSS(fileCss);
				if(boxNom.isSelected()) {
					sansNom.hideCSS(fileCss);
					if(boxId.isSelected()) {
						sansId.hideCSS(fileCss);
					}
					else {
						sansDisplay.noIdDisp(fileCss);
					}
				}
				else if(boxId.isSelected()) {
					sansId.hideCSS(fileCss);
					if(boxNom.isSelected()) {
						sansNom.hideCSS(fileCss);
					}
					else {
						sansDisplay.noNameDisp(fileCss);
					}
				}
				else {
					sansDisplay.noNameDisp(fileCss);
					sansDisplay.noIdDisp(fileCss);
				}
			}

			else if(boxId.isSelected()) {
				sansId.hideCSS(fileCss);
				if(boxNom.isSelected()) {
					sansNom.hideCSS(fileCss);
					if(boxPlanning.isSelected()) {
						sansDuree.hideCSS(fileCss);
					}
					else {
						sansDisplay.noDureeDisp(fileCss);
					}
				}
				else if(boxPlanning.isSelected()) {
					sansDuree.hideCSS(fileCss);
					if(boxNom.isSelected()) {
						sansNom.hideCSS(fileCss);
					}
					else {
						sansDisplay.noNameDisp(fileCss);
					}
				}
				else {
					sansDisplay.noNameDisp(fileCss);
					sansDisplay.noDureeDisp(fileCss);
				}
			}
			else {
				sansDisplay.noNameDisp(fileCss);
				sansDisplay.noDureeDisp(fileCss);
				sansDisplay.noIdDisp(fileCss);
			}


			// process the file, and limit periods to given time interval
			var teamsProcessor = new TEAMSProcessor(selectedFile,date_debut, date_fin,this.tris,libelle.getText());
			teamsProcessor.sort();

			var allpeople = teamsProcessor.get_allpeople();
			
			/*for (People people : allpeople) {
	            System.out.println( people );
	        }*/

			String html =sortie.sortie(allpeople, selectedFile.getName(), date_debut, date_fin,libelle.getText());


			//CHOOSE THE LOCATION TO SAVE FILE
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Enregistrer votre fichier");
			fileChooser.setInitialDirectory(saveDirectory);
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Tout type de fichier", "*.*"));

			File file = fileChooser.showSaveDialog(null);

			//File file = new File("./src/data/file.html");

			try {

				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(html);
				bw.close();
				Desktop desktop = Desktop.getDesktop();
				if(file.exists()) desktop.open(file);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert al = new Alert(AlertType.WARNING);
			al.setTitle("Erreur formulaire");
			al.setContentText("Veuillez remplir les champs obligatoires : Fichier ? deposer / libell?  / Heure d?but & fin !");
			al.setHeaderText(null);
			al.showAndWait();
			System.err.println("No File Founded");
		}



	}





}
