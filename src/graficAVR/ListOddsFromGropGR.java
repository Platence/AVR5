package graficAVR;

import error_package.Modal_Error;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListOddsFromGropGR {


    public static ArrayList<ListOddsFromGropGR> list = new ArrayList<>();
    public static LinkedList<Ostatku> lastTempList = new LinkedList<>();

    private int startSumm ;
    private int endSumm;
    private AnchorPane pane;


    public ListOddsFromGropGR(int startSumm, int endSumm, AnchorPane pane) {
        this.startSumm = startSumm;
        this.endSumm = endSumm;
        this.pane = pane;
    }

    public static void addNewPosition(AnchorPane pane,int startSumm, int endSumm){

        ListOddsFromGropGR obj = new ListOddsFromGropGR(startSumm,endSumm,pane);

        //Передадим параметры для нового fxml
        obj.getPane().setOnMouseClicked(
                e-> obj.showObjectOdds(GraficAvrProd.currentGroup, obj.getStartSumm(),obj.getEndSumm())
        );


        list.add(obj);

    }


    public static void addNewPositionSurplus(AnchorPane pane,int startSumm, int endSumm){

        ListOddsFromGropGR obj = new ListOddsFromGropGR(startSumm,endSumm,pane);

        //Передадим параметры для нового fxml
        obj.getPane().setOnMouseClicked(
                e-> obj.showObjectOdds(GraficAvrProd.currentGroup, obj.getEndSumm(),obj.getStartSumm())
        );


        list.add(obj);

    }




    public void showObjectOdds(String nameGroup, int beginSumm, int endSumm){

        lastTempList.clear();



        for(Ostatku ddd : Main.classOstatku){
            if(ListXY.ignoredSpecialZone(ddd)){continue;}
            if(nameGroup.equals("ALL")){
                if(ddd.oddsSUM>=beginSumm && ddd.oddsSUM<=endSumm){
                    lastTempList.add(ddd);
                }
            }

            if(!nameGroup.equals("ALL") && ddd.getYu2().equals(nameGroup)){
                if(ddd.oddsSUM>=beginSumm && ddd.oddsSUM<=endSumm){
                    lastTempList.add(ddd);
                }
            }

        }

        StringBuilder sb = new StringBuilder();

        for(Ostatku sss : lastTempList){
            sb.append(sss.to_String_detail(sss));
        }

        new Modal_Error().setNewSceneLabel(lastTempList);
        sb.setLength(0);

    }


    public int getStartSumm() {
        return startSumm;
    }

    public void setStartSumm(int startSumm) {
        this.startSumm = startSumm;
    }

    public int getEndSumm() {
        return endSumm;
    }

    public void setEndSumm(int endSumm) {
        this.endSumm = endSumm;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }




}
