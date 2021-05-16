package groupLevel;

import java.util.ArrayList;

public class GroupLevelAcces {

    /**
     * Устанавливает разрешения для групп УИ3 или УИ 2
     * Разрешения для групп
     * Посмотреть какие группы разрешены
     */

    public static ArrayList<String> YU2 = new ArrayList<>();
    public static ArrayList<String> YU2NF = new ArrayList<>();
    public static ArrayList<String> NEGATIVEYU2 = new ArrayList<>();

    static {
        YU2.add("Алкоголь");
        YU2.add("Бакалея (Food)");
        YU2.add("Готовая кулинария, салаты");
        YU2.add("Детские товары (Food)");
        YU2.add("Детское питание (Food)");
        YU2.add("Замороженные продукты");
        YU2.add("Кондитерские изделия (Food)");
        YU2.add("Молочная гастрономия");
        YU2.add("Мясная гастрономия");
        YU2.add("Мясо и мясные изделия");
        YU2.add("Овощи - Фрукты");
        YU2.add("Рыбная гастрономия");
        YU2.add("Соки, воды, пиво");

        YU2NF.add("Сопутствующие товары (Food)");
        YU2NF.add("Бытовая техника");
        YU2NF.add("Корма для животных");
        YU2NF.add("Медиа");
        YU2NF.add("Товары для животных");
        YU2NF.add("Текстиль");
        YU2NF.add("Одежда");
        YU2NF.add("Обувь");


        NEGATIVEYU2.add("Авто аксессуары");
        NEGATIVEYU2.add("Галантерея");
        NEGATIVEYU2.add("Готовая кулинария, салаты");
        NEGATIVEYU2.add("Дисконтные карты, билеты");
        NEGATIVEYU2.add("Канцелярские товары");
        NEGATIVEYU2.add("Тара возвратная");
        NEGATIVEYU2.add("Хлеб, хлебобулочные изделия (Fresh)");


    }


    public boolean accetpedGroupUY2FOOD(String target){
        return YU2.contains(target);
    }

    public boolean acceptedGroupYU2NOONFOOD(String target){
        return YU2NF.contains(target);
    }

    public static boolean containNegativeGroup(String yu2){

        /*
        If Word contains looks like negative list, return true, and not add to accordeon
         */

        if(NEGATIVEYU2.contains(yu2)){
            return true;
        }

        return false;

    }


}

