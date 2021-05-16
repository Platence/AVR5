package info_page.smenaMOLPACKAGE;

import all_controllers.Rule_contollers_Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import sample.Controller;

import java.util.LinkedList;

public class DescriptionSmenaMOL {

    public static final String PLAN = "Плановая";
    public static final String SMENAMOLString= "СменаМОЛ";
    public static boolean smenaMolD;


    public ObservableList<String> getCollection(){
        LinkedList l1 = new LinkedList();
        l1.add(PLAN);
        l1.add(SMENAMOLString);
        ObservableList<String> list = FXCollections.observableList(l1);
        return list;
    }

    public void createSmenaMolList(Controller cr){

        cr.fieldSMENAMOL.setVisible(false);
        cr.dolMOLfield.setVisible(false);


        cr.listSHTYPE.setItems(getCollection());
        cr.listSHTYPE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // System.out.println("Change TYPE PU");
               // System.out.println("NOW IS");
                String s = cr.listSHTYPE.getSelectionModel().getSelectedItem().toString();
                //System.out.println(s);
                if(s.equals(SMENAMOLString)){ showMESmenaMOL(cr);}
                if(s.equals(PLAN)){closeSmenaMOL(cr);}
            }
        });
    }

    public static void showMESmenaMOL(Controller cr){
        cr.buttonSave.setLayoutY(260);
        cr.fieldSMENAMOL.setVisible(true);
        cr.dolMOLfield.setVisible(true);
        cr.dMOL.setText("ДМсдал");
        DescriptionSmenaMOL.smenaMolD = true;
        new SmenaMOL().createAlarmWindowForMol(cr);
    }

    public static void closeSmenaMOL(Controller cr){
        cr.buttonSave.setLayoutY(222);
        cr.fieldSMENAMOL.setVisible(false);
        cr.dolMOLfield.setVisible(false);
        DescriptionSmenaMOL.smenaMolD = false;
        cr.dMOL.setText("ДМ");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information from AVR5");
        alert.setHeaderText("Вы выбрали режим ПЛАНОВАЯ");
        alert.getDialogPane().setPrefSize(500,200);
        TextArea area = new TextArea();
        area.setText("Тип ПИ сброшен");
        alert.getDialogPane().setContent(area);
        alert.show();
    }

    public static String getTypePU(){
        String s =  Rule_contollers_Main.main_controller.listSHTYPE.getSelectionModel().getSelectedItem();
        if(s==null){s="Плановая";}
        System.out.println("Выбрано : " + s);
        return s;
    }

    public static String getDolMOlSmenaMo(){
       String s = Rule_contollers_Main.main_controller.dolMOLfield.getText();
       return s;
    }

    public static String getFIOPrynyalMOL(){
        String s = Rule_contollers_Main.main_controller.fieldSMENAMOL.getText();
        return s;
    }
}
