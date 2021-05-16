package all_controllers.logicAnalitic;

import error_package.Modal_Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import warehouse_plu.RememberSkladPosition;

import java.util.ArrayList;

public class AddPaneToOddsList {

    /*
       list.add("YU3");
        list.add("Area");
        list.add("SUMM");

        Вся процедура добавления логики на окно настроек вызова ODDS LIST
        Получение суммы и значения сортировки.

     */

    public void outUpload(String sortValue, int valueOdds){

        System.out.println(sortValue + " " + valueOdds);

        try {
            new RememberSkladPosition().startChek(valueOdds);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка выгрузки " + e.toString());
        }

    }

    public void addToPane(FlowPane sl){

        Label label1 = new Label("Сортировать по   ");
        sl.getChildren().add(label1);

        ChoiceBox<String> setSort = new ChoiceBox<>();
        setSort.setItems(listComand());
        setSort.setPrefSize(100,15);
        sl.getChildren().add(setSort);

        Label label = new Label("  Разница суммы от  ");
        sl.getChildren().add(label);

        TextField tf = new TextField("1500");
        tf.setPrefSize(90,15);
        sl.getChildren().add(tf);

        Button bt = new Button("Выгрузить");
        bt.setOnAction(event -> outUpload(getStringFromList(setSort),tryGetInt(tf)));
        // Отправляем запрос на выгрузку, с полученным значением сортировки и суммы.
        // Выгрузка разниц просчетов, расчет
        sl.getChildren().add(bt);

    }

    public ObservableList<String> listComand(){

        /*
            Назначает список параметров для сортировки
         */

        ArrayList<String> list = new ArrayList<>();
        list.add("YU3");
        list.add("Area");
        list.add("SUMM");
        return FXCollections.observableArrayList(list);
    }

    public static String getStringFromList(ChoiceBox<String> box){

        try{
            String s = box.getValue();
            return s;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ERROR PICK BOX");
        }

        return "Area";
    }

    public static int tryGetInt(TextField tf){
        // Попытка получить INTEGER из TextField
        try{
            String s = tf.getText();
            int z = Integer.parseInt(s);
            return z;
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка приведения строки в число " + e.getMessage());
        }

        return 1000;
    }


}
