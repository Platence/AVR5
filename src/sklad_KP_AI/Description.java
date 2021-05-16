package sklad_KP_AI;

import error_package.SlideError.SlideModalError;

public class Description {

    public static String desc_standart;

    public static String desc_random;

    public static String hand_onLine;

    public static String half_auto;



    static {
        desc_standart = "Централизованный отбор" + System.lineSeparator() +
                "Алкоголь, сопутка, детское питание" + System.lineSeparator() +
                "Шоколад,кофе,чай с небольшими ограничениями." + System.lineSeparator() +
                "Сортирован по УИ3" + System.lineSeparator() +
                "Исключены соусы,специи" + System.lineSeparator();

        desc_random =
                "Случайный набор любых PLU кроме СП";


        half_auto =
                "Рекомендуемый параметр. Будет открыт Лобби склад." + System.lineSeparator() +
                "Укажите сколько PLU из каждой группы вам нужно." + System.lineSeparator() +
                "Укажите минимальный Qфин для каждой УИ3 группы." + System.lineSeparator() +
                "Неназначенные УИ3 к проверке не допускаются." + System.lineSeparator();

    }

    public static void showSliderInfo(String group){
        String message = "";
        if(group.equals("Стандарт")){message = desc_standart;}
        if(group.equals("Случайный")){message = desc_random;}
        if(group.equals("Расширенный")){message = half_auto;}

        new SlideModalError().setMessage(message);
    }
}
