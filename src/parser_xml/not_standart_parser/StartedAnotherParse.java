package parser_xml.not_standart_parser;

import parser_xml.not_standart_parser.parser_Shared_String.AdOFromAnotherParse;
import parser_xml.not_standart_parser.parser_Shared_String.Parse_Shared_String_Excel;
import parser_xml.not_standart_parser.parser_Sheet1.ParserSheetOne;
import parser_xml.not_standart_parser.unziper.UnziperNonStandart;
import sample.Main;

public class StartedAnotherParse {


    public static boolean is_another_ = false;


    public static boolean go_go_go(String path,String destenation,String s){

        // s = что за тип данных читаем
        // path = исходник
        // destenation = куда будем ложить разархив

        is_another_ = false;
        AdOFromAnotherParse.size_list = 0;

        {
            UnziperNonStandart.setAddresPathAndStart(path, destenation,s);
            // Как вызывать метод

            // Как вызвать проверку строк
            Parse_Shared_String_Excel psse = new Parse_Shared_String_Excel(UnziperNonStandart.final_path_shared_String);
            is_another_ = psse.chek_Shared_strings_from_Content();
            psse = null;
            if(is_another_){return true;}
            //Как вызвать сам парсер
            ParserSheetOne parserSheetOne = new ParserSheetOne(UnziperNonStandart.final_path_sheet1);
            parserSheetOne.startPSH1();

            parserSheetOne = null;

        }

        if(Main.classOstatku.size()<10){
                return false;
        }
        return true;

    }
}
