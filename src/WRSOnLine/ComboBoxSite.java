package WRSOnLine;

import all_controllers.Rule_contollers_Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class ComboBoxSite {

    public static void initialComboBox(ComboBox<String> siteBox){

        ArrayList<String> list = new ArrayList<>();
        list.add("https://wrs.x5.ru/");
        list.add("https://os.x5.ru/");
        ObservableList<String> s = FXCollections.observableArrayList(list);
        siteBox.setItems(s);
        siteBox.setPromptText("Выберите сайт");

        siteBox.setOnAction(e->openNewSite(siteBox.getSelectionModel().getSelectedItem()));
    }

    public static void openNewSite(String selection){
        new OpenBrowser().redirectFromButton(Rule_contollers_Main.main_controller,selection);
    }
}
