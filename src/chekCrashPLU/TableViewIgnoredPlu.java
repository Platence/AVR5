package chekCrashPLU;

import all_controllers.Rule_contollers_Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jdk.nashorn.internal.ir.annotations.Ignore;
import sample.Controller;

public class TableViewIgnoredPlu {


    public TableViewIgnoredPlu(Controller cr) {
        setSettingsToTable(cr);
    }

    public TableViewIgnoredPlu() {
    }

    public void setSettingsToTable(Controller cr){

        IgnoredPlu.loadOldList();
        // Загрузить старый лист

        cr.onlyWrsIgnored.setCellValueFactory(new PropertyValueFactory<>("boxIgnoreWrs"));
        cr.totalignorcolBox.setCellValueFactory(new PropertyValueFactory<>("boxIgnoreFull"));
        cr.ignorePluCol.setCellValueFactory(new PropertyValueFactory<>("pli"));
        cr.simpledescignorcol.setCellValueFactory(new PropertyValueFactory<>("desc"));

        cr.tableIgnor.setItems(setTableFxList());

        cr.tableIgnor.setEditable(true);

        cr.ignorePluCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<IgnoredPlu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<IgnoredPlu, String> event) {
                String ev = event.getOldValue();
                String n = event.getNewValue();
                IgnoredPlu.updateField(ev,n);
                IgnoredPlu.saveCurrentList("1","2");
                updateTable();
            }
        });
        cr.ignorePluCol.setCellFactory(TextFieldTableCell.forTableColumn());


        cr.simpledescignorcol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<IgnoredPlu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<IgnoredPlu, String> event) {
                String ev = event.getOldValue();
                String n = event.getNewValue();
                IgnoredPlu.updateField(ev,n);
                IgnoredPlu.saveCurrentList("1","2");
                updateTable();
            }
        });
        cr.simpledescignorcol.setCellFactory(TextFieldTableCell.forTableColumn());

        cr.tableIgnor.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.DELETE)){
                    String s = Rule_contollers_Main.main_controller.tableIgnor.getSelectionModel().getSelectedItem().getPli();
                    IgnoredPlu.deletePosition(s);
                    IgnoredPlu.saveCurrentList("1","1");
                    updateTable();
                }
            }
        });

    }

    public ObservableList<IgnoredPlu> setTableFxList(){
        return FXCollections.observableArrayList(IgnoredPlu.listIgnoredPly);
    }

    public void updateTable(){
        Rule_contollers_Main.main_controller.tableIgnor.getItems().clear();
        Rule_contollers_Main.main_controller.tableIgnor.setItems(setTableFxList());

    }
}
