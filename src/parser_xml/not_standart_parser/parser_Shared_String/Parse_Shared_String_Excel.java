package parser_xml.not_standart_parser.parser_Shared_String;

import all_paths.Paths_Main_File;
import newEngine.ResultsOSTATKU.ListStrings;
import newEngine.StartnewEngine;
import newEngine.StringClass.CurrentLinkedString;
import parseNeprodam.StartNeprodamParse;

import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse_Shared_String_Excel {


    // Для использования этого класса создать объект new ( путь к SharedString )




    public static boolean have_Shared_string = false;  // Будет служить проверкой на строки

    private String path_Shared_String;                // Поместим сюда путь


    public Parse_Shared_String_Excel(String path_Shared_String) {
        this.path_Shared_String = path_Shared_String;
    }

    public static boolean isHave_Shared_string() {
        return have_Shared_string;
    }

    public static void setHave_Shared_string(boolean have_Shared_string) {
        Parse_Shared_String_Excel.have_Shared_string = have_Shared_string;
    }

    public String getPath_Shared_String() {
        return path_Shared_String;
    }

    public void setPath_Shared_String(String path_Shared_String) {
        this.path_Shared_String = path_Shared_String;
    }





    /*
         непосредственно проверка на присутствие строк
     */



    public boolean chek_Shared_strings_from_Content(){

        have_Shared_string = false;       // Обнулим поле
        Fields_Shared_String.clear_map();
                                          // Возьмём путь из созданного объекта
        try(FileReader fileReader = new FileReader(this.getPath_Shared_String()))
        {
            int count = 0;  // Счетчик символов, (>200) значит есть строки)
            int c;

            while((c=fileReader.read())!=-1){
                count++;
                if(count>200){have_Shared_string = true;return true;}

            } // end while

        } // end try



        catch (Exception e){
            System.out.println("Ошибка при проходе файла SharedStrings");
        }

        if(have_Shared_string){

            start_Another_parse();


            // Если внутри что-то есть, загоняем в парсер
            // Старт Альтернативного подсчета
            // Обнулим лист

        } // end if
        return false;
    }   // end method




    public void start_Another_parse(){

        System.out.println("Начата процедура проверки на присутствие SharedString");

        FileReader fr = null;
        try {
            fr = new FileReader(this.getPath_Shared_String());
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()){

                // Поместим полученный результат в StringBuilder;
                StringBuilder sb = new StringBuilder();
                sb.append(scan.nextLine());

                regular_for_shared_String(sb);
                sb.setLength(0);
            }

            fr.close();
            System.out.println("Закончена");

        }

        catch (Exception e){ System.out.println("[2]Ошибка при проходе файла SharedStrings"); }

    }  // End Method

    public static void regular_for_shared_String(StringBuilder sb){

        // Разбор всей строки на комментарии

        /*
        <si>
         <t>Наименование</t>
         </si>

         */

        Pattern p = Pattern.compile("<si>(.*?)</si>",Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = p.matcher(sb.toString());

        int indexSs = 0;   // Важная переменная что хранит № индекса

        boolean con_word = false;

        while (m.find()){

            String temp = m.group(1);
            temp = second_level_parse(temp);


            if(temp.length()>0){
                // Если строка не пустая, добавим объект и индекс
                // Добавление объекта Fields_String
                indexSs = Fields_Shared_String.add_new_position(temp,indexSs,indexSs);
                // Вернем указатель индекса 3 аргументом
            }

        }
    }


    public static String second_level_parse(String x ){

        String temppp = "";

        if(x.contains("<t/>")){return "EMP";}

        // Удалим символы <> из строки

        Pattern p2 = Pattern.compile("<t>(.*?)</t>");
        Matcher m2 = p2.matcher(x);

        while (m2.find()){
            temppp = m2.group(1);
        }

        return temppp;
    }



}
