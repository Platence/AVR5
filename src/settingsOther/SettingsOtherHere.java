package settingsOther;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;

public class SettingsOtherHere {

    public static boolean sortBygroup;

    public void initGroup(){
        /**
         * Загружает состояние Сортировки по зонам в актах
         */

        try {
            String result = Paths_Main_File.loadInformation(Paths_Main_File.PATH_SORT_BY_GROUP_VALUE, "Load Sort Settings");
            if(result.equals("true")){sortBygroup = true;}
            if(!result.equals("true")){sortBygroup = false;}
            Rule_contollers_Main.main_controller.sortByGroup.setSelected(sortBygroup);
            Rule_contollers_Main.main_controller.sortByGroup.setOnMouseClicked(event -> changeSortGroup());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            new Modal_Error().set_erroe_messege(e.getMessage());
        }

    }

    public void changeSortGroup(){
        if(sortBygroup) {
            try {
                sortBygroup = false;
                Paths_Main_File.saveInformation(Paths_Main_File.PATH_SORT_BY_GROUP_VALUE, "false");
                return;
            }
            catch (Exception e){
                new Modal_Error().set_erroe_messege(" Error ! Try save sort group value");
            }
        }

        if(!sortBygroup) {
            try {
                sortBygroup = true;
                Paths_Main_File.saveInformation(Paths_Main_File.PATH_SORT_BY_GROUP_VALUE, "true");

            }
            catch (Exception e){
                new Modal_Error().set_erroe_messege(" Error ! Try save sort group value");
            }
        }

    }

}
