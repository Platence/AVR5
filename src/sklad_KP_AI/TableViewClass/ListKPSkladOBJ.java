package sklad_KP_AI.TableViewClass;

import sample.Main;
import sklad_KP_AI.Container_KP_Sklad;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class ListKPSkladOBJ {

    public static ArrayList<Ostatku> listosTSKL = new ArrayList<>();

    public static void fillAllPos(){
        // Копирует все PLU из складского акта в таблицу
        listosTSKL.clear();

        for(String pluSklad :Container_KP_Sklad.list_kp_sklad){

            for(Ostatku z : Main.classOstatku){
                if(pluSklad.equals(z.getPlu())){
                    listosTSKL.add(z);break;
                }
            }
        }
        InitializateTable.updateTable();
    }

    public static void removeObj(Ostatku z){
        listosTSKL.remove(z);
        String plu = z.getPlu();
        Container_KP_Sklad.list_kp_sklad.remove(plu);
    }

    public static void addOnePosition(Ostatku z){
        Container_KP_Sklad.list_kp_sklad.add(z.getPlu());
        listosTSKL.add(z);
        InitializateTable.updateTable();
    }
}
