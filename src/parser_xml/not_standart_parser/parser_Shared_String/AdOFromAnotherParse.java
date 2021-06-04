package parser_xml.not_standart_parser.parser_Shared_String;

import chekCrashPLU.IgnoredPlu;
import decriptor.ConsoleAVR;
import sample.Main;
import warehouse_plu.BaseGroup;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.LinkedList;

public class AdOFromAnotherParse {

    public static int size_list;
    private static StringBuilder plu  = new StringBuilder();


    public static void addNewObjectToList(LinkedList<String> list){

        plu.setLength(0);

        try {

            //String plu = list.get(0);
            plu.append(list.get(0));
            short name = BaseGroup.addnewGroupIndexAndName(list.get(1));

            short gyu3 = BaseGroup.addnewGroupIndexAndName(list.get(2));
            String guy2 = list.get(3);

            String thatCount = list.get(6);
            ArrayList<String> area = new ArrayList<>();
            area.add(list.get(7));
            double qf = Double.parseDouble(list.get(8));
            double qYc = Double.parseDouble(list.get(9));
            double oddsCount = Double.parseDouble(list.get(10));
            double oddsSumm = Double.parseDouble(list.get(11));

            String control = list.get(12);

            Main.classOstatku.add(new Ostatku(thatCount,plu.toString(),name,gyu3,guy2,area,qf,qYc,oddsCount,oddsSumm,control));
        }
        catch (Exception e){
            e.printStackTrace();
            ConsoleAVR.printlnn("Ошибки при добавлении в лис AdOFromAnotherParse " + e.getMessage());

        }

    }

}
