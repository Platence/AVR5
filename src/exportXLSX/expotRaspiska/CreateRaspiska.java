package exportXLSX.expotRaspiska;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.inv15i.ExportInv15;
import exportXLSX.inv15i.InformationX;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import numberShopPack.ShopDescription;
import numberShopPack.ShopNumber;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import peopleComisson.PeopleComisson;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import static exportXLSX.inv15i.ExportInv15.getInformationX;

public class CreateRaspiska {

    public void creteRaspiska(String pathOut){

        LinkedList<Cell> listCell = new LinkedList<>();

        FileInputStream fis = null;
        XSSFWorkbook myFFFBook = null;
        try {
            fis = new FileInputStream(Paths_Main_File.path_to_Raspiska);
            myFFFBook = new XSSFWorkbook(fis);
        }

        catch (Exception e) {
            new Modal_Error().set_erroe_messege("Ошибка создания WORKBOOK " + e.getMessage());
            return;
        }

        XSSFSheet sheet_fff = myFFFBook.getSheet("RRR");
        InformationX informationX = getInformationX();

        Cell org = null;
        org = sheet_fff.getRow(6).getCell(5);
        org.setCellValue(informationX.getOrganizacuya());
        listCell.add(org);

        Cell magAndSap = null;
        magAndSap = sheet_fff.getRow(7).getCell(5);
        String s = "Магазин № " + Rule_contollers_Main.main_controller.number_shop.getText();
        s+= ", SAP № " + ShopDescription.getMeSap(Rule_contollers_Main.main_controller.number_shop.getText());
        magAndSap.setCellValue(s);
        listCell.add(magAndSap);

        Cell date = null;
        date = sheet_fff.getRow(11).getCell(7);
        String z = informationX.getDate_INVENT();
        String ddate = z.replace(".","/");
        date.setCellValue(ddate + " г.");
        listCell.add(date);


        Cell molDol = null;
        molDol = sheet_fff.getRow(22).getCell(5);
        molDol.setCellValue(PeopleComisson.dolMOL);
        listCell.add(molDol);

        Cell fioMol = null;
        fioMol = sheet_fff.getRow(22).getCell(7);
        fioMol.setCellValue(PeopleComisson.MAN_MOL);
        listCell.add(fioMol);

        if(DescriptionSmenaMOL.getTypePU().equals(DescriptionSmenaMOL.SMENAMOLString)){
            Cell mol2 = null;
            mol2 = sheet_fff.getRow(25).getCell(5);
            mol2.setCellValue(DescriptionSmenaMOL.getDolMOlSmenaMo());
            listCell.add(mol2);

            Cell fio2 = null;
            fio2 = sheet_fff.getRow(25).getCell(7);
            fio2.setCellValue(DescriptionSmenaMOL.getFIOPrynyalMOL());
            listCell.add(fio2);
        }




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
                new Modal_Error().set_erroe_messege("Ошибка сохранения, пустая ячейка " + temp.getRowIndex() + " " + temp.getColumnIndex());
            }

        }

        try {
            //new Modal_Error().set_erroe_messege("Произошла попытка выгрузки расписки");
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошиька выгрузки расписки! " + e.getMessage());
        }

        try {
            FileOutputStream outFile = new FileOutputStream(new File(pathOut+"\\"+"Raspuska"+".xlsx"));
            myFFFBook.write(outFile);
            outFile.close();

            try {

                File file=new File(pathOut+"\\"+"Raspuska"+".xlsx");
                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        catch (Exception e){
            System.out.println(e);
            new Modal_Error().set_erroe_messege(e.getMessage());
        }

        listCell.clear();
        try {
            myFFFBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
