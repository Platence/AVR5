package parser_xml.not_standart_parser.unziper;

import decriptor.ConsoleAVR;

public class UnziperNonStandart {

    public static String target_xlsx_Path = "";                    // 1 Цель для распаковки ( передать адрес)
    public static String res_Path = "";                           // 2 итог


    public static String name_file_Sheet_1 = "xl/worksheets/sheet1.xml";
    public static String name_file_Shared_String = "xl/sharedStrings.xml";

    public static String final_path_shared_String = "";
    public static String final_path_sheet1 = "";


    public static String name_method_1 = "SPR";
    public static String name_method_2 = "NPR";



    public static void start(){

        Process_UnZip.beginn(target_xlsx_Path,res_Path);

    }

    public static void setAddresPathAndStart(String a, String b, String current_method){


        if(current_method.equals(name_method_1)) {
             // Обработка исключительно для списка разниц
            target_xlsx_Path = a;
            res_Path = b;
            start();

            ConsoleAVR.printlnn("Распаковка прошла успешно ");

         /*
               подаем на вход цель, и конечную папку.
         */
        }
    }
}
