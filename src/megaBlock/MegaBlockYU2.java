package megaBlock;

import chekCrashPLU.IgnoredPlu;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class MegaBlockYU2 {

    /*
    Полностью отключает группу по УИ 2 для актов КП
     */

    public static ArrayList<String> listgroupYU2 = new ArrayList<>();
    public static ArrayList<String> listgroupYU3= new ArrayList<>();


    public static void loadingYU2(){

        for(IgnoredPlu  s : IgnoredPlu.listIgnoredPly){
            listgroupYU2.add(s.getPli());
            listgroupYU3.add(s.getPli());
        }
        listgroupYU2.add("Хлеб СП");
        listgroupYU2.add("Сырье СП");

    }

    public static boolean chekPositionToMainRulesYU2(Ostatku targed){
        for(String z : listgroupYU2){
            if(z.equals(targed.getYu2())){return true;}
        }
        return false;
    }
}
