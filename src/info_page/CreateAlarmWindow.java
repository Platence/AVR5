package info_page;

import chekCrashPLU.flowSearchPane.CreatePaneSearch;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import warehouse_plu.Ostatku;

import java.util.Collections;

public class CreateAlarmWindow {


    public void show(ObservableList<Ostatku> ost, String fileName, String operation){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/sample/dialog.css").toExternalForm());


        //alert.getDialogPane().setStyle(getClass().getResource("../sample/dialog.css").toString());
        alert.setTitle(operation);
        alert.setHeaderText(fileName);
        alert.getDialogPane().setPrefSize(880,450);



        TableView<Ostatku> tableView = new TableView<>();
        TableColumn<Ostatku,String> columnPlu = new TableColumn<>("PLU");
        TableColumn<Ostatku,String> columnName = new TableColumn<>("Name");
        TableColumn<Ostatku,String> area = new TableColumn<>("AREA");
        TableColumn<Ostatku,Double> columQf = new TableColumn<>("QF");
        TableColumn<Ostatku,Double> columQY = new TableColumn<>("QY");
        TableColumn<Ostatku,Double> oddsC = new TableColumn<>("O COUNT");
        TableColumn<Ostatku,Double> oddsS = new TableColumn<>("O SUMM");
        TableColumn<Ostatku,String> contrP = new TableColumn<>("Control");


        columnPlu.setCellValueFactory(new PropertyValueFactory<>("plu"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setMaxWidth(190);

        area.setCellValueFactory(new PropertyValueFactory<>("area_one_strok"));
        columQf.setCellValueFactory(new PropertyValueFactory<>("qfinal"));
        columQY.setCellValueFactory(new PropertyValueFactory<>("qychetnoe"));
        oddsC.setCellValueFactory(new PropertyValueFactory<>("oddsCOUNT"));
        oddsS.setCellValueFactory(new PropertyValueFactory<>("oddsSUM"));
        contrP.setCellValueFactory(new PropertyValueFactory<>("controlChek"));



        tableView.getColumns().add(columnPlu);
        tableView.getColumns().add(columnName);

        if(operation.equals("БлокЗапас")){
            TableColumn<Ostatku,String> nameGroup = new TableColumn<>("YU3");
            nameGroup.setCellValueFactory(new PropertyValueFactory<>("yu3"));
            nameGroup.setMaxWidth(85);
            tableView.getColumns().add(nameGroup);
        }

        tableView.getColumns().add(area);
        tableView.getColumns().add(columQf);
        tableView.getColumns().add(columQY);
        tableView.getColumns().add(oddsC);
        tableView.getColumns().add(oddsS);
        tableView.getColumns().add(contrP);

        if(operation.equals("БлокЗапас")){
            TableColumn<Ostatku,Integer> tableBlockZap = new TableColumn<>("LOCK/DOWN");
            tableBlockZap.setCellValueFactory(new PropertyValueFactory<>("blockedZap"));
            tableView.getColumns().add(tableBlockZap);
        }

        tableView.setItems(ost);

        if(operation.equals("ES")){
            CreatePaneSearch createPaneSearch = new CreatePaneSearch();
            createPaneSearch.setPaneToAlert(alert,tableView,ost);
            return;
        }
        alert.getDialogPane().setContent(tableView);
        alert.show();
    }

    public void showPrint(ObservableList<Ostatku> ost, String fileName, String operation){


        TableView<Ostatku> tableView = new TableView<>();
        TableColumn<Ostatku,String> columnPlu = new TableColumn<>("PLU");
        TableColumn<Ostatku,String> columnName = new TableColumn<>("Name");
        TableColumn<Ostatku,String> area = new TableColumn<>("AREA");
        TableColumn<Ostatku,Double> columQf = new TableColumn<>("QF");
        TableColumn<Ostatku,Double> columQY = new TableColumn<>("QY");
        TableColumn<Ostatku,Double> oddsC = new TableColumn<>("O COUNT");
        TableColumn<Ostatku,Double> oddsS = new TableColumn<>("O SUMM");
        TableColumn<Ostatku,String> contrP = new TableColumn<>("Control");


        columnPlu.setCellValueFactory(new PropertyValueFactory<>("plu"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        area.setCellValueFactory(new PropertyValueFactory<>("area_one_strok"));
        columQf.setCellValueFactory(new PropertyValueFactory<>("qfinal"));
        columQY.setCellValueFactory(new PropertyValueFactory<>("qychetnoe"));
        oddsC.setCellValueFactory(new PropertyValueFactory<>("oddsCOUNT"));
        oddsS.setCellValueFactory(new PropertyValueFactory<>("oddsSUM"));
        contrP.setCellValueFactory(new PropertyValueFactory<>("controlChek"));


        tableView.getColumns().add(columnPlu);
        tableView.getColumns().add(columnName);
        tableView.getColumns().add(area);
        tableView.getColumns().add(columQf);
        tableView.getColumns().add(columQY);
        tableView.getColumns().add(oddsC);
        tableView.getColumns().add(oddsS);
        tableView.getColumns().add(contrP);


        tableView.setItems(ost);

        return;

    }


}

