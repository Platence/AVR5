package warehouse_plu;

import error_package.Modal_Error;
import sample.Main;

import java.util.ArrayList;

public class ExtendedOstatku {

    /*
    Лист склад
    склад
    список склад
     */

    public static ArrayList<Ostatku> ost = new ArrayList<>();
    public static int fullCount = 0;



    public void copy(){
        for(Ostatku z : Main.classOstatku){
            ost.add(z);
        }
        System.out.println("Copy succefull!");
    }

    public static String getQfin(String plu){

        for(Ostatku z : ost){

            if(z.plu.equals(plu)){
                int x = (int) z.qfinal;
                return String.valueOf(x);
            }
        }

        return "EMP";

    }

    public static String getAllSkladZones(String plu){

        String res = "";

        for(Ostatku z : ost){
            if(z.plu.equals(plu)){
                for(String s : z.gates){
                    res+= s + " ";
                }
                return res;
            }
        }

        return "EMP";

    }


    public static void getMeOddsSkladAndKP(){

        fullCount = 0;

        if(ost.size()<1){new Modal_Error().set_erroe_messege("Список склада пуст!");return;}

        StringBuilder sb = new StringBuilder();

        for(Ostatku xxx : Main.classOstatku){

            if(xxx.oddsCOUNT==0){continue;}
            if(xxx.oddsSUM<0){continue;}

            for(Ostatku yyy : ost ) {

                if (xxx.plu.equals(yyy.plu)) {

                    if(!yyy.controlChek.equals("EMP")){continue;}

                    if (yyy.qychetnoe > xxx.qychetnoe) {

                        int x = (int) (yyy.qychetnoe - xxx.qychetnoe);
                        int end = getInfFromQFin(xxx.qfinal) - x;

                        if(!xxx.controlChek.equals("EMP")){
                            int temp = parsingControlCount(xxx.controlChek);
                            end = temp-end;
                        }
                            int stout = getMeFullOddsSUMM(xxx.oddsSUM,xxx.oddsCOUNT,end);
                            sb.append(yyy.plu + " " + end + " = " + " " + stout);
                            sb.append(System.lineSeparator());

                    }
                }
            }
        }

        new Modal_Error().set_erroe_messege(sb.toString() + System.lineSeparator() + fullCount);

    }

    public static int parsingControlCount(String cp){

        String result = "";

        for(int i = 0; i < cp.length(); i++){

            char temp = cp.charAt(i);

            if(temp==160){
                continue;
            }

            if(temp==','){
                temp = '.';
            }
            result+=temp;
        }

        try {
            double d = Double.parseDouble(result);
            return (int) d;
        }

        catch (Exception e){
            System.out.println("Непредвиденная ошибка в расчете КП значений " + cp);
            e.printStackTrace();
            return 0;
        }

    }

    public static int getInfFromQFin(double d){

        return (int) d;
    }

    public static int getMeFullOddsSUMM(double currentSumm,double oddsCount, int end){

        /*
        расчет примерной стоимости
         */
        try{



        double tempSum =  Math.abs(currentSumm);
        double tempcount =  Math.abs(oddsCount);

        double saleForOneObj = tempSum/tempcount;


        if(saleForOneObj<9990){fullCount+=(int)saleForOneObj;}

        return (int) saleForOneObj;

        }


        catch (ArithmeticException e){

            System.out.println("Ошибка при расчете стоимости средней продажи. Возвращен 0");
            return 0;
        }

    }


}
