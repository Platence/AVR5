package info_page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;

public class ListOperation {

    public static LinkedList<String> listops = new LinkedList<>();


    static {

        listops.add("Нулевые цены");
        listops.add("Собс.произв.");
        listops.add("Тара возвратная");
        listops.add("Отрицательные");
        listops.add("Запрещенные");
        listops.add("БлокЗапас");
        listops.add("ES");
    }


    public ObservableList<String> getObsListOperation() {
        // Возвращает для ComboBox
        ObservableList<String> list = FXCollections.observableArrayList(listops);
        return list;
    }
}
