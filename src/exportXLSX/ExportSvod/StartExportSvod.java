package exportXLSX.ExportSvod;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;


import java.util.LinkedList;

public class StartExportSvod extends ExportExcel{


    public static LinkedList<Cell> cellSvodPU = new LinkedList<>();


    public void genereteNewCell()throws Exception{

        cellSvodPU.clear();

        StartExportSvod eno = new StartExportSvod();
        XSSFSheet sheet = eno.genereteSheet(ExportExcel.COMAND_TO_EXPOR_SVOD_PU);


        Row rowinfo = null;
        int i = 3;

    }


    public void startUpload(){
        try
        {
            this.genereteNewCell();
            this.exportFile(ExportExcel.COMAND_TO_EXPOR_SVOD_PU,cellSvodPU, Paths_Main_File.path_final_out);
        }
        catch (Exception e) {
            new Modal_Error().set_erroe_messege("Ошибка при выгрузке NULLOST "  + e.getMessage());
            e.printStackTrace();
        }

    }

}
