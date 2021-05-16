package all_controllers;

import all_controllers.logicAnalitic.CreateLabel_to_YU3;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import warehouse_plu.odds_from_group.Odds_Level_two;
import warehouse_plu.odds_from_group.Odds_level_One;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class Accordeon_Controller {


    public static TreeMap<Odds_level_One,ArrayList<Odds_Level_two>> listlist = new TreeMap<>();

    /*
         Этот класс возвращает дерево разницы по группам
         В виде Titled Pane
         аккордеон
         ТГ
         слайды
     */


    public TitledPane createAccordeonOddsGroup(){

        listlist = give_me_all_group();

        TitledPane paneF = new TitledPane();
        paneF.setText("Разница по группам");

        FlowPane fpr = new FlowPane();
        fpr.setOrientation(Orientation.VERTICAL);

        ScrollPane scr = new ScrollPane();

        fpr.getChildren().add(accordion_for_yu2());
        scr.setContent(fpr);
        paneF.setContent(scr);

        return paneF;
    }

    public FlowPane accordion_for_yu2(){

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.VERTICAL);

        for(Map.Entry<Odds_level_One,ArrayList<Odds_Level_two>> entry : Odds_level_One.all_list_map.entrySet()){
            TitledPane tlp = new TitledPane();
            tlp.setExpanded(false);
            tlp.setContent(accordion_for_yu3(entry.getValue()));
            tlp.setText(entry.getKey().getName() + " [ " + entry.getKey().getSum() + " ]");
            pane.getChildren().add(tlp);
        }

        return pane;
    }

    public FlowPane accordion_for_yu3(ArrayList<Odds_Level_two> list){

        FlowPane pane = new FlowPane();
        pane.setOrientation(Orientation.VERTICAL);


        for(Odds_Level_two target : list){

            TitledPane paneF2 = new TitledPane();

            paneF2.setOnMouseClicked(event -> new CreateLabel_to_YU3().create_flow_pane_extended_LEVEL_FOR(paneF2,target.getName()));
            paneF2.setText(target.getName() + "  [ "
                    + String.valueOf(target.getSum()) + " ]");           // Присвоение имени + суммы уровня 2

            pane.getChildren().add(paneF2);
        }

        return pane;
    }

    public static TreeMap<Odds_level_One, ArrayList<Odds_Level_two>> give_me_all_group(){

        return Odds_level_One.all_list_map;
    }

}
