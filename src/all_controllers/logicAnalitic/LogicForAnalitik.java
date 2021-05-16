package all_controllers.logicAnalitic;

import groupLevel.GroupLevelAcces;
import sample.Main;
import warehouse_plu.Ostatku;
import warehouse_plu.odds_from_group.Odds_Level_two;
import warehouse_plu.odds_from_group.Odds_level_One;

import java.util.ArrayList;
import java.util.Map;

public class LogicForAnalitik {


    public static boolean need_start=false;                // Планируется отказ выполнения при покидании вкладки

    /*
    Будем возвращать отсюда элементы
    УИ2 или УИ3 по запросу
     */

    public static void start_Analitics() throws Exception{

        Bundle_For_WRS.set_null_for_all_field_WRS();        // Обнуление всех полей для WRS

        Odds_level_One.clear_map();                         // Обнуление всех полей листа

              /*
                    Сложная структура добавления Связка ИУ2 сумма -> Лист значений УИ3(От УИ2) + сумма;
               */

        for(Ostatku target : Main.classOstatku){

            Bundle_For_WRS.start_lit(target);           // Дополнительено просчет

            int summ = (int) target.oddsSUM;            // Временная переменная суммы ( одна для всех)

            if(GroupLevelAcces.containNegativeGroup(target.getYu2())){continue;}

            if(Odds_level_One.all_list_map.isEmpty()){
            // На первом проходе добавим одну позицию.
               ArrayList<Odds_Level_two> list2_first = new ArrayList<>();
               list2_first.add(new Odds_Level_two(target.getYu3(),summ));
               Odds_level_One.all_list_map.put(new Odds_level_One(target.getYu2(),summ),list2_first);
               continue;
            }

            // Далее поиск повторов, и обновления в случае таковых
            boolean add_yu2 = true;
            for(Map.Entry<Odds_level_One,ArrayList<Odds_Level_two>> entry : Odds_level_One.all_list_map.entrySet()){

                    String s__level_1 = entry.getKey().getName();    // Получим УИ2

                    if(target.getYu2().equals(s__level_1)){

                        // Если такой УИ2 уже есть, просто обновим у него сумму

                        int temp_sum_yu2 = entry.getKey().getSum();
                        entry.getKey().setSum(temp_sum_yu2+=summ);

                        // А так же проверим его YU3
                        // И обновим сумму если нужно
                        // add_yu3 для проверки на присутсвие уи3
                        boolean add_yu3 = true;

                        for(Odds_Level_two level_2 : entry.getValue()){

                            String s_level_2 = level_2.getName();

                            if(s_level_2.equals(target.getYu3())){
                                int temp_sum_level_2 = level_2.getSum();
                                level_2.setSum(temp_sum_level_2+=summ);
                                add_yu3 = false;break;
                            }

                        }  //Конец For на присутсвие УИ3

                        if(add_yu3){
                            int z = 0;                          //Проверка на пустой лист, чтобы не записать
                                                                // Значение дважды!
                            if(entry.getValue().isEmpty()) {
                                ArrayList<Odds_Level_two> temp = new ArrayList<>();
                                temp.add(new Odds_Level_two(target.getYu3(), summ));
                                entry.setValue(temp);
                                z++;
                            }

                            if(z==0){
                                entry.getValue().add(new Odds_Level_two(target.getYu3(),summ));
                            }

                        } // Конец IF если совпадений не было

                       add_yu2 = false; break;
                    }  // Конец IF на присутсвие УИ2


            }  // Конец проверки в цикле for

                if(add_yu2){
                    ArrayList<Odds_Level_two> ttt_list = new ArrayList<>();
                    ttt_list.add(new Odds_Level_two(target.getYu3(),summ));
                    Odds_level_One.all_list_map.put(new Odds_level_One(target.getYu2(),summ),ttt_list);
                }

        }  // Конец прохода списка MAIN

    }




    public void start_option(){

        if (need_start) {
            setFalse();
        } else {
            setTrue();
        }
    }

    public void setFalse(){
        need_start = false;
    }

    public void setTrue(){
        need_start = true;
    }

}
