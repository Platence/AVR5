package warehouse_plu;

import all_controllers.logicAnalitic.Bundle_For_WRS;
import chekCrashPLU.IgnoredPlu;
import parser_xml.SAXPars;
import sample.Main;

import java.util.ArrayList;
import java.util.Objects;

public class Ostatku {


    public String plu = "";
    public String name = "";
    public short yu2;
    public short yu3;
    public String that_count ="";
    public ArrayList<String> gates = new ArrayList<>(); // Зоны подсчёта
    public double qfinal;
    public double qychetnoe;
    public double oddsCOUNT = 0;
    public double oddsSUM = 0;
    public String controlChek = "";
    private double countSap = 0.0;


    public String lastDateImport = "";  // "NULL" в конструкторе
    public String lastDateSale = "";    // "NULL" в конструкторе
    public double ostatStoumost = 0.0;
    public boolean for_act_KP = false;
    public boolean neprodamBOL = false;
    public boolean controlReport = false;
    public boolean dateProdajaTRUE = false;
    public String area_one_strok = "";
    public int priority_NPR;  // 0 = NULL 1- high ; 2 - middle ; 3 - low ; 4 - verylow
    public boolean isneprodam = false;
    public boolean visible_Ostatku;
    private int blockedZap = 0;

    public static int count_sklad_plu = 0;
    public static double final_result = 0.0;

    public Ostatku(String plu, String name, short yu2, short yu3,  String that_count,
                   ArrayList<String> gates, double qfinal, double qychetnoe, double oddsCOUNT, double oddsSUM, String controlChek) {

        if(controlChek.length()<1){controlChek="EMP";}

            try{int x = Integer.parseInt(plu);x=0;}

            catch (Exception e){
                this.plu = "ERROR";
                return;
            }

        if(!IgnoredPlu.checkFromTotal(plu)){
            //System.out.println(plu + " WORK");
            return;
        }

        this.plu = plu;
        this.name = name;
        this.yu2 = yu2;
        this.yu3 = yu3;
        this.that_count = that_count;
        this.qfinal = qfinal;
        this.qychetnoe = qychetnoe;
        this.oddsCOUNT = oddsCOUNT;
        this.oddsSUM = oddsSUM;
        this.controlChek = controlChek;
        for(String zxc : gates){     // Эта часть была заменена с this.gates = gates;
            this.gates.add(zxc);
            area_one_strok+=zxc;
        }
        this.lastDateImport ="NULL";
        this.ostatStoumost = 0.0;
        this.for_act_KP = false;
        this.neprodamBOL = false;
        this.controlReport = false;
        this.lastDateSale="NULL";
        this.dateProdajaTRUE = false;
        this.priority_NPR = 0;
        this.isneprodam = false;
        this.visible_Ostatku = true;

        add_count(this);

        if(this.qfinal>0){All_List_group_info.add(this.getYu3());}

    }

    public Ostatku(){
        // НЕСТАНДАРТНЫЙ ПОТОК СОЗДАЕТСЯ В КЛАССЕ LISTTEMPS!
    }

    public void setaddAllpositionGates(String s){
        //System.out.println(s + " CONNETCION  :");
        String[] ccc = s.split(",");

        for(int i = 0 ; i < ccc.length; i ++){
            StringBuilder temp = new StringBuilder();
            temp.append(ccc[i].replace(" ",""));
            this.gates.add(temp.toString());
            this.area_one_strok+=temp.toString();
            //ccc[i] = temp.toString();
            //System.out.println(ccc[i] + " TEST CONECTION GATES");
            temp.setLength(0);
        }
    }

    public int getPriority_NPR() {
        return priority_NPR;
    }

    public String getLastDateImport() {
        return lastDateImport;
    }

    public String getLastDateSale() {
        return lastDateSale;
    }

    public String getReturn_boolean() {
        return_boolean = String.valueOf(isneprodam);
        return return_boolean;
    }

    public String return_boolean = "";


    public double getOddsSUM() {
        return oddsSUM;
    }

    public double getCountSap() {
        return countSap;
    }

    public void setCountSap(double countSap) {
        this.countSap = countSap;
    }

    public double getOddsCOUNT() {
        return oddsCOUNT;
    }

    public String getControlChek() {
        return controlChek;
    }

    public String getPlu() {
        return plu;
    }

    public String getName() {
        return name;
    }

    public double getQfinal() {
        return qfinal;
    }

    public double getQychetnoe() {
        return qychetnoe;
    }

    public static void includeNEPRODAM(String plushka,String datainclude,double summa,String datalastprod){
        //Подгружаем непродовайки в список разниц, только те где q FIN > 0;
        for(Ostatku o : Main.classOstatku){
            if(o.plu.equals(plushka)){
                if(o.qfinal>0){ o.ostatStoumost = summa;o.lastDateImport = datainclude;o.neprodamBOL=true;o.lastDateSale = datalastprod;}
            }
        }
    }

    public static double xxx (double a,double b,double c,double d){
        return 0.0;
    }

    public String getYu2(){
        return BaseGroup.getMeNameFromIndex(yu2);
    }

    public String getYu3(){
        return BaseGroup.getMeNameFromIndex(this.yu3);
    }

    public String getArea_one_strok(){
        return area_one_strok;
    }

    public void add_count(Ostatku ddd){
        /*
        Подсчет товара в складе
         */

        if(ddd.qfinal==0){return;}
        for(String aaa : ddd.gates){
            if(aaa.contains("777")){return;}
            if(aaa.contains("888")){return;}
            if(aaa.contains("999")){return;}
            if(aaa.contains("-")){return;}
            if(aaa.equals("-")){return;}
            if(aaa.contains("A")){return;}
            if(aaa.contains("B")){return;}
            if(aaa.contains("C")){return;}
            if(aaa.contains("D")){return;}
            if(aaa.contains("E")){return;}
            if(aaa.contains("F")){return;}

        }

        count_sklad_plu++;
    }

    public int getBlockedZap() {
        return blockedZap;
    }

    public void setBlockedZap(int blockedZap) {
        this.blockedZap = blockedZap;
    }

    @Override
    public String toString() {

        return "Ostatku{" +
                "plu='" + plu + '\'' +

                ", name='" + name + '\'' +
                ", yu2='" + yu2 + '\'' +
                ", yu3='" + yu3 + '\'' +
                ", codeyu='" + '\'' +
                ", yu4='" + '\'' +
                ", that_count='" + that_count + '\'' +
                ", gates=" + gates +
                ", qfinal=" + qfinal +
                ", qychetnoe=" + qychetnoe +
                ", oddsCOUNT=" + oddsCOUNT +
                ", oddsSUM=" + oddsSUM +
                ", controlChek='" + controlChek + '\'' +
                ", lastDateImport='" + lastDateImport + '\'' +
                ", lastDateSale='" + lastDateSale + '\'' +
                ", ostatStoumost=" + ostatStoumost +
                ", for_act_KP=" + for_act_KP +
                ", neprodamBOL=" + neprodamBOL +
                ", controlReport=" + controlReport +
                ", dateProdajaTRUE=" + dateProdajaTRUE +
                ", count_prosroka='" + '\'' +
                ", area_one_strok='" + area_one_strok + '\'' +
                ", priority_NPR=" + priority_NPR +
                ", isneprodam=" + isneprodam +
                ", visible_Ostatku=" + visible_Ostatku +
                ", return_boolean='" + return_boolean + '\'' +
                '}';
    }

    public String to_String_detail(Ostatku x){
        return "plu : -> " + x.plu + System.lineSeparator() +
                "Name : -> " + x.name + System.lineSeparator() +
                "Area : -> " + x.area_one_strok + System.lineSeparator() +
                "Qфин : -> " + x.qfinal + System.lineSeparator() +
                "Qуч : -> " + x.qychetnoe + System.lineSeparator() +
                "ODDS шт : -> [ " + x.oddsCOUNT + " ]" + System.lineSeparator() +
                "ODDS SUMM 0.0 RUR : -> [ " +x.oddsSUM +  " ]" + System.lineSeparator() +
                "CONROL_C (KP) : -> " + x.controlChek + System.lineSeparator() +
                System.lineSeparator() +
                "EMP =  нет контрольного пересчета " + System.lineSeparator() + System.lineSeparator()
                ;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ostatku ostatku = (Ostatku) o;
        return Objects.equals(plu, ostatku.plu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plu);
    }
}
