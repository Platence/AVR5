package interfaces_all;

import warehouse_plu.Ostatku;

public interface Settings_control_add_KPPP {

    boolean error_area(Ostatku d);
    //Проверка на нежелательные зоны.
    //У каждого акта своя логика



    boolean logic_to_add(Ostatku d);

    boolean chekqf(Ostatku d);

    boolean chekinout(Ostatku d);

    boolean ignore_pulse(Ostatku d);

}
