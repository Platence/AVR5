package exportXLSX.exportODDSLIST;


import exportXLSX.ExportExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import warehouse_plu.AdvancedOstatku;
import warehouse_plu.Ostatku;

import java.util.LinkedList;

public class ExportODDSList extends ExportExcel {

    /**
     * Класс выгрузки разности подсчетов.
     */

    public static LinkedList<Cell> cellODDSList = new LinkedList<>();

    public LinkedList<Cell> getCellODDSList() {
        return cellODDSList;
    }

    public void genereteNewCell() throws Exception{

        clearList();

        ExportODDSList eno = new ExportODDSList();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_ODDS_LIST);

        Row rowinfo = null;
        int i = 2;
        LinkedList<AdvancedOstatku> temp = AdvancedOstatku.getSortList();
        for(AdvancedOstatku ost : temp){

            rowinfo = sheet.getRow(i);
            Cell plu = rowinfo.getCell(2);
            Cell name = rowinfo.getCell(3);
            Cell area = rowinfo.getCell(4);
            Cell summ = rowinfo.getCell(5);
            Cell summN = rowinfo.getCell(6);
            Cell odds = rowinfo.getCell(7);
            Cell oddsN = rowinfo.getCell(8);
            Cell control = rowinfo.getCell(9);
            Cell increment = rowinfo.getCell(10);
            Cell yu3 = rowinfo.getCell(11);

            plu.setCellValue(ost.getOstatku().getPlu());
            name.setCellValue(ost.getOstatku().getName());
            area.setCellValue(ost.getOstatku().gates.toString());
            summ.setCellValue((int)ost.getSklSumm());
            summN.setCellValue((int)ost.getOstatku().getOddsSUM());
            odds.setCellValue(ost.getSklcount());
            oddsN.setCellValue(ost.getOstatku().getOddsCOUNT());
            control.setCellValue(chekEMP(ost.getOstatku()));
            increment.setCellValue(getIncrement(ost.getSklSumm(),ost.getOstatku().getOddsSUM()));
            yu3.setCellValue(ost.getOstatku().yu3);

            cellODDSList.add(plu);
            cellODDSList.add(name);
            cellODDSList.add(area);
            cellODDSList.add(summ);
            cellODDSList.add(summN);
            cellODDSList.add(odds);
            cellODDSList.add(oddsN);
            cellODDSList.add(control);
            cellODDSList.add(increment);
            cellODDSList.add(yu3);

            i++;

        }
    }




    public static void clearList(){
        cellODDSList.clear();
    }

    public static String chekEMP(Ostatku ost){
        if(ost.getControlChek().equals("EMP")){return " ";}
        else return ost.getControlChek();
    }

    public static String getIncrement(double first, double second){

        int result = (int)(second-first);

        return result>first ? String.valueOf(result) : "-" + String.valueOf(result);

    }
}
