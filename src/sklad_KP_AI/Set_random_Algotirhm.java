package sklad_KP_AI;

import all_controllers.Rule_contollers_Main;
import interfaces_all.Settings_control_add_KPPP;
import javafx.concurrent.Task;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.TreeSet;

public class Set_random_Algotirhm implements Settings_control_add_KPPP {

    public static ArrayList<String> have_on_yu3 = new ArrayList<>();
    //Этот лист потом можно выкинуть в настройки

    static {
        have_on_yu3.add("Водка");
        have_on_yu3.add("Шоколад, батончики");
        have_on_yu3.add("Масло растительное");
        have_on_yu3.add("Коньяк и коньячные напитки");
        have_on_yu3.add("Кофе, какао, шоколад");
        have_on_yu3.add("Консервы мясные");
        have_on_yu3.add("Пиво");
        have_on_yu3.add("Конфеты");
        have_on_yu3.add("Крупы и зерновые");

        have_on_yu3.add("Ликёро-водочне");
        have_on_yu3.add("Вермуты");
        have_on_yu3.add("Вино игристое");
        have_on_yu3.add("Чай");
        have_on_yu3.add("Орехи");
        have_on_yu3.add("Заменитель материнского молока");
        have_on_yu3.add("Замороженные полуфабрикаты");
        have_on_yu3.add("Рыба замороженная");

        have_on_yu3.add("Средства личной гигиены");
        have_on_yu3.add("Средства для волос");
        have_on_yu3.add("Средства для ухода за бельем и одеж");
        have_on_yu3.add("Детские сухие смеси");


        have_on_yu3.add("Колбасные изделия");
        have_on_yu3.add("Икра");
        have_on_yu3.add("Пресервы");


        have_on_yu3.add("Крема и товары для красоты");
        have_on_yu3.add("Средства ухода за обувью");
        have_on_yu3.add("Повседневная одежда");
        have_on_yu3.add("Кухонная посуда");

        have_on_yu3.add("Корма для кошек");
        have_on_yu3.add("Корма для собак");







    }

    public static boolean checker(Ostatku xxx){
        Set_random_Algotirhm sra = new Set_random_Algotirhm();

        if(!sra.error_area(xxx)){return false;} //Проверка на зону
        if(!sra.chekqf(xxx)){return false;}    //Проверка на QF
        if(!sra.chekinout(xxx)){return false;} //Проверка доступности ин-аута
        if(!sra.ignore_pulse(xxx)){return false;} //Проверка на +
        if(sra.logic_to_add(xxx)){return true;}  //Общая логика

        return false;
    }


    @Override
    public boolean error_area(Ostatku d) {

        for(String d_area : d.gates){
            if(!d_area.contains("S")){break;}
            if(d_area.contains("999")){break;}
            if(d_area.contains("777")){break;}
            if(d_area.contains("888")){break;}
            return true;
        }

        return false;
    }



    @Override
    public boolean logic_to_add(Ostatku d) {

        //System.out.println("Подходит " + d.getPlu() + " " +  d.getName() + " " + d.getOddsCOUNT());

        return true;
    }

    @Override
    public boolean chekqf(Ostatku d) {
        if(d.getQfinal()<Algorithm_sklad.min_QF){return false;}
        return true;
    }

    @Override
    public boolean chekinout(Ostatku d) {

        for(String group_correct : have_on_yu3){
            if(d.getYu3().equals(group_correct))
            {
                return true;
            }
            //System.out.println(d.yu3.equals(group_correct) + " / " + d.yu3 + " / " + group_correct);
        }

        if(Algorithm_sklad.max_pLU_OUT>=1){
            Algorithm_sklad.max_pLU_OUT--;
            return true;
        }
        return false;
    }

    @Override
    public boolean ignore_pulse(Ostatku d) {
        if(d.getOddsCOUNT()>0 && Rule_contollers_Main.main_controller.ignor_in_out.isSelected())
        {
            return false;
        }

        return true;
    }


}
