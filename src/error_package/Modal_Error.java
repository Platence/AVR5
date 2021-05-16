package error_package;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import warehouse_plu.Ostatku;

import java.io.IOException;
import java.util.LinkedList;

public class Modal_Error extends Task<Void> {

    public static String er_1 = "";

    public static Controller_Error2 controller_error2;

    public static volatile boolean playyed = false;


    public void set_erroe_messege(String x) {


        er_1 = x;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initModality(Modality.WINDOW_MODAL);

        if(er_1.length()>150){
            ScrollPane node = new ScrollPane();
            TextArea at = new TextArea();
            at.setText(er_1);
            node.setContent(at);
            alert.getDialogPane().setContent(node);
            alert.setTitle("System AVR say :");
            alert.setHeaderText("Обратите внимание :");

            alert.showAndWait();
            return;
        }
        alert.setTitle("System AVR say :");
        alert.setHeaderText("Обратите внимание :");
        alert.setContentText(er_1);
        alert.showAndWait();






    }

    public void setMessage_Only(String s ){
        er_1 = s;
    }

    public void setNewSceneLabel(LinkedList<Ostatku> list){



        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("error2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("SYSTEM_MESSAGE");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        controller_error2.setNer();


    }

    @Override
    protected Void call() throws Exception {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("error.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("SYSTEM_MESSAGE");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.show();
        return null;
    }
}
