package fin_kp_AI;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class BoxAnotherTMC {

    public static ArrayList<String> listBoxKpFin = new ArrayList<>();

    public static ChoiceBox<String> box_copy;

    static {
        listBoxKpFin.add("Алкоголь");
        listBoxKpFin.add("Весовые товары");
        listBoxKpFin.add("Сигареты");
    }

    public void setForBoxNames(ChoiceBox<String> box ){

        box.setItems(FXCollections.observableArrayList(listBoxKpFin));
        box.getSelectionModel().select(0);
        box_copy = box;

    }

    public static String getNowPicked(){

        return box_copy.getSelectionModel().getSelectedItem();
    }



}
