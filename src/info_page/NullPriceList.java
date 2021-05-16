package info_page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class NullPriceList {



    public ObservableList<Ostatku> getnullPrice(){
        ArrayList<Ostatku> temp = new ArrayList<>();
        for(Ostatku z : Main.classOstatku){
            if(z.oddsSUM==0.0){
                if(z.oddsCOUNT != 0.0){
                    temp.add(z);
                }
            }
        }

        ObservableList<Ostatku> ost = FXCollections.observableArrayList(temp);
        return ost;
    }

}
