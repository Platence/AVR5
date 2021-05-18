package tgODDS;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.exportnewTG.TGOddsDetalXLX;

public class ControlPackegeNEWTG {

    // Main comands here

    public static void toDo(){
        ControlListObjects clo = new ControlListObjects();
        // Составление всех данных для таблицы
        printInfo(clo);

        try {
            TGOddsDetalXLX tg = new TGOddsDetalXLX();
            tg.genereteNewCell(clo);
            tg.exportFile(ExportExcel.COMAND_TO_EXPORT_NEWTG_ODDS, TGOddsDetalXLX.cellnewTG, Paths_Main_File.path_final_out);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("непредвиденная ошибка " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void printInfo(ControlListObjects controlListObjects){

        for (InfoForTable clo : controlListObjects.list){
            System.out.println(clo.toString());
        }
    }
}
