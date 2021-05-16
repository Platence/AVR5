package WRSOnLine;

import all_controllers.Rule_contollers_Main;
import error_package.SlideError.SlideModalError;
import javafx.application.Platform;
import sample.Controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class SettingsOnWrs {

    public static boolean onWebPage;
    private Controller controller;

    private static final String path = "src/wrsstatus.txt";

    public SettingsOnWrs(Controller controller){
        this.controller = controller;
    }


    public void loadSettings(){

        try {
            FileReader fr = new FileReader(path);
            Scanner sc = new Scanner(fr);
            String value = "3";
            while (sc.hasNext()){
                value = sc.nextLine();
            }

            if(value.equals("1")){onWebPage = true;}
            if(value.equals("0")){onWebPage = false;}

            fr.close();
        }
        catch (Exception e){
            Platform.runLater(()->{new SlideModalError().setMessage("Создание переменной посещения WRS" + System.lineSeparator() +"" +
                    "Посетите настройки - Переменные среды");});

            onWebPage = true;

            if(!saveSettings(onWebPage)){
                Platform.runLater(()->{new SlideModalError().setMessage("Не удалось получить/сохранить " + System.lineSeparator() + "" +
                        "статус разрешения веб-страницы - ON(WRS/OS)");});
            }

        }


        System.out.println("STATUS WEB - " + onWebPage);

        if(!onWebPage){
            controller.wrsOnLine.setDisable(true);
            controller.webPageSettings.setSelected(true);
        }

        if(onWebPage){
            controller.wrsOnLine.setDisable(false);
            controller.webPageSettings.setSelected(false);
        }


    }

    public boolean saveSettings(boolean status){

        try {
            FileWriter wf = new FileWriter(path);
            if (status) {
                wf.write("1");
            }
            if (!status) {
                wf.write("0");
            }
            wf.close();
        }
        catch (Exception e){
            return false;
        }

        return true;
    }


}
