package info_page.smenaMOLPACKAGE;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import sample.Controller;

public class SmenaMOL {

    public static String MOLPRYNYAL = "EMP";
    public static String MOLDOL = "EMP";

    public static final String message = "В документы будут добавлены строки сдающего и принимающего МОЛ."+System.lineSeparator()+
            "При выходе из программы, выбор типа ПИ будет сброшен."+System.lineSeparator()+
            "Так же, как и внесенные данные о принимающем МОЛ";


    public void createAlarmWindowForMol(Controller controller){

        controller.fieldSMENAMOL.setStyle("-fx-background-color:burlywood ");
        controller.dolMOLfield.setStyle("-fx-background-color:burlywood ");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information from AVR5");
        alert.setHeaderText("Вы выбрали режим СменаМОЛ");
        alert.getDialogPane().setPrefSize(500,200);
        TextArea area = new TextArea();
        area.setText(message);
        alert.getDialogPane().setContent(area);
        alert.show();


    }

}
