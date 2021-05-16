package exportXLSX.exportNULL;

import all_controllers.logicAnalitic.BundleForNegativeCount;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.inv15i.ExportInv15;
import numberShopPack.ShopNumber;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import warehouse_plu.Ostatku;


import java.util.LinkedList;

public class ExportNullOstatku extends ExportExcel {

    public static LinkedList<Cell> cellNullOstatku = new LinkedList<>();

    public void genereteNewCell()throws Exception{

        cellNullOstatku.clear();

        ExportNullOstatku eno = new ExportNullOstatku();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPORT_NULL_OSTATKU);

        Row numberShop = null;
        Row nameFile = null;

        numberShop = sheet.getRow(0);
        Cell numberShopCell = numberShop.getCell(0);
        numberShopCell.setCellValue(setAdressToCell());
        cellNullOstatku.add(numberShopCell);


        BundleForNegativeCount.cheknullnumber();


        Row rowinfo = null;
        int i = 3;

        for(Ostatku ost : BundleForNegativeCount.nullnumberOstatku){

                rowinfo = sheet.getRow(i);
                Cell plu = rowinfo.getCell(0);
                Cell name = rowinfo.getCell(1);
                Cell area = rowinfo.getCell(2);
                Cell qf = rowinfo.getCell(3);
                Cell qu = rowinfo.getCell(4);
                Cell odds = rowinfo.getCell(5);
                Cell summ = rowinfo.getCell(6);
                Cell control = rowinfo.getCell(7);

                plu.setCellValue(ost.getPlu());
                name.setCellValue(ost.name);
                area.setCellValue(ost.gates.toString());
                qf.setCellValue(ost.qfinal);
                qu.setCellValue(ost.qychetnoe);
                odds.setCellValue(ost.oddsCOUNT);
                summ.setCellValue(ost.oddsSUM);
                control.setCellValue(replaceEMP(ost.controlChek));

                cellNullOstatku.add(plu);
                cellNullOstatku.add(name);
                cellNullOstatku.add(area);
                cellNullOstatku.add(qf);
                cellNullOstatku.add(qu);
                cellNullOstatku.add(odds);
                cellNullOstatku.add(summ);
                cellNullOstatku.add(control);

                i++;

            }
        }

    public String setAdressToCell(){

        // Назначение адреса и номера магазина в ячейку

        String adress = ShopNumber.currentNumberShop;
        adress+= " ;  " + ExportInv15.getAddress();
        return adress;
    }

    public void startUpload(){
        try
        {
            this.genereteNewCell();
            this.exportFile(ExportExcel.COMAND_TO_EXPORT_NULL_OSTATKU,cellNullOstatku, Paths_Main_File.path_final_out);
        }
        catch (Exception e) {
            new Modal_Error().set_erroe_messege("Ошибка при выгрузке NULLOST "  + e.getMessage());
            e.printStackTrace();
        }

    }

    public String replaceEMP(String control){
        if(control.equals("EMP")){return " ";}
        return control;
    }

}
