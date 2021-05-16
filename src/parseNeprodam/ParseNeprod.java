package parseNeprodam;

import parser_xml.not_standart_parser.parser_Shared_String.AdOFromAnotherParse;
import parser_xml.not_standart_parser.parser_Shared_String.Fields_Shared_String;
import sample.Main;
import warehouse_plu.Ostatku;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseNeprod {

    public static int countUpdateTMC = 0;

    public void startParseNeprod(){
        new ParseNeprod().startPSH1(StartNeprodamParse.FINAL_PATH_NEPRODAM_SHEET1);
    }

    public void startPSH1(String path) {

        NeprodamField.listNPS.clear(); // Обнуление предыдущего списка
        nullIsNeprod();                // Обнуление предыдущего слияния

        // Чтение файла

        FileReader fr = null;
        try {
            fr = new FileReader(path);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()){

                // Поместим полученный результат в StringBuilder;

                StringBuilder sb = new StringBuilder();
                sb.append(scan.nextLine());
                parseFirstLevel(sb.toString());
                sb.setLength(0);
            }

            fr.close();

        }
        catch (Exception e){
            System.out.println("Ошибка в считывании главного файла");
        }

        addInformationToOstatku();    // ПЕРЕДАЧА В ГЛАВНЫЙ СПИСОК полученного результата

    }

    public static void parseFirstLevel(String x){

        Pattern p1 = Pattern.compile("<c (.*?)</c>",Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m1 = p1.matcher(x);
        String temp = "";

        boolean start_iter = false;  // Когда поймаем А14 начнем парсинг
        int current_column = 0;

        LinkedList<String> tempList = new LinkedList<>();   // Переменный лист одной строки!

        while (m1.find()){


            if(current_column == StartNeprodamParse.COUNT_ITERATION){
                // Когда набирается 13 или сколько нужно строк,
                // Вызываем конструктор
                NeprodamField.tryAddnewPosition(tempList);
                tempList.clear();
                current_column = 0;
            }

            temp = m1.group(1);
            // Получим строку типа
            // r="A1" s="4" t="s"><v>0</v>

                temp = parseSecondLevel(temp);
                if(temp.isEmpty()){temp="EMP";}
                tempList.add(temp);
                current_column++;


        }


    }

    public static String parseSecondLevel(String s2){

          /*
           Если будет набор символов "t=\"inlineStr\"";
           То ищем строку <t>
            иначе строку <v>
         */

        String test = "t=\"inlineStr\"";

        if(s2.contains(test)){
            return parseLevelValueWithV(s2);
        }

        return valueWithOutV(s2);
    }

    public static String parseLevelValueWithV(String x){

        // Замена v на t и наоборот в нижнем методе....

        Pattern p3 = Pattern.compile("<t>(.*?)</t>");
        Matcher matcher = p3.matcher(x);

        String temp = "";
        int ind = 0;

        while (matcher.find()){
            temp = matcher.group(1);

        }


        return temp;
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

    public static void addInformationToOstatku(){
        countUpdateTMC = 0;

        for (Ostatku temp : Main.classOstatku){
              for(NeprodamField field : NeprodamField.listNPS){
                  if(temp.getPlu().equals(field.getPlu())){
                      temp.lastDateImport = field.getLastdateImport();
                      temp.lastDateSale = field.getLastDateSale();
                      temp.isneprodam = true; countUpdateTMC++;
                      break;
                  }
              }
        }
        NeprodamField.listNPS.clear();
    }

    public static void nullIsNeprod(){

        for(Ostatku temp : Main.classOstatku){
            if(temp.isneprodam){
                temp.isneprodam = false;
                temp.lastDateSale = "EMP";
                temp.lastDateImport = "EMP";
            }
        }
    }
}
