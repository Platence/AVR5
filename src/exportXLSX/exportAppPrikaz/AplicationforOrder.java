package exportXLSX.exportAppPrikaz;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.exportODDSLIST.GetMeListForOrderApp;
import info_page.smenaMOLPACKAGE.DescriptionSmenaMOL;
import org.apache.poi.ss.usermodel.Cell;

import java.util.LinkedList;

public class AplicationforOrder extends ExportExcel {

    private LinkedList<Cell> cellListOrd;
    private boolean smenaMol;

    public AplicationforOrder(){
        this.smenaMol = DescriptionSmenaMOL.smenaMolD;
        this.cellListOrd = new LinkedList<>();
        this.uploadXLSList();
    }

    public void uploadXLSList(){
        try {
            cellListOrd = GetMeListForOrderApp.getMelistAppORD(this);
            this.exportFile(rightComand(), cellListOrd, Paths_Main_File.path_final_out);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка выполнения " + e);
            e.printStackTrace();
        }
    }

    public String rightComand(){
        if(this.isSmenaMol()){return COMAND_TO_EXPORT_APP_ORDERSML;}
        return COMAND_TO_EXPORT_APP_ORDER;
    }


    public LinkedList<Cell> getCellListOrd() {
        return cellListOrd;
    }

    public void setCellListOrd(LinkedList<Cell> cellListOrd) {
        this.cellListOrd = cellListOrd;
    }

    public boolean isSmenaMol() {
        return smenaMol;
    }

    public void setSmenaMol(boolean smenaMol) {
        this.smenaMol = smenaMol;
    }

    public String wordReazon(){
        if(this.smenaMol){return "СменаМОЛ";}
        return "Плановая";
    }
}
