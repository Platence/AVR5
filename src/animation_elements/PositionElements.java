package animation_elements;

import all_controllers.Rule_contollers_Main;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class PositionElements {


    public static ArrayList<Label> listNodeLabel = new ArrayList<>();


    public void setPositionToListLabel(){
        // Добавляет все лейблы в лист
        // для управления затухания

        listNodeLabel.clear();
        listNodeLabel.add(Rule_contollers_Main.main_controller.ck1);
        listNodeLabel.add(Rule_contollers_Main.main_controller.label_changeDatePU);
        listNodeLabel.add(Rule_contollers_Main.main_controller.orgDatePu);
        listNodeLabel.add(Rule_contollers_Main.main_controller.numberShop);
        listNodeLabel.add(Rule_contollers_Main.main_controller.dateOrderLabel);


    }
}
