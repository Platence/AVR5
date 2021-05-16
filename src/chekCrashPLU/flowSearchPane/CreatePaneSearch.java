package chekCrashPLU.flowSearchPane;

import exportXLSX.exportSearch.CreateExportSearchShit;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import warehouse_plu.Ostatku;

import java.util.List;

public class CreatePaneSearch {



    public void setPaneToAlert(Alert alert, TableView<Ostatku> tableView, ObservableList<Ostatku> list){
        Pane p = new Pane();
        Button button = new Button("EXPORT");
        button.setOnAction(e->exportMetaToExcel(list));
        button.setLayoutY(348);
        button.setLayoutX(710);

        tableView.setLayoutX(10);
        tableView.setLayoutY(-2);

        p.getChildren().add(tableView);
        p.getChildren().add(button);
        alert.getDialogPane().setContent(p);
        alert.show();
    }

    private void exportMetaToExcel(ObservableList<Ostatku> list){
        new CreateExportSearchShit(list);
    }
}
