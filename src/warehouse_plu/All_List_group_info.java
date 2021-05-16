package warehouse_plu;

import sample.Main;

import java.util.ArrayList;
import java.util.TreeSet;

public class All_List_group_info {

    public static TreeSet<String> list_gr_for_on_line = new TreeSet<>();
    public static ArrayList<String> list_AR_for_on_line = new ArrayList<>();
    public static ArrayList<Ostatku> thirst_step_KP_Sklad = new ArrayList<>();
    public static ArrayList<String> acceptGroupYU2 = new ArrayList<>();



    static {
        acceptGroupYU2.add("Алкоголь");
        acceptGroupYU2.add("Бакалея (Food)");
        acceptGroupYU2.add("Детские товары (Food)");
        acceptGroupYU2.add("Детские товары (NonFood)");
        acceptGroupYU2.add("Детское питание (Food)");
        acceptGroupYU2.add("Замороженные продукты");
        acceptGroupYU2.add("Кондитерские изделия (Food)");
        acceptGroupYU2.add("Молочная гастрономия");
        acceptGroupYU2.add("Мясная гастрономия");
        acceptGroupYU2.add("Овощи - Фрукты");
        acceptGroupYU2.add("Рыбная гастрономия");
        acceptGroupYU2.add("Соки, воды, пиво");
        acceptGroupYU2.add("Сопутствующие товары (Food)");
    }








    public static void add(String s){
        //Просто добавляет группу
        list_gr_for_on_line.add(s);
    }

    public static ArrayList<String> get_back_list_for_on_line(){
        ArrayList<String> temp = new ArrayList<>();
        for(String ss : list_gr_for_on_line){
            temp.add(ss);
        }
        return temp;
    }

    public static void ok_start(){
        list_AR_for_on_line = get_back_list_for_on_line();
    }

    public static ArrayList<Ostatku> get_me_group(String name){

        //Возвращает только группу по имени
        ArrayList<Ostatku> temped = new ArrayList<>();

        for(Ostatku extra : Main.classOstatku){
            if(extra.qfinal>0)
            {
                if (extra.getYu3().equals(name))
                {
                    temped.add(extra);
                }

            }

        }
        return temped;
    }

    public static void add_object_to_list_sklad(Ostatku t){

        for(Ostatku ggg : thirst_step_KP_Sklad){
            if(ggg.plu.equals(t.plu)){
                //System_tray_user.set_new_message("Такая PLU уже добавлена!");
                return;}
        }


        thirst_step_KP_Sklad.add(t);
    }

    public static void remove_off_list_sklad(Ostatku t){
        thirst_step_KP_Sklad.remove(t);
    }

    public static String get_all_count(){
        int x = thirst_step_KP_Sklad.size();
        return String.valueOf(x);
    }

    public static TreeSet<String> ffff(){
        TreeSet<String> temped = new TreeSet<>();

        for(Ostatku key : thirst_step_KP_Sklad){
            temped.add(key.plu);
        }

        return temped;
    }

    public static boolean acceptYU2Group(String reject){

        for(String zzz : acceptGroupYU2){
            if(reject.equals(zzz)){return true;}
        }

        return false;
    }

}
