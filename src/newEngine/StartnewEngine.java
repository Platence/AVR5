package newEngine;

import error_package.Modal_Error;
import newEngine.StringClass.CurrentLinkedString;
import parser_xml.not_standart_parser.unziper.Process_UnZip;
import parser_xml.not_standart_parser.unziper.UnziperNonStandart;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class StartnewEngine {

    public String pathUnzipSharedString = "";
    public String pathexcelFile = "" ;          // архив который нужно распаковать
    public String pathsheetUnZipFile = "" ;
    public String destanationallFiles = "";     // папка в которую нужно всё закинуть

    public String testPath = "";



    // C:\Stress_Test\fghfgh\Сверка разниц 8200 03022020.xlsx
    // C:\test_unzip\
    //


    public StartnewEngine(String pathexcelFile, String destanationallFiles) {


        /**
         *  ВАЖНО ПРИ ГЕНЕРАЦИИ УЧИТЫВАТЬ КАКИЕ СТОЛБЦЫ ЧИТАЮТСЯ
         *  А КАКИЕ НЕТ
         *  СМОТРЕТЬ КЛАСС ListTemps -> forcompile
         */

        this.pathexcelFile = pathexcelFile;
        this.pathsheetUnZipFile = destanationallFiles;

        try
        {
            Process_UnZip.beginn(pathexcelFile, destanationallFiles);
            //Анархив
        }
        catch (Exception e){ System.out.println(e);}

        try
        {


            ParsingXML parse1 = new ParsingXML();
            parse1.prepeareSharedString(UnziperNonStandart.final_path_shared_String);

            ParsingXML parse2 = new ParsingXML();
            parse2.prepeare(UnziperNonStandart.final_path_sheet1);

            parse1 = null;
            parse2 = null;

            System.out.println(UnziperNonStandart.final_path_sheet1);
            System.out.println(UnziperNonStandart.final_path_shared_String);
            //Парсинг

            System.gc();
        }

        catch (Exception e){ System.out.println(e); }



    }





}
