package sample;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CloseOperation {

    public CloseOperation(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        setStandartOperationToAlertClose(alert);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            alert.close();
        } else if (option.get() == ButtonType.OK) {
            closeApp();
        } else if (option.get() == ButtonType.CANCEL) {
            alert.close();
        } else {
            alert.close();
        }

    }

    private static void closeApp(){
        Platform.exit();
        System.exit(0);
    }

    private static void setStandartOperationToAlertClose(Alert alert ){
        alert.getDialogPane().setStyle("-fx-background-color:black");
        alert.setTitle("Exit program");
        alert.setHeaderText("Are you sure want to leave?");
        alert.setContentText("All resources will be closed");
    }
}
