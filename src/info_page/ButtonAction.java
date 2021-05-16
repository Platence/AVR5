package info_page;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;

import java.io.File;

public class ButtonAction {

    public void startChek(){

        String operation = Rule_contollers_Main.main_controller.optionView.getSelectionModel().getSelectedItem();

        String nameFile = getFileName(Paths_Main_File.path_list_diff_current);

        if(operation.equals("Нулевые цены")){
            new CreateAlarmWindow().show(new NullPriceList().getnullPrice(), nameFile," Нулевые цены");
        }

        if(operation.equals("Собс.произв.")){
            new CreateAlarmWindow().show(new SelfWork().getSelf(),nameFile, " Собственное производство");
        }

        if(operation.equals("Тара возвратная")){
            new CreateAlarmWindow().show(new SelfWork().getTara(),nameFile, "Тара возвратная");
        }

        if(operation.equals("Отрицательные")){
            new CreateAlarmWindow().show(new SelfWork().getzeroHave(),nameFile,"Отрицательные");
        }

        if(operation.equals("Запрещенные")){
            new CreateAlarmWindow().show(new SelfWork().getAllErrors(),nameFile,"Запрещенные");
        }

        if(operation.equals("БлокЗапас")){
            new CreateAlarmWindow().show(new SelfWork().getBlockZapas(),nameFile,"БлокЗапас");
        }

        if(operation.equals("ES")){
            new CreateAlarmWindow().show(new SelfWork().getSearchIndex(),nameFile,"ES");
        }


    }

    public void startChek(int x){

        System.out.println("START");

        String operation = Rule_contollers_Main.main_controller.optionView.getSelectionModel().getSelectedItem();

        String nameFile = getFileName(Paths_Main_File.path_list_diff_current);

        if(operation.equals("Нулевые цены")){
            new CreateAlarmWindow().showPrint(new NullPriceList().getnullPrice(), nameFile," Нулевые цены");
        }

        if(operation.equals("Собс.произв.")){
            new CreateAlarmWindow().showPrint(new SelfWork().getSelf(),nameFile, " Собственное производство");
        }

        if(operation.equals("Тара возвратная")){
            new CreateAlarmWindow().showPrint(new SelfWork().getTara(),nameFile, "Тара возвратная");
        }

        if(operation.equals("Отрицательные")){
            new CreateAlarmWindow().showPrint(new SelfWork().getzeroHave(),nameFile,"Отрицательные");
        }


    }

    public String getFileName(String path){
        File f = new File(path);
        return f.getName();

    }
}
