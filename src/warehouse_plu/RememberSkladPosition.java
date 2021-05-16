package warehouse_plu;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.exportODDSLIST.ExportODDSList;
import sample.Main;

import java.util.ArrayList;
import java.util.HashSet;


public class RememberSkladPosition {

    /**
     * Класс для хранения складких позиций.
     *
     */

    public static boolean operation = false;                        // TRUE если копирование было удачным.


    public static ArrayList<Ostatku> listOstatkuSklada = new ArrayList<>();

    public RememberSkladPosition(boolean operation){
        try
        {
            listOstatkuSklada.clear();
            listOstatkuSklada = (ArrayList<Ostatku>) Main.classOstatku.clone();
            this.operation = operation;
        }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println(listOstatkuSklada.size());
    }

    public RememberSkladPosition(){}





    public void startChek(int beginInterval){


        // На вход подаётся минимальная разница между подсчетами

        HashSet<String> alreadyChek = new HashSet<>();

          if(operation) {   // Если было запоминание

            AdvancedOstatku.clearList();

            for(Ostatku ost : Main.classOstatku){
                for(Ostatku first : listOstatkuSklada){
                    if(ost.getPlu().equals(first.getPlu())) {
                        if(alreadyChek.contains(ost.getPlu())){continue;}

                        int res = getoddsFrom2Plu(ost.oddsSUM, first.oddsSUM);   // Основное условие

                        if (res >= beginInterval) {
                            AdvancedOstatku.ostat.add(new AdvancedOstatku(ost, first.oddsCOUNT, first.oddsSUM));
                        }
                        alreadyChek.add(ost.getPlu());   // добавление в лист
                    }
                }
            }

            chekemptyList();  // Проверка на пустоту
            uploadODDSLIST(); // Выгрузка
           // for(AdvancedOstatku adv : AdvancedOstatku.ostat){ System.out.println(adv.toString()); }

          }

        else new Modal_Error().set_erroe_messege("Нет упоминаний первого слива.");
    }


    public int getoddsFrom2Plu(double a, double b){
        // a - finnalyODDS
        // b - warehouseOddds

        return (Math.abs((int)( Math.abs(a) - Math.abs(b))));
    }

    public void chekemptyList(){

        if(AdvancedOstatku.ostat.isEmpty()){
            new Modal_Error().set_erroe_messege("Список пуст. Убедитесь что про верно просчитали 1 слив," + System.lineSeparator() + "" +
                "(установите галочку запомнить, для 1 слива)." + System.lineSeparator() + "  Затем просчитали финальный подсчет.");return;
        }

    }

    public void uploadODDSLIST(){
        // Выгрузка листа ODDS
        ExportODDSList eol = new ExportODDSList();

        try
        {
            eol.genereteNewCell();
            eol.exportFile(ExportExcel.COMAND_TO_EXPORT_ODDS_LIST,eol.getCellODDSList(), Paths_Main_File.path_final_out);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка при выгрузке ODDS " + e.getMessage());
            e.printStackTrace();
        }


    }

}
