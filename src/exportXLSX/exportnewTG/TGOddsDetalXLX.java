package exportXLSX.exportnewTG;

import exportXLSX.ExportExcel;
import exportXLSX.exportODDSLIST.ExportODDSList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import tgODDS.ControlListObjects;
import tgODDS.InfoForTable;

import java.util.LinkedList;

public class TGOddsDetalXLX extends ExportExcel {

    public static LinkedList<Cell> cellnewTG= new LinkedList<>();

    public void genereteNewCell(ControlListObjects ch){

        cellnewTG.clear();

        ExportODDSList eno = new ExportODDSList();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_NEWTG_ODDS);

        int countRow = 2;
        Row r = null;     // sheet.getRow(#);



        for(InfoForTable x : ch.list){

            r = sheet.getRow(countRow);
            countRow++;

            Cell name = r.getCell(3);
            Cell surplus = r.getCell(4);
            Cell sortage = r.getCell(5);
            Cell notcount = r.getCell(6);
            Cell kpcount = r.getCell(7);
            Cell result = r.getCell(8);

            name.setCellValue((x.getNameGroup()));
            surplus.setCellValue((x.getSummPuls()));
            sortage.setCellValue(x.getSummShortage());
            notcount.setCellValue(x.getNotSearhed());
            kpcount.setCellValue(x.getCountPluKP());
            result.setCellValue(x.getResultG());

            cellnewTG.add(name);
            cellnewTG.add(surplus);
            cellnewTG.add(sortage);
            cellnewTG.add(notcount);
            cellnewTG.add(kpcount);
            cellnewTG.add(result);
        }

    }

    private static int getIntegerFromString(String s){
        try{
            return Integer.parseInt(s);
        }
        catch (Exception e){
            return 9999999;
        }
    }
}
