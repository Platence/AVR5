package fin_kp_AI;

import all_controllers.Rule_contollers_Main;
import chekCrashPLU.IgnoredPlu;
import chekCrashPLU.Karantin.ControlClassKarantin;
import chekCrashPLU.Karantin.KarantinPLU;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import groupLevel.GroupLevelAcces;
import megaBlock.MegaBlockYU2;
import parseNeprodam.StartNeprodamParse;
import sample.Main;
import sklad_KP_AI.Algorithm_sklad;
import sklad_KP_AI.Container_KP_Sklad;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static fin_kp_AI.ShowForTableView.clearTable;

public class AddPositionToTableAndList {

    public static String COMAND_ALCOGOL = "Алкоголь";
    public static String COMAND_VES_TMC = "Весовые товары";
    public static String COMAND_SMOKE = "Сигареты";
    public static String COMAND_NEPRODAM = "Непродам";
    public static int MAX_COUNT_NEPRODAM = 0;              // Количество непродаваек

    public static int qfMin = 0;
    public static int oddsMax = 0;
    public static boolean block_Control_chek = true;

    public static ArrayList<Ostatku> list_ALCOGOL = new ArrayList<>();
    public static ArrayList<Ostatku> list_sigarets = new ArrayList<>();
    public static ArrayList<Ostatku> list_ves_tovar = new ArrayList<>();
    public static ArrayList<Ostatku> list_neprodam = new ArrayList<>();

    public static LinkedList<Ostatku> alllist = new LinkedList<>();

    public static  int maxCoutAlc = 4;
    public static  int maxClothes = 2;
    public static int maxFood = 25;
    public static int maxNonFOOD = 5;

    public static boolean have_actSklad_in_memory = false;
    public static boolean pallet;
    public static LinkedList<Ostatku> lastSuccefulSort = new LinkedList<>();


    public void addPosition(String z, int qf, int odds, boolean blockCP){

            qfMin = qf;
            oddsMax = odds;
            block_Control_chek = blockCP;
            CheckBoxIngroreSSSS.setBoxSelected();
            if(MAX_COUNT_NEPRODAM!=0){
                maxFood = ((int)(MAX_COUNT_NEPRODAM*0.9D));
                maxNonFOOD = ((int)(MAX_COUNT_NEPRODAM*0.2D));
                maxCoutAlc=4;
                maxClothes = 2;
            }

            System.out.println(maxFood + " " + maxNonFOOD + " FOOD ?  NON COUNT");

            pallet = Rule_contollers_Main.main_controller.blockS999KPFIN.isSelected();

            if(pallet) {
                new SlideModalError().setMessage("Включен тотальный блок" + System.lineSeparator() + "" +
                    "товара из паллет" + System.lineSeparator() + "" +
                    "Отключение (В настройках)");
                }

            if(z.equals(COMAND_ALCOGOL)){addAlc();}
            if(z.equals(COMAND_SMOKE)){addSmoke();}
            if(z.equals(COMAND_VES_TMC)){addVesTMC();}
            if(z.equals(COMAND_NEPRODAM)){addNeprodam();}



        joinTable();  // Соединение всех списков

        ShowForTableView.updateTable();

    }

    public static void joinTable(){

        alllist.clear();
        alllist.addAll(list_ALCOGOL);
        alllist.addAll(list_sigarets);
        alllist.addAll(list_ves_tovar);
        alllist.addAll(list_neprodam);

        // TEST
        // TEST
        // TEST
        // TEST
        // TEST

        if(lastSuccefulSort.size()>1) {

            ArrayList<Ostatku> temp = new ArrayList<>();

            for (Ostatku z : AddPositionToTableAndList.lastSuccefulSort) {
                if (alllist.contains(z)) {
                    temp.add(z);
                    System.out.println(z.getPlu() + " , " + z.getName());
                }
            }
            System.out.println("Содержание таблицы");
            alllist.clear();
            AddPositionToTableAndList.lastSuccefulSort.clear();
            alllist.addAll(temp);
        }

        // TEST
        // TEST
        // TEST
        // TEST
        // TEST
    }

    public static void removerfromAlllist(Ostatku d ){

        if(alllist.contains(d)){alllist.remove(d);}
        if(list_sigarets.contains(d)){list_sigarets.remove(d);}
        if(list_ves_tovar.contains(d)){list_ves_tovar.remove(d);}
        if(list_ALCOGOL.contains(d)){list_ALCOGOL.remove(d);}
        if(list_neprodam.contains(d)){list_neprodam.remove(d);}
        joinTable();
        ShowForTableView.updateTable();
    }


    public static void addAlc(){

        list_ALCOGOL.clear();
        int df = 0;

        while (true){
            //Крутим поиск пока не наберем 10 позиций

            if(list_ALCOGOL.size()==10){return;}

            boolean x = current_search_alko();
            df++;

            if(df>15){
                new Modal_Error().set_erroe_messege("После 15 полных циклов не удалось добавить 10 позиций.Измените настройки выборки");
            return;
            }
        }

    }

    public static void addSmoke(){

        list_sigarets.clear();

        int count = 0;

        while (true){
            if(list_sigarets.size()==10){break;}

            current_search_sigarets();
            count++;

            if(count>10){new Modal_Error().set_erroe_messege("Не удалось собрать список сигарет. Попробуйте снизить " +
                    "приоритет");break;}
        }

    }

    public static void addVesTMC(){

        list_ves_tovar.clear();

        int count = 0;

        while (true){
            if(list_ves_tovar.size()==10){break;}

            current_search_vestovar();
            count++;

            if(count>10){new Modal_Error().set_erroe_messege("Не удалось собрать список Весовых товаров.Попробуйте снизить " +
                    "приоритет");break;}

        }
    }

    public static void addNeprodam(){

        list_neprodam.clear();

        StartNeprodamParse.startUnzip();

        new SetPriorityOstatkuList().start();        // Установка приоритетов для непродаваек
        int count = 0;

        int currentPriority = 1;

        while (true){
            if(list_neprodam.size()==MAX_COUNT_NEPRODAM){break;}

            current_searchNeprodam(currentPriority);


            if(count>15){break;}

            if(count==4){currentPriority++;}
            if(count==7){currentPriority++;}
            if(count==9){currentPriority++;}

            count++;
            System.out.println(count);
        }


    }

    public static boolean current_search_alko(){

        int maxVino = 5;
        int count_operation = 0;
        while (true){
            if(list_ALCOGOL.size()==10){break;}

            count_operation++;

            if(count_operation>1500){return false;}


            Ostatku temp = Main.classOstatku.get(Algorithm_sklad.get_random_index());

            if(!temp.controlChek.equals("EMP")&&block_Control_chek){continue;}      // Позиции без контрольного пересчета

            if(temp.qfinal<qfMin){continue;}
            if(Math.abs(temp.oddsCOUNT)>oddsMax){continue;}
            if(!temp.getYu2().equals("Алкоголь")){continue;}

            if(alreadyChek(temp)){continue;}                     // Добавить в каждое правило!
            if(alreadyHave(temp,list_ALCOGOL)){continue;}        // Добавить в каждое правило!
            if(alreadyHave(temp,alllist)){continue;}        // Добавить в каждое правило!


            if(temp.getYu3().contains("Вин")){
                if(maxVino>0){list_ALCOGOL.add(temp);maxVino--;continue;}
                if(maxVino<=0){continue;}
            }

            list_ALCOGOL.add(temp);
            System.out.println("Добавлена позиция " + temp);


        }


        return false;
    }

    public static boolean current_search_sigarets(){
        int count_operation = 0;

        while (list_sigarets.size()<=10){
            if(list_sigarets.size()==10){break;}
            count_operation++;

            if(count_operation>1500){return false;}


            Ostatku temp = Main.classOstatku.get(Algorithm_sklad.get_random_index());
            if(!temp.controlChek.equals("EMP")&&block_Control_chek){continue;}
            if(temp.qfinal<qfMin+5){continue;}
            if(Math.abs(temp.oddsCOUNT)>oddsMax){continue;}
            if(!temp.getYu2().contains("бачные изделия и аксессуары (Fo")){continue;}

            if(alreadyChek(temp)){continue;}                     // Добавить в каждое правило!
            if(alreadyHave(temp,list_sigarets)){continue;}        // Добавить в каждое правило!
            if(alreadyHave(temp,alllist)){continue;}        // Добавить в каждое правило!


            list_sigarets.add(temp);
            System.out.println("Добавлена позиция " + temp);


        }


        return false;
    }

    public static boolean current_search_vestovar(){

        int count_operation = 0;

        int count_konfets = 4;

        while (list_ves_tovar.size()<=10){

            if(list_ves_tovar.size()==10){break;}
            count_operation++;

            if(count_operation>1500){return false;}


            Ostatku temp = Main.classOstatku.get(Algorithm_sklad.get_random_index());
            if(!itsDouble(temp.qychetnoe)){continue;}
            if(!temp.controlChek.equals("EMP")&&block_Control_chek){continue;}
            if(temp.qfinal<qfMin){continue;}
            if(Math.abs(temp.oddsCOUNT)>0.1D){continue;}
            if(!temp.that_count.equals("КГ")){continue;}

            if(alreadyChek(temp)){continue;}                   // Добавить в каждое правило!
            if(alreadyHave(temp,list_ves_tovar)){continue;}  // Добавить в каждое правило!
            if(alreadyHave(temp,alllist)){continue;}        // Добавить в каждое правило!

            if(temp.getYu3().equals("Конфеты")){count_konfets--;}
            if(count_konfets<=0){continue;
            }
            list_ves_tovar.add(temp);
            System.out.println("Добавлена позиция " + temp);

        }

        return false;
    }

    public static boolean current_searchNeprodam(int priority){

        boolean searchAlco = Rule_contollers_Main.main_controller.blockAlcInNeprod.isSelected();

        if(searchAlco){
            new SlideModalError().setMessage("Запрещено добавление алкоголя" +
                    System.lineSeparator() + "Чтобы исправить, посетите :" +
                    System.lineSeparator() + "(Настройки - Переменные среды)");
        }

        if(priority==4){
            if(list_neprodam.size()>=MAX_COUNT_NEPRODAM){return false ;}
            Ostatku temp ;
            int ciqle = 0;
            while (ciqle<=500){
                ciqle++;
                temp = Main.classOstatku.get(Algorithm_sklad.get_random_index());

                if(temp.getYu2().equals("Алкоголь")&&searchAlco){continue;}

                if(alreadyChek(temp)){continue;}                 // Добавить в каждое правило!
                if(alreadyHave(temp,list_neprodam)){continue;}  // Добавить в каждое правило!
                if(alreadyHave(temp,alllist)){continue;}        // Добавить в каждое правило!


                if(temp.qfinal>=qfMin){
                    if(Math.abs(temp.oddsCOUNT)<=oddsMax){
                        if(new GroupLevelAcces().accetpedGroupUY2FOOD(temp.getYu2())){
                        list_neprodam.add(temp);
                        }
                    }
                }
                if(list_neprodam.size()>=MAX_COUNT_NEPRODAM){return false ;}
            }

            return false;
        }

        for(Ostatku temp : Main.classOstatku){


            if(!temp.isneprodam){continue;}

            if(alreadyChek(temp)){continue;}                 // Добавить в каждое правило!
            if(alreadyHave(temp,list_neprodam)){continue;}  // Добавить в каждое правило!
            if(alreadyHave(temp,alllist)){continue;}        // Добавить в каждое правило!

            boolean isFOOD = new GroupLevelAcces().accetpedGroupUY2FOOD(temp.getYu2());

            if(isFOOD){
                if(temp.getYu2().equals("Алкоголь")){
                    if(searchAlco){continue;}
                    if (temp.getQfinal()>=qfMin){
                        if(maxCoutAlc>0 && temp.priority_NPR==priority){
                            if(Math.abs(temp.getOddsCOUNT())<=oddsMax){
                                list_neprodam.add(temp);maxCoutAlc--;maxFood--;continue;
                            }
                        }
                        if(maxCoutAlc<=0){continue;}
                    }
                } // END ALC

                if (temp.getQfinal()>=qfMin){
                    if(maxFood>0 && temp.priority_NPR==priority){
                        if(Math.abs(temp.getOddsCOUNT())<=oddsMax){
                            if(maxFood>0){ list_neprodam.add(temp);maxFood--;}
                        }
                    }
                }
                continue;
            }


            boolean isNONFOOD = new GroupLevelAcces().acceptedGroupYU2NOONFOOD(temp.getYu2());

            if (isNONFOOD){
                if (temp.getQfinal()>=qfMin){
                    if(maxNonFOOD>0 && temp.priority_NPR==priority){
                        if(Math.abs(temp.getOddsCOUNT())<=oddsMax){
                            if(maxNonFOOD>0){
                                if(temp.getYu2().equals("Одежда") && maxClothes<0){continue;}
                                list_neprodam.add(temp);maxNonFOOD--;if(temp.getYu2().equals("Одежда")){maxClothes--;}
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean alreadyChek(Ostatku temp){

        if(!checkBoxSRule(temp)){return true;}

        if(!have_actSklad_in_memory){
            chekmemorySklad();
            System.out.println(" Размер списка склада " + Container_KP_Sklad.list_kp_sklad.size());
        }
        if(BannedPlU.containsBanned(temp.plu)){return true;}
        if (!IgnoredPlu.chekFromWRS(temp.getPlu())){return true;}

        for(String plu : Container_KP_Sklad.list_kp_sklad){
            if(temp.getPlu().equals(plu)){return true;}
        }

        for(KarantinPLU kara : ControlClassKarantin.listKarantinPlu){
            if(temp.getPlu().equals(kara.getPlu())){return true;}
        }

        if(temp.getArea_one_strok().contains("999")){
            if(AddPositionToTableAndList.pallet){return true;}
        }
        if(MegaBlockYU2.chekPositionToMainRulesYU2(temp)){return true;}

        return false;
    }

    public static boolean alreadyHave(Ostatku temp, List<Ostatku> currentList){

        if (!IgnoredPlu.chekFromWRS(temp.getPlu())){return true;}

        for(Ostatku plu : currentList){
            if(temp.getPlu().equals(plu.getPlu())){return true;}
        }
        return false;
    }

    public static boolean itsDouble(double z){

        int temp = (int) z;

        if(temp!=z){return true;}
        return false;

    }

    public static void clearAllList(){

        alllist.clear();
        list_ALCOGOL.clear();
        list_neprodam.clear();
        list_ves_tovar.clear();
        list_sigarets.clear();
        clearTable();


    }

    public static void chekmemorySklad(){
        if(Container_KP_Sklad.list_kp_sklad.isEmpty()){
            new Container_KP_Sklad().loading();
             if(Container_KP_Sklad.list_kp_sklad.isEmpty()){
                 have_actSklad_in_memory = true;
                 new Modal_Error().set_erroe_messege("Будьте осторожны, не удалось подгрузить последний список "+ System.lineSeparator()+"" +
                         "Удачно созданного КП_Склад");
             }
        }
    }

    private static boolean checkBoxSRule(Ostatku temp){
        // Проверка входящего параметра на кол-во зон
        // Негативный сценарий :
        // Включен чек бокс на игнор зон S(склад)
        // Входящая PLU содержит либо S###? или S###? + S9999

        if(CheckBoxIngroreSSSS.getS999Status()){
            if(temp.getArea_one_strok().contains("S9999")){return false;}
        }


        if(CheckBoxIngroreSSSS.isCurrentBox()) {
            if(temp.getArea_one_strok().contains("S") && temp.getArea_one_strok().length()<5){return false;}
            for (String sone : temp.gates){
                if(!sone.startsWith("S")){return true;}
            }
            return false;
        }
        return true;
    }




}
