package sklad_KP_AI.TableViewClass;

import all_controllers.Rule_contollers_Main;
import error_package.SlideError.SlideModalError;
import fin_kp_AI.BannedPlU;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sample.Controller;
import sklad_KP_AI.ExtendedAlg;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class InitializateTable {

    Controller cr;

    public InitializateTable(Controller cr){
        this.cr = cr;
        initTableNow();
    }

    public void initTableNow(){

        cr.plukpSklTAB.setCellValueFactory(new PropertyValueFactory<>("plu"));
        cr.plukpdescTAB.setCellValueFactory(new PropertyValueFactory<>("name"));
        cr.plukpdareaTAB.setCellValueFactory(new PropertyValueFactory<>("area_one_strok"));
        cr.plukpdoddsTAB.setCellValueFactory(new PropertyValueFactory<>("oddsCOUNT"));
        cr.plukpdqyyyTAB.setCellValueFactory(new PropertyValueFactory<>("qychetnoe"));
        cr.plukpdqFFFFTAB.setCellValueFactory(new PropertyValueFactory<>("qfinal"));
        cr.plukpdqPPPPPTAB.setCellValueFactory(new PropertyValueFactory<>("yu2"));

        cr.kpskladTable.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.DELETE)){
                    Ostatku z = cr.kpskladTable.getSelectionModel().getSelectedItem();
                    replacePosition(z);
                    new SlideModalError().setMessageWithComand(
                            "Замена plu..."
                                    +System.lineSeparator()
                                    + z.getPlu()
                                    +System.lineSeparator()
                                    +z.getName(),
                            SlideModalError.comandAddButton,
                            z.getPlu(),z.getName(),"KP SKLAD BAN");

                }
            }
        });
    }

    public static void updateTable(){
        Rule_contollers_Main.main_controller.kpskladTable.setItems(null);

        addAllPos();
        //Rule_contollers_Main.main_controller.plukpdqPPPPPTAB.setMinWidth(250);
    }

    public static void addAllPos(){
        Rule_contollers_Main.main_controller.kpskladTable
                .setItems(FXCollections.observableArrayList(ListKPSkladOBJ.listosTSKL));
        Platform.runLater(()->{Rule_contollers_Main.main_controller.info11
                .setText("Текущий размер акта " +String.valueOf(ListKPSkladOBJ.listosTSKL.size()));});

    }

    public void replacePosition(Ostatku ost){

        ListKPSkladOBJ.removeObj(ost);

        BannedPlU.listPluBanned.add(ost.getPlu());
        // Add new Position

        new ExtendedAlg(ost.getYu3());

        updateTable();
    }
}
