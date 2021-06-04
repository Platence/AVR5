package all_controllers;


import all_controllers.mouse_event.ButtonRas4et.HoldButtodDisable;
import all_paths.Paths_Main_File;
import chekCrashPLU.CrashesPlu;
import decriptor.ConsoleAVR;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import parser_xml.SAXPars;
import parser_xml.SaxParsGU;
import passwordPackega.PassLogic;
import sample.Main;
import settings_main.Settings;
import warehouse_plu.Ostatku;
import warehouse_plu.RememberSkladPosition;

public class DownloadTask extends Task<Void> {



    @Override
    protected Void call() throws Exception {


        //Непосредственно сам парсер + передаём копию класса для обновления
        SAXPars.newsverka = false;
        new SaxParsGU().started(Paths_Main_File.path_list_diff_current, Settings.destenation_from_target,this);
        // Получение и передача файла сверки разниц

        ConsoleAVR.printlnn(Paths_Main_File.path_list_diff_current);

        //Визуальные настройки объектов после парсинга
        Rule_contollers_Main.set_settings_visible(false,true);
        Rule_contollers_Main.set_visible_element_cp_sklad();
        Rule_contollers_Main.run_pick_all_algorithm();

        update_message("Внесено в склад  : " + Ostatku.count_sklad_plu  );

        Main.classOstatku.removeIf(ostatku -> ostatku.getPlu().equals("ERROR"));



        if(!PassLogic.status){CrashesPlu.chek_all_point_PLU();}
        if(CrashesPlu.haveErrors){updateTitle(CrashesPlu.stringBuilder.toString());}

        // Фиксация списка склада
        if(Rule_contollers_Main.main_controller.skladlisttempbox.isSelected()){
                new RememberSkladPosition(true);
            Rule_contollers_Main.main_controller.skladlisttempbox.setSelected(false);
        }


        return null;
    }

    public void updating(double x){
        updateProgress(x,100);
    }

    public void update_message(String x){
        updateMessage(x);
    }




}
