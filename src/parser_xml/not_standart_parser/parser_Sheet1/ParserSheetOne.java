package parser_xml.not_standart_parser.parser_Sheet1;


import decriptor.ConsoleAVR;
import parser_xml.not_standart_parser.parser_Shared_String.AdOFromAnotherParse;
import parser_xml.not_standart_parser.parser_Shared_String.Fields_Shared_String;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserSheetOne {

    private String path_sheet_1;

    public static String START_FIELD = "r=\"A14\"";           // Эту переменную вынести в настройки
    public static int COUNT_ITERATION = 13;                   // Кол-во итераций на строку. Тоже в настройки

    private static StringBuilder sb = new StringBuilder();
    private static StringBuilder temp1LEVEL = new StringBuilder();



    public ParserSheetOne(String path_sheet_1) {
        this.path_sheet_1 = path_sheet_1;
    }

    public String getPath_sheet_1() {
        return path_sheet_1;
    }

    public void setPath_sheet_1(String path_sheet_1) {
        this.path_sheet_1 = path_sheet_1;
    }

    public void startPSH1() {


        FileReader fr = null;
        try {
            fr = new FileReader(this.getPath_sheet_1());
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()){

                // Поместим полученный результат в StringBuilder;

                sb.append(scan.nextLine());
                parseFirstLevel(sb.toString());
                sb.setLength(0);
            }

            fr.close();


        }
        catch (Exception e){
            ConsoleAVR.printlnn("Ошибка в считывании главного файла");
        }

    }


    public static void parseFirstLevel(String x){

        Pattern p1 = Pattern.compile("<c (.*?)</c>",Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m1 = p1.matcher(x);
        temp1LEVEL.setLength(0);

        boolean start_iter = false;  // Когда поймаем А14 начнем парсинг
        int current_column = 0;

        LinkedList<String> tempList = new LinkedList<>();   // Переменный лист одной строки!

        while (m1.find()){


            if(current_column == COUNT_ITERATION){
                // Когда набирается 13 или сколько нужно строк,
                // Вызываем конструктор
                AdOFromAnotherParse.addNewObjectToList(tempList);
                tempList.clear();
                current_column = 0;
            }

            temp1LEVEL.append(m1.group(1));
            // Получим строку типа
            // r="A1" s="4" t="s"><v>0</v>

            if(!start_iter){start_iter = chek_start_position(temp1LEVEL.toString());}   // Стартуем если А14 (START_FIELD)

            if(start_iter){
                //temp = parseSecondLevel(temp);
                tempList.add(parseSecondLevel(temp1LEVEL.toString()));
                current_column++;
            }

        }

    }


    public static String parseSecondLevel(String s2){

          /*
           Если будет набор символов t="s"
           То ищем строку по индексу
           иначе считываем всё!
         */

          String test = "t=\"s\"";

          if(s2.contains(test)){
              return parseLevelValueWithV(s2);
           }


          return valueWithOutV(s2);


    }


    public static String parseLevelValueWithV(String x){


        Pattern p3 = Pattern.compile("<v>(.*?)</v>");
        Matcher matcher = p3.matcher(x);

        String temp = "";
        int ind = 0;

        while (matcher.find()){
            temp = matcher.group(1);
            try {ind = Integer.parseInt(temp);}catch (Exception e){
                System.out.println("Не удалось найти индекс");
            }
        }

        // Возвращем индекс (Строку из индекса)
        for(Fields_Shared_String target : Fields_Shared_String.all_string_sh){
            if(target.getIndex_S()==ind){return target.getNameS();}
        }
        return "NON_FIELD";
    }

    public static String valueWithOutV(String x){


        Pattern p3 = Pattern.compile("<v>(.*?)</v>");
        Matcher matcher = p3.matcher(x);

        String temp = "";


        while (matcher.find()){
            temp = matcher.group(1);
        }


        return temp;
    }



    public static boolean chek_start_position(String s14){



        if(s14.contains("=\"A14\"")){
            return true;
        }

        return false;
    }






}
