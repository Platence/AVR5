package settings_main;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BlockingClass {

    /*
    Некоторые опции для КП ФИН
    */

    public static int countTry = 0;

    public static void loadStateS999KPF(String path, CheckBox box){

        if(countTry>1){return;}

        try {
            Scanner sc = new Scanner(new File(path));
            int x = 0;
            while (sc.hasNextLine()){
                String s = sc.nextLine();
                if(s.equals("BLOCK")){ box.setSelected(true);}
                if(s.equals("UNLOCK")){ box.setSelected(false);}
                break;
            }
            sc.close();
        }

        catch (FileNotFoundException e) {
            try {
                countTry++;
                FileWriter wf = new FileWriter(path);
                wf.write("UNLOCK");
                wf.close();
                loadStateS999KPF(path,box);
            }

            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void changeStatusS999(String path,CheckBox box){

        boolean status = box.isSelected();
        try(FileWriter wf = new FileWriter(path)){
            if(status){wf.write("BLOCK");}
            if(!status){wf.write("UNLOCK");}
        }

        catch (IOException e) {
            new Modal_Error().set_erroe_messege("Ошибка записи состояния " + path);
        }
    }
}
