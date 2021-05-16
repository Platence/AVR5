package all_controllers.logicAnalitic;

import chekCrashPLU.IgnoredPlu;
import megaBlock.MegaBlockYU2;
import warehouse_plu.Ostatku;

public class GlobalRulesBlock {


    public static boolean getres(Ostatku d){
        if(d.getYu2().contains("Хлеб С")){return false;}
        if(d.getYu3().contains("обственн")){return false;}
        if(!IgnoredPlu.chekFromWRS(d.getPlu())){return false;}
        if(MegaBlockYU2.chekPositionToMainRulesYU2(d)){return false;}

        return true;
    }

    public static boolean nonBred(Ostatku d){
        if(d.getYu2().contains("Хлеб С")){return false;}
        if(d.getYu3().contains("обственн")){return false;}
        return true;
    }

    public static boolean nonGramm1000(Ostatku d){
        if(d.getArea_one_strok().contains("7777")){
            if(Math.abs(d.getBlockedZap())<1000){return false;}
        }

        if(d.getArea_one_strok().contains("8888")){
            if(Math.abs(d.getBlockedZap())<1000){return false;}
        }

        return true;
    }
}
