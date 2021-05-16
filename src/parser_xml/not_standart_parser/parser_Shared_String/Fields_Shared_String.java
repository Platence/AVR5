package parser_xml.not_standart_parser.parser_Shared_String;

import java.util.ArrayList;
import java.util.Objects;

public class Fields_Shared_String {

    /*
       Класс хранящий в себе строка - индекс объекта  Fields_Shared_String
     */




    public static ArrayList<Fields_Shared_String> all_string_sh = new ArrayList<>();

    private String nameS;
    private int index_S;


    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public void setIndex_S(int index_S) {
        this.index_S = index_S;
    }

    public Fields_Shared_String(String nameS, int index_S) {
        this.nameS = nameS;
        this.index_S = index_S;
    }

    public String getNameS() {
        return nameS;
    }

    public int getIndex_S() {
        return index_S;
    }

    public static void clear_map(){

        all_string_sh.clear();
    }

    public static void show_me_all_field_and_index(){

        for(Fields_Shared_String asd : all_string_sh){
            System.out.println(asd.getNameS() + " index " + asd.getIndex_S());
        }
    }

    public static int add_new_position(String name, int ind, int current_index){


        // Пока не получим хотя бы одно слово, пустой символ

        // не записываем

        for(Fields_Shared_String sss : all_string_sh){

            if(name.equals(sss.getNameS())){
                return current_index;
            }
        }

        all_string_sh.add(new Fields_Shared_String(name,ind));
        current_index++;
        return current_index;
    }


    @Override
    public boolean equals(Object o) {
        if(this.nameS.equals(o)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameS);
    }
}
