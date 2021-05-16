package sklad_KP_AI.settngsHa;

import chekCrashPLU.Karantin.ControlClassKarantin;
import chekCrashPLU.Karantin.KarantinPLU;
import error_package.Modal_Error;
import exportXLSX.kpSkladXlsX.KpppSKLADExport;
import info_page.Info_po_inin;
import javafx.application.Platform;
import javafx.concurrent.Task;
import sample.Main;
import sklad_KP_AI.Algorithm_sklad;
import sklad_KP_AI.Container_KP_Sklad;
import sklad_KP_AI.Set_random_Algotirhm;
import warehouse_plu.Ostatku;

import java.util.TreeSet;

public class AlgorithmHalfHandAuto extends Task<Void> {

    public static double current_progress = 0.0;
    public static volatile StringBuilder sbs = new StringBuilder();
    /**
     * полу-автомат
     * @return
     * @throws Exception
     */


    @Override
    protected Void call() throws Exception {

        sbs.setLength(0);

        Container_KP_Sklad.list_kp_sklad.clear();    // Обнулим перед входом предыдущий кп_склад
        current_progress = 0;

        Info_po_inin.sb.setLength(0);

            for(MetaHalfAutoSet ms : MetaHalfAutoSet.listMeta){

                // Обратимся к предыдущему листу, чтобы получить значения групп
                String s = addNewPosition(ms.getNameYu3Field(),ms.getCount_qf_min(),ms.getMax_count_plu());
                if(!s.contains("= 0")){sbs.append(s);}
                if(Container_KP_Sklad.list_kp_sklad.size()==50){break;}
                s = null;
            }

            if(Container_KP_Sklad.list_kp_sklad.size()<50){
                sbs.append(System.lineSeparator());
                sbs.append("Увеличьте у групп МАКС PLU в лобби склад");
                Platform.runLater(()->{new Modal_Error().set_erroe_messege(sbs.toString());});
            }

            updateMessage("Выполнено " + Container_KP_Sklad.list_kp_sklad.size());

       if(Container_KP_Sklad.list_kp_sklad.size()<50){
           System.out.println("Проверка сработала");
           updateMessage("Собрано только " + Container_KP_Sklad.list_kp_sklad.size() + " Установите больше разрешений!");
       }


        //Rule_contollers_Main.unmind_listner();

        new Container_KP_Sklad().saving();
        // Выгрузка в XLSX
        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        kpppSKLADExport.startExport();

//        Platform.runLater(()->{
//            Rule_contollers_Main.main_controller.main_pain.getSelectionModel().select(Rule_contollers_Main.main_controller.tab_Hand_auto);
//        });

        return null;
    }

    public void updateMessageLabel(String z){
        updateMessage(z);
    }

    public void updateMessageAreaText(String z){
        //Info_po_inin.sb.append(z);
        //updateTitle(Info_po_inin.sb.toString());
    }


    public void updateProgressBar(){
        updateProgress(current_progress,100);

    }


    public String addNewPosition(String name_group,int qf_min_for_group, int max_plu_for_group){

        int minqfST = qf_min_for_group;
        int qMaxPlu = max_plu_for_group;
        int corqle_zize = 3000;            //Переменная цикла для каждой группы

        int vsego_dobavleno = 0;


        // updateMessageAreaText("По группе : " + name_group + System.lineSeparator() + "Требуется : "
        // + max_plu_for_group + "шт., Где Qфин : " + qf_min_for_group + System.lineSeparator());

        Ostatku tempOstatku;

        TreeSet<String> wasSeeThisPlu = new TreeSet<>();                             // Храним переменные(сколько ещё)
        Set_random_Algotirhm sra = new Set_random_Algotirhm(); // +                  // Можно добавить

        while (true) {

            if(Container_KP_Sklad.list_kp_sklad.size()==50){break;}                   // Выход если есть 50 позиций

            tempOstatku = Main.classOstatku.get(Algorithm_sklad.get_random_index());   //Получим случайный объект

            boolean only_sklad = Algorithm_sklad.get_zal(tempOstatku);                 // Только склад!

            if(!only_sklad){continue;}

            if(!sra.ignore_pulse(tempOstatku)){continue;}

            boolean karantin = false;
            for(KarantinPLU s : ControlClassKarantin.listKarantinPlu){
                if(s.getPlu().equals(tempOstatku.getPlu())){
                    karantin = true;}                                                 // Не добавлять карантинные
            }
            if(karantin){continue;}



            if (tempOstatku.getYu3().equals(name_group)) {

                //updateMessageLabel("Проверка " + tempOstatku.plu);

                // Вход в группу по случайному индексу

                if (tempOstatku.qfinal >= minqfST) {

                    if (qMaxPlu <= 0) {

                        // Если допуска больше нет, выходим
                        wasSeeThisPlu.add(tempOstatku.plu);
                        continue;
                    }

                    boolean ad = tryAddNewPositionKpSklad(tempOstatku.plu);    // Проверка на присутствие

                    if (ad) {

                        // Добавим позицию если все проверки пройдены успешно

                        Container_KP_Sklad.list_kp_sklad.add(tempOstatku.plu);
                        qMaxPlu--; current_progress+=2; updateProgressBar();
                        vsego_dobavleno++;
                        updateMessageAreaText("Добавлена PLU : " + tempOstatku.plu + " QF [" + tempOstatku.qfinal + "] " + System.lineSeparator());
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

            corqle_zize--;

            if(corqle_zize<=0){

                break;
            }

        }
        return "YU3 = " + name_group + " / CAN BE MORE ON... = " + wasSeeThisPlu.size() + System.lineSeparator();
        //updateMessageAreaText("Всего добавлено : " + vsego_dobavleno + " PLU. " + " Можно добавить ещё : ~" + wasSeeThisPlu.size());
        //updateMessageAreaText(System.lineSeparator());
        //updateMessageAreaText(System.lineSeparator());


    }

    public static boolean tryAddNewPositionKpSklad(String pluxa){

        // Попытка добавить новую позицию в кп_склад

        for(String z : Container_KP_Sklad.list_kp_sklad){
            if(z.equals(pluxa)){return false;}
        }

        return true;
    }



}
