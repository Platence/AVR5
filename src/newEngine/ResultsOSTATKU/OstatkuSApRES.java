package newEngine.ResultsOSTATKU;

import chekCrashPLU.IgnoredPlu;
import sample.Main;
import warehouse_plu.BaseGroup;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class OstatkuSApRES {

    // Содержит данные о ячейке и значении



    private char column;
    private int rowNumber;
    private String value;


    public OstatkuSApRES(char column, int rowNumber, String value) {
        this.column = column;
        this.rowNumber = rowNumber;
        this.value = value;

    }


    public char getColumn() {
        return column;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public String getValue() {
        return value;
    }

    public static void getfromIndex(int index){

        Ostatku ostatku = new Ostatku();

        for(OstatkuSApRES ost : ListTemps.listSVerka){
            if(index==ost.rowNumber){
                try {
                    if (ost.column == 'A') {
                        ostatku.plu = ost.value;
                    }
                    if (ost.column == 'B') {
                        ostatku.name = ost.value;
                    }
                    if (ost.column == 'C') {
                        ostatku.yu2 = BaseGroup.addnewGroupIndexAndName(ost.value);
                    }
                    if (ost.column == 'D') {
                        ostatku.yu3 = BaseGroup.addnewGroupIndexAndName(ost.value);
                    }
                    if (ost.column == 'H') {
                        ostatku.setaddAllpositionGates(ost.value);
                    }
                    if (ost.column == 'I') {
                        ostatku.qfinal = Double.parseDouble(ost.value);
                    }
                    if (ost.column == 'J') {
                        ostatku.qychetnoe = Double.parseDouble(ost.value);
                    }
                    if (ost.column == 'K') {
                        ostatku.oddsCOUNT = Double.parseDouble(ost.value);
                    }
                    if (ost.column == 'L') {
                        ostatku.oddsSUM = Double.parseDouble(ost.value);
                    }
                    if (ost.column == 'M') {
                        ostatku.controlChek = ost.value;
                    }

                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        }
        if(!IgnoredPlu.checkFromTotal(ostatku.plu)){return;}



        Main.classOstatku.add(ostatku);

    }

    public static double getDouble(String value){

        try{return Double.parseDouble(value);}
        catch (Exception e){
            System.out.println(e.getMessage() + " PARSE DOUBLE!");
        }
        return 9999.99;
    }

    public static void addNewGrathikPos(int index){

    }

    // ПЕРЕЙТИ В КЛАСС forComplite LIST TEMPS!!!! Для активации Нужных Ячеек!!!!!!!!!

    public static void updateGrathG2(int index){

        for(OstatkuSApRES ost : ListTemps.listSVerka){

            if(index==ost.rowNumber){
                try {

                    if (ost.column == 'D') {

                        int dateddated = ost.rowNumber;
                        String date = getValueFromIndexSVod(dateddated,'C');


                        int indexShopName = ost.rowNumber;
                        String shopName = getValueFromIndexSVod(indexShopName,'B');

                        String valueD = ost.getValue();


                    }
                }

                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getValueFromIndexSVod(int indexRow, char column){

        for(OstatkuSApRES xxx : ListTemps.listSVerka){
            if(indexRow == xxx.getRowNumber() && xxx.getColumn()==column){return xxx.getValue();}
        }


        return "ERROR";
    }




}
