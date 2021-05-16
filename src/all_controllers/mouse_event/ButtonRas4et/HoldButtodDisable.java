package all_controllers.mouse_event.ButtonRas4et;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class HoldButtodDisable {


    public void holdbutton(Node node){
        Platform.runLater(()->{
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.setVisible(true);
        });
    }

    public  Alert showAllertNonOroginal(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("AVR SAY ->");
        alert.setHeaderText(message);
        alert.getDialogPane().setPrefSize(350,180);
        alert.getDialogPane().setDisable(true);
        alert.show();
        return alert;
    }
}
