package load;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Model {

	public static ObservableList<Dane> readFile(File file) throws FileNotFoundException {
		ObservableList<Dane> obsList = FXCollections.observableArrayList();
		try {
			Scanner in = new Scanner(file);
			String line;

			while (in.hasNext()) {
				line = in.nextLine();
				String[] keyvalue = line.split(":");
				keyvalue[1] = keyvalue[1].trim();
				obsList.add(new Dane(keyvalue[0], keyvalue[1]));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obsList;
	}

	public static File getFile() {
		File selectedFile;
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile == null) {
			return null;
		} else
			return selectedFile;

	}

	public static void saveFile(ObservableList<Dane> obserList, File file) {

		
		
		if (file != null) {
			try {
				BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
				for (Dane dane : obserList) {
					outWriter.write(dane.toString());
					outWriter.newLine();
				}
				outWriter.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
