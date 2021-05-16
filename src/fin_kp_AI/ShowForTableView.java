package fin_kp_AI;

import all_controllers.Rule_contollers_Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import warehouse_plu.Ostatku;

public class ShowForTableView {

    public static void updateTable(){

        Rule_contollers_Main.main_controller.kp_fin_table_m.setItems(getObsList());
        Rule_contollers_Main.main_controller.kp_fin_table_m.setStyle("-fx-selection-bar: yellow;");
    }

    public static void refreshTable(){
        Rule_contollers_Main.main_controller.kp_fin_table_m.refresh();
    }

    public static ObservableList<Ostatku> getObsList(){
        ObservableList<Ostatku> list = FXCollections.observableArrayList(AddPositionToTableAndList.alllist);
        new ShowForTableView().customiseFactory(Rule_contollers_Main.main_controller.control_c);
        new ShowForTableView().customiseFactoryArea(Rule_contollers_Main.main_controller.area_c);
        Rule_contollers_Main.main_controller.label_info.textProperty().unbind();
        Rule_contollers_Main.main_controller.label_info.setText("Добавлено в КП_ФИН " + AddPositionToTableAndList.alllist.size());
        Rule_contollers_Main.main_controller.yu_c.setSortType(TableColumn.SortType.DESCENDING);

        return list;
    }

    public static void clearTable(){
        Rule_contollers_Main.main_controller.kp_fin_table_m.setItems(null);
    }

    public void customiseFactory(TableColumn<Ostatku, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Ostatku, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Ostatku> currentRow = getTableRow();


                    if (!isEmpty()) {
                        if(!item.equals("EMP")){currentRow.setStyle("-fx-background-color:lightcoral");}
                        if(item.equals("EMP")){currentRow.setStyle("");}
                    }
                }
            };
        });
    }

    public void customiseFactoryArea(TableColumn<Ostatku, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Ostatku, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<Ostatku> currentRow = getTableRow();

                    if (!isEmpty()) {
                        System.out.println(" is empty " + item);
                        if(item.contains("999")&&item.length()<7){
                            System.out.println("true!");
                            currentRow.setStyle("-fx-background-color:orangered");}
                        else {

                        }

                    }
                }
            };
        });
    }

    public static void addListnerDelete(){

        Rule_contollers_Main.main_controller.kp_fin_table_m.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.DELETE){
                   Ostatku d =  Rule_contollers_Main.main_controller.kp_fin_table_m.getSelectionModel().getSelectedItem();


                    BannedPlU.addPosition2(d.plu,d.getName(),"Забанен из акта КП");
                    AddPositionToTableAndList.lastSuccefulSort.clear();
                    for(Ostatku dd : Rule_contollers_Main.main_controller.kp_fin_table_m.getItems()){
                        AddPositionToTableAndList.lastSuccefulSort.add(dd);
                    }

                   AddPositionToTableAndList.removerfromAlllist(d);

                }
            }
        });
    }

}
