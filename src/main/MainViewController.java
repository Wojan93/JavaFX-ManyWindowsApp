package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import load.AlertWindow;

public class MainViewController {

	public MainViewController() {
	}

	@FXML
	private RadioButton wczytaj;

	@FXML
	private RadioButton utworz;

	@FXML
	private RadioButton wyswietl;

	@FXML
	private Button dalej;

	@FXML
	private Button zamknij;

	@FXML
	void dalejAction(ActionEvent event) {
		try {
			Parent tableViewChild = FXMLLoader.load(getClass().getResource("..\\load\\LoadView.fxml"));
			
			Scene tableViewScene = new Scene(tableViewChild,600,600);
			tableViewScene.getStylesheets().add(getClass().getResource("..\\load\\loadView.css").toExternalForm());
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void zamknijAction(ActionEvent event) {
		AlertWindow.showAndWait(AlertType.CONFIRMATION, "QUIT?", "Czy na pewno chcesz opuœciæ program?");
		Stage stage = (Stage) zamknij.getScene().getWindow();
		stage.close();
	}

}
