package exportXLSX;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.finKP.ExportKPPFIN;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.kpSkladXlsX.KpppSKLADExport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public abstract class ExportExcel {


    public static String COMAND_TO_EXPORT_INV_15 = "INV15";
    public static String COMAND_TO_EXPORT_KP_SKLAD = "акт_кпС";
    public static String COMAND_TO_EXPORT_KP_SKLADMOL = "акт_кпСМОЛ";

    public static String COMAND_TO_EXPORT_KP_FIN = "акт_КПФ";
    public static String COMAND_TO_EXPORT_KP_FINSMENA = "акт_КПФСМ";

    public static String COMAND_TO_EXPORT_KP_FIN_EXTENDED = "KPEXT";
    public static String COMAND_TO_EXPORT_NULL_OSTATKU = "НУЛЕВЫЕ";
    public static String COMAND_TO_EXPOR_SVOD_PU = "SVODPU";
    public static String COMAND_TO_EXPORT_ODDS_LIST = "РСХОЖД";
    public static String COMAND_TO_EXPORT_TG_ODDS = "РАЗПОГР";
    public static String COMAND_TO_EXPORT_APP_ORDER = "Приложение";
    public static String COMAND_TO_EXPORT_APP_ORDERSML = "ПриложениеСМ";

    /*
        Команда на выгрузку определенного файла
        Для создания нового файла
        Нужно указать путь к файлу в Path_MAIN
        указать имя команды
        указать имя листа xlsx
     */


    public void exportFile(String comand, LinkedList<Cell> listCell, String pathOut){

        /**
         * На вход подать команду (какой файл создаем)
         * Лист ячеек
         * Путь выгрузки который сложится в "путь + имя файла".
         */

        FileInputStream fis = null;
        XSSFWorkbook myFFFBook = null;

        try {
            fis = new FileInputStream(getMePathOfCommand(comand));
            myFFFBook = new XSSFWorkbook(fis);
        }

        catch (Exception e) {
            new Modal_Error().set_erroe_messege("Ошибка создания WORKBOOK " + e.getMessage());
            return;
        }

        XSSFSheet sheet_fff = myFFFBook.getSheet(getnameSheet(comand));
        StringBuilder sb = new StringBuilder();

        for(Cell temp : listCell){
            try {
                Cell cellTemp = sheet_fff.getRow(temp.getRowIndex()).getCell(temp.getColumnIndex());
                try{ cellTemp.setCellValue(temp.getStringCellValue());}
                catch (IllegalStateException e){
                    //e.printStackTrace();
                    cellTemp.setCellValue(temp.getNumericCellValue());
                }
            }
            catch (NullPointerException e){
                //new Modal_Error().set_erroe_messege("Ошибка сохранения, пустая ячейка " + temp.getRowIndex() + " " + temp.getColumnIndex());
                sb.append("HAVE ERRORS....");
            }



        }


        if(sb.length()>0){
            new Modal_Error().set_erroe_messege("Обнаружены ошибки при выгрузке документа.");
        }

        if(comand.equals(ExportExcel.COMAND_TO_EXPORT_KP_FIN)){

            // Extended KP FIN

            XSSFSheet sheet_fff2 = myFFFBook.getSheet(getnameSheet(ExportExcel.COMAND_TO_EXPORT_KP_FIN_EXTENDED));
            for(Cell temp : ExportKPPFIN.cellListExFin){
                Cell cellTemp = sheet_fff2.getRow(temp.getRowIndex()).getCell(temp.getColumnIndex());
                try{ cellTemp.setCellValue(temp.getStringCellValue());}
                catch (IllegalStateException e){cellTemp.setCellValue(temp.getNumericCellValue());}
            }


        }



        try { fis.close(); }
        catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка при попытке выгрузки  " + comand + " " +  e.getMessage());
        }

        try {
            FileOutputStream outFile = new FileOutputStream(new File(pathOut+"\\"+comand+".xlsx"));
            myFFFBook.write(outFile);
            outFile.close();

            try {
                File file=new File(pathOut+"\\"+comand+".xlsx");
                Desktop.getDesktop().open(file);
            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch (Exception e){
            System.out.println(e);
            new Modal_Error().set_erroe_messege(e.getMessage());
        }


        try {
            myFFFBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ExportInv15.listCell.clear();
        KpppSKLADExport.cellListKpSklad.clear();
        listCell.clear();
        System.gc();

    }

    public XSSFSheet genereteSheet(String comand){
        /**
         *  Возвращает генирируемую страницу
         */
        FileInputStream fis = null;
        XSSFWorkbook myFFFBook = null;

        String path = getMePathOfCommand(comand);

        try {
            fis = new FileInputStream(path);
            // возвращает путь в соответсвии
            // с командой
            myFFFBook = new XSSFWorkbook(fis);
        }

        catch (Exception e) {
            new Modal_Error().set_erroe_messege("Ошибка создания WORKBOOK " + e.getMessage());
            return null;
        }

        XSSFSheet sheet_fff = myFFFBook.getSheet(getnameSheet(comand));
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sheet_fff;

    }

    public String getMePathOfCommand(String command){

        /**
         * Возвращает путь к файлу в соответсвии
         * с командой
         */

        if(command.equals(COMAND_TO_EXPORT_INV_15)){return Paths_Main_File.PATH_TO_USIALLY_INV15;}
        if(command.equals(COMAND_TO_EXPORT_KP_SKLAD)){return Paths_Main_File.PATH_KP_SKLAD_EXCEL;}
        if(command.equals(COMAND_TO_EXPORT_KP_SKLADMOL)){return Paths_Main_File.PATH_KP_SKLAD_EXCEL_MOL;}
        if(command.equals(COMAND_TO_EXPORT_KP_FIN)){return Paths_Main_File.PATH_FIN_KP_EXCEL;}
        if(command.equals(COMAND_TO_EXPORT_KP_FINSMENA)){return Paths_Main_File.PATH_FIN_KP_EXCELMOL;}
        if(command.equals(COMAND_TO_EXPORT_KP_FIN_EXTENDED)){return Paths_Main_File.PATH_FIN_KP_EXCEL;}
        if(command.equals(COMAND_TO_EXPORT_NULL_OSTATKU)){return Paths_Main_File.PATH_FIN_NULL_OST;}
        if(command.equals(COMAND_TO_EXPOR_SVOD_PU)){return Paths_Main_File.Path_TO_SVODPU;}
        if(command.equals(COMAND_TO_EXPORT_ODDS_LIST)){return Paths_Main_File.PATH_FIN_ODDS;}
        if(command.equals(COMAND_TO_EXPORT_TG_ODDS)){return Paths_Main_File.PATH_TG_ODDS;}
        if(command.equals(COMAND_TO_EXPORT_APP_ORDER)){return Paths_Main_File.PATH_APP_ORD;}
        if(command.equals(COMAND_TO_EXPORT_APP_ORDERSML)){return Paths_Main_File.PATH_APP_ORDSML;}

        return "";
    }

    public String getnameSheet(String comand){

        /**
         * Возвращает имя страницы
         * В соответсвии с comand
         */

        if(comand.equals(COMAND_TO_EXPORT_INV_15)){return NameSheets.nameSheetInv_15;}
        if(comand.equals(COMAND_TO_EXPORT_KP_SKLAD)){return NameSheets.nameSheetKPSklad;}
        if(comand.equals(COMAND_TO_EXPORT_KP_SKLADMOL)){return NameSheets.nameSheetKPSklad;}
        if(comand.equals(COMAND_TO_EXPORT_KP_FINSMENA)){return NameSheets.nameSheet_KPFIN;}
        if(comand.equals(COMAND_TO_EXPORT_KP_FIN)){return NameSheets.nameSheet_KPFIN;}
        if(comand.equals(COMAND_TO_EXPORT_KP_FIN_EXTENDED)){return NameSheets.nameSheet_KPFINEXT;}
        if(comand.equals(COMAND_TO_EXPORT_NULL_OSTATKU)){return NameSheets.name_Sheet_NULL;}
        if(comand.equals(COMAND_TO_EXPOR_SVOD_PU)){return NameSheets.name_sheets_Svod;}
        if(comand.equals(COMAND_TO_EXPORT_ODDS_LIST)){return NameSheets.name_Sheet_ODDS;}
        if(comand.equals(COMAND_TO_EXPORT_TG_ODDS)){return NameSheets.name_Sheets_TG;}
        if(comand.equals(COMAND_TO_EXPORT_APP_ORDER)){return NameSheets.name_Sheets_Apps;}
        if(comand.equals(COMAND_TO_EXPORT_APP_ORDERSML)){return NameSheets.name_Sheets_AppsSML;}

        return "";
    }


}
