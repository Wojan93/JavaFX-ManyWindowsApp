package load;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertWindow {


	
	public static Optional<ButtonType> showAndWait(AlertType type, String title,String message) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(message);
		switch(type) {
		case CONFIRMATION:
			alert.setHeaderText("QUIT");
			
			break;
		case WARNING:
			alert.setHeaderText("DATA WARNING");
		case INFORMATION: {
			alert.setHeaderText("UWAGA!");
			break;
		}
		default:
			break;
		}
		return alert.showAndWait();
	}
}