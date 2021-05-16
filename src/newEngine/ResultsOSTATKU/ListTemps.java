package newEngine.ResultsOSTATKU;

import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTemps {

    public static ArrayList<OstatkuSApRES> listSVerka = new ArrayList<>();
    public static int lastindex = 0;
    public static int startrow = 14;
    public static int startForSap = 1;


    public void clearing(){
        listSVerka.clear();
    }

    public static void tryAddNewPosition(String keyCode, String keyValue){


        // ОБРАТИТЬ ВНИМАНИЕ НА ПЕРЕМЕННУЮ StartREAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // ОБРАТИТЬ ВНИМАНИЕ НА ПЕРЕМЕННУЮ StartREAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // ОБРАТИТЬ ВНИМАНИЕ НА ПЕРЕМЕННУЮ StartREAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        char column = getSimbolColumn(keyCode);
        int columnNumber = getNumberRow(keyCode);
        boolean complite = forComplite(column);

        if(complite){listSVerka.add(new OstatkuSApRES(column,columnNumber,keyValue));lastindex = columnNumber;}

        keyValue = null;
        keyCode = null;

    }

    public void tryAddNewPosition(String keyCode, String keyValue,int operation){

        // SAP READ


        char column = getSimbolColumn(keyCode,0);
        int columnNumber = getNumberRow(keyCode);
        boolean complite = forComplite(column,0);

        if(complite){listSVerka.add(new OstatkuSApRES(column,columnNumber,keyValue));lastindex = columnNumber;}



    }

    public static char getSimbolColumn(String keyKode,int operation){


        //SAP READ

        Pattern p = Pattern.compile("([A-Z].*?)");
        Matcher m = p.matcher(keyKode);

        while (m.find()){
            String s = m.group(1);
            return s.charAt(0);
        }

        return 'Z';


    }

    public static char getSimbolColumn(String keyKode){

        Pattern p = Pattern.compile("([A-Z].*?)");
        Matcher m = p.matcher(keyKode);

        while (m.find()){
            String s = m.group(1);
            return s.charAt(0);
        }

        return 'Z';


    }

    public static boolean forComplite(char c,int operation){
        // Список допущенных к проверке столбцов
        // SAP READ
        if(c=='A'){return true;}
        if(c=='B'){return true;}
        if(c=='C'){return true;}

        return false;
    }


    public static int getNumberRow(String keyKode){

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(keyKode);

        while (m.find()){
            String s = m.group(0);
            return Integer.parseInt(s);
        }
        return 999999;
    }

    public static boolean forComplite(char c){
        // Список допущенных к проверке столбцов
        if(c=='A'){return true;}
        if(c=='B'){return true;}
        if(c=='C'){return true;}
        if(c=='D'){return true;}
        if(c=='H'){return true;}
        if(c=='I'){return true;}
        if(c=='J'){return true;}
        if(c=='K'){return true;}
        if(c=='L'){return true;}
        if(c=='M'){return true;}


        return false;
    }

    public static void genereteNewOstatku(){

        Main.classOstatku.clear();
        System.out.println(lastindex);
        for(int i = startrow ; i < lastindex ; i++){
            OstatkuSApRES.getfromIndex(i);
        }
        OstatkuSApRES.getfromIndex(lastindex);

        for(OstatkuSApRES cg : listSVerka){
            cg = null;
        }
        ListTemps.listSVerka.clear();
        listSVerka = new ArrayList<>();
        System.gc();

    }

    public static void genereteSapOstatku(){

        System.out.println(lastindex);
        for(int i = startForSap ; i < lastindex ; i++){
            //OstatkuSApRES.getfromIndexSAP(i);
        }

        //OstatkuSApRES.getfromIndexSAP(lastindex);
        ListTemps.listSVerka.clear();
    }

    public static void genereteGrath(){

        for(int i = 0 ; i < lastindex ; i++){
            OstatkuSApRES.addNewGrathikPos(i);
        }
        OstatkuSApRES.addNewGrathikPos(lastindex);
        ListTemps.listSVerka.clear();
    }

    public static void generetePathG2(){

        for(int i = 0 ; i < lastindex ; i++){
            OstatkuSApRES.updateGrathG2(i);
        }

        OstatkuSApRES.updateGrathG2(lastindex);

    }



}
