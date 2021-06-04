package graficAVR.scaleStepChoiceBox;

import decriptor.ConsoleAVR;
import javafx.collections.FXCollections;
import sample.Controller;

import java.util.ArrayList;

public class SetStandartScale {

    private static ArrayList<Integer> valueList;
    private static Controller controller;

    static {
        valueList = new ArrayList<>();
        valueList.add(100);
        valueList.add(200);
        valueList.add(300);
        valueList.add(400);
        valueList.add(500);
    }

    public SetStandartScale(Controller controller){
        SetStandartScale.controller = controller;
        go();
    }

    private void go(){
        controller.choiceStep.setItems(FXCollections.observableArrayList(valueList));
        controller.choiceStep.getSelectionModel().select(2);
    }

    public static int getCurrentStep(){
        int z = controller.choiceStep.getSelectionModel().getSelectedItem();
        ConsoleAVR.printlnn("Получено значение шага : " + z);
        return z;
    }
}
