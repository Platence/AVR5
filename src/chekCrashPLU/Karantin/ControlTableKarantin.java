package chekCrashPLU.Karantin;

import all_controllers.Rule_contollers_Main;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControlTableKarantin {

    public static void initializateTableSkladKP(){

        Rule_contollers_Main.main_controller.reazon.setCellValueFactory(new PropertyValueFactory<>("reazon"));
        Rule_contollers_Main.main_controller.descKarantin.setCellValueFactory(new PropertyValueFactory<>("desctiption"));
        Rule_contollers_Main.main_controller.pluKarantin.setCellValueFactory(new PropertyValueFactory<>("plu"));
        Rule_contollers_Main.main_controller.karaPlutable.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.DELETE)){
                    KarantinPLU kap = Rule_contollers_Main.main_controller.karaPlutable.getSelectionModel().getSelectedItem();
                    ControlClassKarantin.listKarantinPlu.remove(kap);
                    ControlClassKarantin.saveListAndUpdateTable();
                }
            }
        });

        updateTable();

    }

    public static void updateTable(){
        Rule_contollers_Main.main_controller.karaPlutable.getItems().clear();
        Rule_contollers_Main.main_controller.karaPlutable.setItems(FXCollections.observableArrayList(ControlClassKarantin.listKarantinPlu));
    }
}
