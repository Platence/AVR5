package exportXLSX.exportSearch;

import all_paths.Paths_Main_File;

import error_package.Modal_Error;
import javafx.collections.ObservableList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import warehouse_plu.Ostatku;

import java.awt.*;
import java.io.File;

import java.io.FileOutputStream;
import java.util.LinkedList;


public class CreateExportSearchShit {

    private ObservableList<Ostatku> list;

    public CreateExportSearchShit(ObservableList<Ostatku>list) {
        this.list = list;
        startOption();
    }


    private void startOption(){

        XSSFWorkbook book = new XSSFWorkbook ();
        XSSFSheet sheet = book.createSheet("ListOne");

        // Нумерация начинается с нуля


        StringBuilder cellBuilder = new StringBuilder();
        LinkedList<Cell> cellList = new LinkedList<>();
        int count = 1;

        for(Ostatku ost : this.list){
            Row row = sheet.createRow(count);
            Cell plu  = row.createCell(1);
            Cell name = row.createCell(2);
            Cell area = row.createCell(3);
            Cell qf   = row.createCell(4);
            Cell qy   = row.createCell(5);
            Cell oc   = row.createCell(6);
            Cell os   = row.createCell(7);
            Cell kp   = row.createCell(8);
            Cell st   = row.createCell(9);
            st.setCellValue("[     ]");
            kp.setCellValue(ost.getControlChek());
            os.setCellValue(ost.getOddsSUM());
            oc.setCellValue(ost.getOddsCOUNT());
            qy.setCellValue(ost.getQychetnoe());
            qf.setCellValue(ost.getQfinal());
            area.setCellValue(ost.getArea_one_strok());
            name.setCellValue(ost.getName());
            plu.setCellValue(ost.getPlu());
            count++;
        }

        try {
            FileOutputStream outFile = new FileOutputStream(new File(Paths_Main_File.path_final_out + "\\" + "ES" + ".xlsx"));
            book.write(outFile);
            outFile.close();
            book.close();
        }

        catch (Exception e){
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("Ошибка выгрузки. Возможно занято другим процессом");
        }

        try {
            File file=new File(Paths_Main_File.path_final_out + "\\" + "ES" + ".xlsx");
            Desktop.getDesktop().open(file);
        }

        catch (Exception e) {
            e.printStackTrace();
            new Modal_Error().set_erroe_messege("не удалось открыть файл, проверьте его вручную");
        }

    }
}
