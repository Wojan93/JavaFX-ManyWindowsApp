package load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadViewController {
	String line;
	ObservableList<Dane> obList = FXCollections.observableArrayList();
	File file = null;
	@FXML
	private MenuItem zapisz;

	@FXML
	private MenuItem wyswietl;

	@FXML
	private TableView<Dane> tabela;

	@FXML
	private TableColumn<Dane, SimpleStringProperty> haslo;

	@FXML
	private TableColumn<Dane, SimpleStringProperty> definicja;

	@FXML
	private TextField hasloInput;

	@FXML
	private TextField definicjaInput;
	@FXML
	private Button add;

	@FXML
	private Button del;

	@FXML
	private Button powrot;

	@FXML
	void zapiszAction(ActionEvent event) {
		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz plik");
		file = fileChooser.showSaveDialog(stage);
		Model.saveFile(obList, file);
		if (file == null) {
			Model.saveFile(tabela.getItems(), file);
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void wyswietlTabela(ActionEvent event) throws FileNotFoundException {
		obList = Model.readFile(Model.getFile());

		haslo.setCellValueFactory(new PropertyValueFactory<Dane, SimpleStringProperty>("haslo"));
		definicja.setCellValueFactory(new PropertyValueFactory<Dane, SimpleStringProperty>("definicja"));
		tabela.getColumns().setAll(haslo, definicja);
		tabela.setItems(obList);
		tabela.setEditable(true);
	}

	@FXML
	void addButtonClicked(ActionEvent event) {
	 if (hasloInput.getText().isEmpty() || definicjaInput.getText().isEmpty()) {
			AlertWindow.showAndWait(AlertType.INFORMATION, "Alert Empty Value", "Dodajesz pusta wartosc");
		}
		else {
		Dane d = new Dane();
		d.setHaslo(hasloInput.getText());
		d.setDefinicja(definicjaInput.getText());
		 
			haslo.setCellValueFactory(new PropertyValueFactory<Dane, SimpleStringProperty>("haslo"));
			definicja.setCellValueFactory(new PropertyValueFactory<Dane, SimpleStringProperty>("definicja"));
			if (obList.isEmpty()) {
				tabela.setItems(obList);
			}
			tabela.getItems().add(d);
			tabela.setEditable(true);
		}
		hasloInput.clear();
		definicjaInput.clear();
	}

	@FXML
	void delButtonClicked(ActionEvent event) {
		ObservableList<Dane> daneZaznaczone, wszystkieDane;
		wszystkieDane = tabela.getItems();
		daneZaznaczone = tabela.getSelectionModel().getSelectedItems();
		AlertWindow.showAndWait(AlertType.WARNING, "Czy na pewno chcesz usun¹æ ten element?", "Tej operacji nie mo¿na cofn¹æ. Utracisz wybrane dane.");
		daneZaznaczone.forEach(wszystkieDane::remove);
	}

	@FXML
	void powrotButtonClicked(ActionEvent event) {
		try {
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("..\\main\\MainView.fxml"));
			Scene tableViewScene = new Scene(tableViewParent, 600, 600);
			tableViewScene.getStylesheets().add(getClass().getResource("..\\main\\mainView.css").toExternalForm());
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}