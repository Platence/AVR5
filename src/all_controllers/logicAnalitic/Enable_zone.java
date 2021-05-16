package all_controllers.logicAnalitic;

import java.util.ArrayList;

public class Enable_zone {

    public static ArrayList<String> e_zone = new ArrayList<>();

    /*
       Запрещенные зоны невыставленного товара
     */

    static {
        e_zone.add("Авто аксессуары");
        e_zone.add("Бытовая техника");
        e_zone.add("Галантерея");
        e_zone.add("Медиа");
        e_zone.add("Детские товары (NonFood)");
        e_zone.add("Канцелярские товары");
        e_zone.add("Обувь");
        e_zone.add("Одежда");
        e_zone.add("Отдых");
        e_zone.add("Сад и Огород");
        e_zone.add("Сопутствующие товары (NonFood)");
        e_zone.add("Спорт");
        e_zone.add("Тара возвратная");
        e_zone.add("Текстиль");
        e_zone.add("Товары для дома");
        e_zone.add("Товары для животных");
        e_zone.add("Товары для праздника");
        e_zone.add("Товары для ремонта");
        e_zone.add("Товары спец.акций_2");
        e_zone.add("Хлеб СП");
        e_zone.add("Хлеб, хлебобулочные изделия (Fresh)");
        e_zone.add("Хлеб, хлебобулочные изделия (Food)");

    }

    public static boolean permissible_group(String yu2_g){
        // Возвращает TRUE если группы нет в списке

        for(String s : e_zone){
            if(yu2_g.equals(s)){return false;}
        }

        return true;

    }
}
