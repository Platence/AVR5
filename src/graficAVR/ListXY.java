package graficAVR;

import all_controllers.Rule_contollers_Main;
import groupLevel.GroupLevelAcces;
import javafx.scene.chart.XYChart;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.*;

public class ListXY {


    public static int maxSize = 0;
    public static int sizeStep;

    public static int toShortage(int z ){

        if(z>0){
            String d = "-" + z;
            try{
                int x = Integer.parseInt(d);
                return x;
            }
            catch (Exception e){
                return z;
            }
        }
        return z;
    }

    public static LinkedList <XYChart.Data<Integer,Integer> > getSurplus(){


        LinkedList<XYChart.Data<Integer,Integer>> list = new LinkedList<>();

        maxSize = GraficAvrProd.stepMax;
        int brekaed = GraficAvrProd.stepMin;

        int treshhodOne = maxSize;
        int tresholdTwo = treshhodOne+sizeStep;

        System.out.println("Остановка на " + brekaed);
        System.out.println("Начало на " + tresholdTwo + "  / " + treshhodOne);

        //Для организации трешхолда и цикла


        while (true){

            int countPlu = 0;
            if(treshhodOne<0){break;}

            for(Ostatku temp : Main.classOstatku){
                if(standartChekOption(temp)){continue;}

                if(temp.oddsSUM>=treshhodOne && temp.oddsSUM<=tresholdTwo){
                    countPlu+=temp.oddsSUM;
                }
            }

             if(countPlu == 0){
                tresholdTwo = treshhodOne;
                treshhodOne-=sizeStep;
                continue;
             }

            if(treshhodOne==0){treshhodOne = 1;}
            list.add(new XYChart.Data<Integer, Integer>(treshhodOne,countPlu));
            System.out.println("В промежутке " + treshhodOne + " и " + tresholdTwo + " Добавлено " + countPlu);

            tresholdTwo = treshhodOne;
            treshhodOne-=sizeStep;


                if(treshhodOne<=brekaed-sizeStep){
                    break;
                  }

        }

        list.add(new XYChart.Data<>(0,0));

        list.sort(new Comparator<XYChart.Data<Integer, Integer>>() {
            @Override
            public int compare(XYChart.Data<Integer, Integer> o1, XYChart.Data<Integer, Integer> o2) {
                return o1.getXValue()>=o2.getXValue() ? 0 : 1;
            }
        });

        Collections.reverse(list);

        for(XYChart.Data<Integer,Integer> liste : list){
            System.out.println(liste.getXValue() + " X " +  "  Y : " +  liste.getYValue());
        }

        return list;


    }

    public static LinkedList <XYChart.Data<Integer,Integer> > getShortage(){

        LinkedList<XYChart.Data<Integer,Integer>> list = new LinkedList<>();

        TreeMap<Integer,Integer> shortage = new TreeMap<>();

        int valuehere = toShortage(GraficAvrProd.stepMax);

        int valuebreak = toShortage(GraficAvrProd.stepMin);

        int treshhodOne = valuehere;
        int tresholdTwo = valuehere-sizeStep;

        //Для организации трешхолда и цикла

        while (true){

            int countPlu = 0;
            if(treshhodOne>0){break;}

            for(Ostatku temp : Main.classOstatku){
                if(standartChekOption(temp)){continue;}
                if(temp.oddsSUM<=treshhodOne && temp.oddsSUM>tresholdTwo){
                    countPlu+=temp.oddsSUM;
                }
            }
            if(countPlu == 0){
                tresholdTwo = treshhodOne;
                treshhodOne+=sizeStep;
                continue;
            }

            countPlu = Math.abs(countPlu);
            if(treshhodOne==0){treshhodOne = -1;}
            shortage.put(treshhodOne,countPlu);
            System.out.println("В промежутке " + treshhodOne+ " и " + tresholdTwo + " Добавлено " + countPlu);

            tresholdTwo = treshhodOne;
            treshhodOne+=sizeStep;
            if(treshhodOne>=valuebreak+sizeStep){break;}

        }

        list.add(new XYChart.Data<Integer, Integer>(0,0));
        for(Map.Entry<Integer,Integer> sss : shortage.entrySet()){
            list.add(new XYChart.Data<Integer, Integer>(sss.getKey(),sss.getValue()));
        }


        return list;


    }


    public static LinkedList <XYChart.Data<Integer,Integer> > getSurplus(String nameGroup){

        LinkedList<XYChart.Data<Integer,Integer>> list = new LinkedList<>();

        maxSize = (int)getMaxcountODDS();
        int brekaed = (int) ValueSlider.minValue;

        System.out.println("Остановка на " + brekaed);

        int treshhodOne = 2000;
        int tresholdTwo = maxSize;

        //Для организации трешхолда и цикла

        // Method withOnlyOneGroup

        while (true){

            int countPlu = 0;

            for(Ostatku temp : Main.classOstatku){
                if(!temp.getYu2().equals(nameGroup)){continue;}
                if(standartChekOption(temp)){continue;}
                if(temp.oddsSUM>=treshhodOne && temp.oddsSUM<=tresholdTwo){
                    countPlu++;
                }
            }

            list.add(new XYChart.Data<Integer, Integer>(treshhodOne,countPlu));
            System.out.println("В промежутке " + treshhodOne + " и " + tresholdTwo + " Добавлено " + countPlu);

            tresholdTwo = treshhodOne;
            treshhodOne-=sizeStep;
            if(treshhodOne<=brekaed-sizeStep){break;}


        }
        list.add(new XYChart.Data<>(0,0));

        list.sort(new Comparator<XYChart.Data<Integer, Integer>>() {
            @Override
            public int compare(XYChart.Data<Integer, Integer> o1, XYChart.Data<Integer, Integer> o2) {
                return o1.getXValue()>=o2.getXValue() ? 0 : 1;
            }
        });

        Collections.reverse(list);

        for(XYChart.Data<Integer,Integer> liste : list){
            System.out.println(liste.getXValue() + " X " +  "  Y : " +  liste.getYValue());
        }


        return list;


    }



    public static LinkedList <XYChart.Data<Integer,Integer> > getShortage(String nameGroup){

        LinkedList<XYChart.Data<Integer,Integer>> list = new LinkedList<>();

        int valuehere = -2000;

        int valuebreak = (int) (0 - ValueSlider.minValue);

        int treshhodOne = valuehere;
        int tresholdTwo = Integer.MIN_VALUE;

        //Для организации трешхолда и цикла

        //MethodWithGroup

        while (true){


            int countPlu = 0;

            for(Ostatku temp : Main.classOstatku){
                if(!temp.getYu2().equals(nameGroup)){continue;}
                if(standartChekOption(temp)){continue;}
                if(temp.oddsSUM<=treshhodOne && temp.oddsSUM>tresholdTwo){
                    countPlu++;
                }
            }

            list.add(new XYChart.Data<Integer, Integer>(treshhodOne,countPlu));
            System.out.println("В промежутке " + treshhodOne+ " и " + tresholdTwo + " Добавлено " + countPlu);

            tresholdTwo = treshhodOne;
            treshhodOne+=sizeStep;
            if(treshhodOne>=valuebreak+sizeStep){break;}

        }

        list.add(new XYChart.Data<Integer, Integer>(0,0));

        return list;


    }


    public static boolean ignoredSpecialZone(Ostatku ostatku){

        if(ostatku.getYu3().contains("твенное произ")){return true;}
        if(ostatku.getYu3().contains("фабрикаты для")){return true;}

        return false;
    }

    public static double getMaxcountODDS(){

        double temp = 0D;

        for(Ostatku x : Main.classOstatku){
            if(x.oddsSUM==0){continue;}
            if(!ignoredSpecialZone(x)){continue;}
            if(Math.abs(x.getOddsSUM())>temp){temp=Math.abs(x.getOddsSUM());}
        }
        System.out.println(temp);
        return temp;

    }

    public static boolean standartChekOption(Ostatku temp){

        if(temp.oddsSUM==0){return true;}

        if(ignoredSpecialZone(temp)){return true;}


        return false;
    }

}
