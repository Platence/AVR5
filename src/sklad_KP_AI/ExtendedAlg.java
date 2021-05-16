package sklad_KP_AI;

import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import fin_kp_AI.BannedPlU;
import megaBlock.MegaBlockYU2;
import sample.Main;
import sklad_KP_AI.TableViewClass.ListKPSkladOBJ;
import warehouse_plu.Ostatku;

import java.util.ArrayList;

public class ExtendedAlg {

    public String yu3G;
    boolean succefull;
    byte count = 0;

    public ExtendedAlg(String yu3G) {
        this.yu3G = yu3G;
        Rule_contollers_Main.main_controller.label_info.textProperty().unbind();
        tryReplacePosition();

    }

    public void tryReplacePosition(){

        boolean ignored = Rule_contollers_Main.main_controller.ignor_in_out.isSelected();
        double minQf = 5;


        for(Ostatku d : Main.classOstatku){
            if(count==0){if(!d.getYu3().equals(this.yu3G)){continue;}}
            if(ignored&&d.getOddsCOUNT()>0){continue;}
            if(Math.abs(d.getQfinal())<minQf){continue;}
            if(containsBlockPlu(d.getPlu())){continue;}
            if(MegaBlockYU2.chekPositionToMainRulesYU2(d)){continue;}               // Общее правило для всех
            if(d.getArea_one_strok().contains("A")){continue;}
            if(d.getArea_one_strok().contains("B")){continue;}
            if(d.getArea_one_strok().contains("H")){continue;}
            if(d.getArea_one_strok().contains("D")){continue;}
            if(d.getArea_one_strok().contains("C")){continue;}
            if(d.getArea_one_strok().contains("Q")){continue;}
            if(d.getArea_one_strok().contains("S9999")){continue;}
            ListKPSkladOBJ.addOnePosition(d);
            Rule_contollers_Main.main_controller.label_info.setText("Добавлена PLU : "
                    + d.getPlu() + "  "
                    + d.getYu2() + "  "
                    + d.getName());

            succefull = true;return;
        }
        if (count==1){new Modal_Error().set_erroe_messege("Не удалось заменить PLU.Рекомендуется создать список склада заново!"); return;}
        if (!succefull){
            this.count=1;
            this.tryReplacePosition();
            new Modal_Error().set_erroe_messege("Не удалось найти PLU по группе"+ this.yu3G + System
                    .lineSeparator()+"Автозамена на другую группу.");

        }
    }

    public boolean containsBlockPlu(String plu){

        for(String z : Container_KP_Sklad.list_kp_sklad){
            if(z.equals(plu)){return true;}
        }

        for(String s : BannedPlU.listPluBanned){
            if(s.equals(plu)){return true;}
        }


        return false;
    }
}
