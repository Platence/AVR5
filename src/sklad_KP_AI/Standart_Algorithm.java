package sklad_KP_AI;

import warehouse_plu.Ostatku;

import java.util.Map;
import java.util.TreeMap;

public class Standart_Algorithm {


    public static TreeMap<String,Integer> standart_map = new TreeMap<>();




    public static boolean chek_new_position(Ostatku temp){

        if(temp.qfinal<Algorithm_sklad.min_QF){return false;}
        if(temp.oddsCOUNT>0){return false;}

        for(Map.Entry<String,Integer> entry : standart_map.entrySet()){
            // Ищем группу в списке
            // Если находим, спрашиваем есть ли кол-во
            if(entry.getKey().equals(temp.getYu3())){

                if(entry.getValue()>0){
                    int tempInt = entry.getValue()-1;
                    entry.setValue(tempInt);
                    return true;
                }
            }
        }



        return false;
    }

    public static void ini_standart_map(){

        standart_map.clear();
        standart_map.put("Водка",5);
        standart_map.put("Шоколад, батончики",4);
        standart_map.put("Масло растительное",3);
        standart_map.put("Коньяк и коньячные напитки",2);
        standart_map.put("Кофе, какао, шоколад",5);
        standart_map.put("Консервы мясные",2);
        standart_map.put("Пиво",3);
        standart_map.put("Конфеты",3);
        standart_map.put("Крупы и зерновые",2);
        standart_map.put("Ликёро-водочне",2);
        standart_map.put("Вермуты",2);
        standart_map.put("Вино игристое",3);
        standart_map.put("Чай",5);
        standart_map.put("Орехи",2);
        standart_map.put("Заменитель материнского молока",3);
        standart_map.put("Замороженные полуфабрикаты",3);
        standart_map.put("Рыба замороженная",3);

        standart_map.put("Средства личной гигиены",2);
        standart_map.put("Средства для волос",2);
        standart_map.put("Средства для ухода за бельем и одеж",2);
        standart_map.put("Детские сухие смеси",1);
        standart_map.put("Детские средства гигиены",2);

        standart_map.put("Колбасные изделия",1);
        standart_map.put("Икра",1);
        standart_map.put("Пресервы",2);
        standart_map.put("Рыба вяленая, сушеная",1);
        standart_map.put("Соки, нектары",3);



    }

}
