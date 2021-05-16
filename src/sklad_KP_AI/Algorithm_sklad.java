package sklad_KP_AI;

import all_controllers.Rule_contollers_Main;
import chekCrashPLU.IgnoredPlu;
import chekCrashPLU.Karantin.ControlClassKarantin;
import chekCrashPLU.Karantin.KarantinPLU;
import exportXLSX.kpSkladXlsX.KpppSKLADExport;
import fin_kp_AI.BannedPlU;
import info_page.Info_po_inin;
import javafx.concurrent.Task;
import megaBlock.MegaBlockYU2;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm_sklad extends Task<Void> {

    public static ArrayList<String> list_id_algorithm = new ArrayList<>();
    public static int count_for_sklad = 50;
    public static String current_pick = "Стандарт";

    public static int min_QF = 0;
    public static int max_pLU_OUT = 0;
    public static boolean ignored_pulse;






    public static void set_up_id(){
        list_id_algorithm.add("Случайный");
        list_id_algorithm.add("Стандарт");
        list_id_algorithm.add("Расширенный");

    }

    public static boolean random(Ostatku d){

        //Отсюда направляемся уже в сам класс рандом,
        //Где идёт подробный разбор
        boolean res = Set_random_Algotirhm.checker(d);

        return res;
    }


    @Override
    protected Void call() throws Exception {

        Ostatku temp;
        Container_KP_Sklad.list_kp_sklad.clear();
        count_for_sklad = 0;
        Rule_contollers_Main.main_controller.create_act_cp_sklad.setVisible(false);
        Rule_contollers_Main.main_controller.progress_bar_main.setVisible(true);
        Standart_Algorithm.ini_standart_map();
        Info_po_inin.sb.setLength(0);
        //Обнуление полей

        //Цикл, внутри котого обращаемся к выбору для каждого Ostatku
        int count_cirqle = 0;

        while (true){

            if(isCancelled()){break;}Thread.sleep(1);
            if(Algorithm_sklad.count_for_sklad==50){break;}
            //Выйдем если набрали 50 позиций


            //Получаем номер согласно размеру текущего листа
            temp = Main.classOstatku.get(get_random_index());
            boolean stay = have_already(temp); //Проверим на совпадение
            if(stay){continue;}
            if(!get_zal(temp)){continue;}  // Только позиции со склада


            //updateMessage("Проверка " + temp.getPlu() + " " + temp.getName());
            updateProgress(count_for_sklad,50);

            boolean b = false; // Temp boolean
            count_cirqle++;
            if(count_cirqle>3000){break;}
            //System.out.println(count_cirqle);

            if(current_pick.equals("Случайный")) {
               b = random(temp);           //Алгоритм здесь random
                if(b) {
                    add_position(temp);
                    //updateTitle(Info_po_inin.sb.toString());
                }
            }

            if(current_pick.equals("Стандарт")){
                 b = Standart_Algorithm.chek_new_position(temp);
                 if(b){
                    add_position(temp);
                    //updateTitle(Info_po_inin.sb.toString());
                }
            }




            if(b){
//                Info_po_inin.sb.append(Container_KP_Sklad.list_kp_sklad.size() + ") " + temp.plu + " " + temp.name + " [[[ qf = "
//                    + temp.qfinal  + " qy =  " + temp.qychetnoe + " ods =  " + temp.oddsCOUNT + " ]]]" + System.lineSeparator());
            }
            //Добавление комментария к SBUilder

        }

        Rule_contollers_Main.main_controller.create_act_cp_sklad.setVisible(true);
        Rule_contollers_Main.main_controller.progress_bar_main.setVisible(false);
        updateMessage("Выполнено");

        if(count_cirqle>3500){updateMessage(" НЕ УДАЛОСЬ СОБРАТЬ 50 позиций. Попробуйте поменять алгоритм ");}

//        int ttt = 0;
//        for (String s : Container_KP_Sklad.list_kp_sklad){
//            System.out.println(ttt + " / " + s);
//            ttt++;
//        }

        new Container_KP_Sklad().saving();
        // Выгрузка в XLSX
        KpppSKLADExport kpppSKLADExport = new KpppSKLADExport();
        kpppSKLADExport.startExport();



        return null;
    }

    public static int get_random_index(){
        int xx = (int) (Math.random() * Main.classOstatku.size());
        return xx;
    }

    public static void add_position_to_main_list(Ostatku x){
        Container_KP_Sklad.list_kp_sklad.add(x.getPlu());
    }

    public static void add_position(Ostatku temp){

        //Отсюда направляемся в сам алгоритм, если
        //Вернется тру то добавим в список ( Container)
        add_position_to_main_list(temp);count_for_sklad=Container_KP_Sklad.list_kp_sklad.size();


    }

    public static boolean have_already(Ostatku targed){

        // Если такая PLU уже есть в списке, вернем TRUE

        for(KarantinPLU plu : ControlClassKarantin.listKarantinPlu){
            if(targed.getPlu().equals(plu.getPlu())){return true;}
        }

        for(String plu : BannedPlU.listPluBanned){
            if(targed.getPlu().equals(plu)){return true;}
        }

        for(String z : Container_KP_Sklad.list_kp_sklad){
            if(z.equals(targed.plu)){return true;}
            // Это плохо! цикл возвращается на круг
        }

        if(MegaBlockYU2.chekPositionToMainRulesYU2(targed)){return true;}

        return false;
    }



    public static boolean get_zal(Ostatku ddd){


        if(!IgnoredPlu.chekFromWRS(ddd.plu)){return false;}  // Проверка события на галочку WRS в игноре

        String x = ddd.area_one_strok;

        if(x.contains("777")){return false;}
        if(x.contains("888")){return false;}


        Pattern p = Pattern.compile("[A-Z]+");
        Matcher m = p.matcher(x);

        TreeSet<String> kops = new TreeSet<>();

        while (m.find()){
            kops.add(m.group());
        }

        if(kops.contains("S")){
            if(kops.size()<=1){
                return true;
            }

        }
        return false;

    }



}
