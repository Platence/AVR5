package warehouse_plu;

import chekCrashPLU.IgnoredPlu;
import parser_xml.SAXPars;
import sample.Main;

import java.util.ArrayList;

public class Add_new_Object {
    public static  StringBuilder plu =  new StringBuilder();;
    public static  StringBuilder name = new StringBuilder();
    public static  StringBuilder bs_1 = new StringBuilder();
    public static  short uy2 ;
    public static  short uy3 ;
    public static  String that_count ="";
    public static  ArrayList<String> area = new ArrayList<>();
    public static  double qF =0;
    public static  double qY =0;
    public static  double oddds_count =0;
    public static  double oddds_summ =0;
    public static  String controlPoint ="";

    public static byte plu_number=1;
    public static byte name_number=2;
    public static byte uy2_number=3;
    public static byte yu3_number=4;
    public static byte codeyu_number=5;
    public static byte yu4_number=6;
    public static byte thar_count_number=7;
    public static byte area_number=8;
    public static byte qF_number=9;
    public static byte qY_number=10;
    public static byte odds_COUNT_number=11;
    public static byte odds_SUMM_number=12;
    public static byte control_POINT_number=13;
    public static byte controlCCCCCCC = 14;





    public static void init_String(String tip,byte number){

        if(tip.contains("ТСД")&&number == plu_number){return;}
        try {

            if (number == plu_number) {
                plu.append(tip);
                return;
            }
            if (number == name_number) {
                name.append(tip);
                return;
            }
            if (number == uy2_number) {
                uy2 = BaseGroup.addnewGroupIndexAndName(tip);
                return;
            }
            if (number == yu3_number) {
                uy3 = BaseGroup.addnewGroupIndexAndName(tip);
                return;
            }
            if (number == codeyu_number) {
                //codeyu = tip;
                return;
            }
            if (number == yu4_number) {
                //yu4 = tip;
                return;
            }
            if (number == thar_count_number) {
                that_count = tip;
                return;
            }
            if (number == area_number) {
                area = span_String_area(tip);
                return;
            }
            if (number == qF_number) {
                qF = Double.parseDouble(tip);
                return;
            }
            if (number == qY_number) {
                qY = Double.parseDouble(tip);
                return;
            }
            if (number == odds_COUNT_number) {
                oddds_count = Double.parseDouble(tip);
                return;
            }
            if (number == odds_SUMM_number) {
                oddds_summ = Double.parseDouble(tip);
                return;
            }
            if (number == control_POINT_number) {

                if(!chekonNumberPLU(plu.toString())) {
                    controlPoint = tip;
                    Main.classOstatku.add(new Ostatku(plu.toString(), name.toString(), uy2, uy3, that_count, area, qF, qY, oddds_count, oddds_summ, controlPoint));
                    null_for_all_field();
                }
                else null_for_all_field();
            }

            if(number == controlCCCCCCC){
                null_for_all_field();
            }

        }
        catch (Exception e){
                e.printStackTrace();
        }
    }

    public static ArrayList<String> span_String_area(String word){
        ArrayList<String> temp_l = new ArrayList<>();
        for(int i = 0 ; i < word.length(); i ++){
            if(word.charAt(i)==','){temp_l.add(bs_1.toString());bs_1.setLength(0);continue;}
            if(word.charAt(i)==' '){bs_1.setLength(0);continue;}
            bs_1.append(word.charAt(i));
        }
        temp_l.add(bs_1.toString());
        bs_1.setLength(0);
        return temp_l;
    }

    public static void null_for_all_field(){
        plu.setLength(0);
        name.setLength(0);
        uy2 =0;
        uy3 =0;
        that_count ="";
        try{area.clear();}catch (Exception e){
            System.out.println("e in 126 CLASS Add_newObject");
        }

        qF =0;
        qY =0;
        oddds_count =0;
        oddds_summ =0;
        controlPoint ="";
    }

    public static boolean chekonNumberPLU(String z ){

        try{
            String l = z.substring(0,1);
            int hg = Integer.parseInt(l);
            hg=0;
            l=null;
        }
        catch (Exception e){
            return true;
        }

        return false;
    }

}
