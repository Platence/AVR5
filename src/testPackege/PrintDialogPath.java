package testPackege;

import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;
import javafx.scene.control.Alert;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintDialogPath {

    private String path;

    public PrintDialogPath() {
        creteTest();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void creteTest(){

        String t = Rule_contollers_Main.main_controller.sdfsdfsdf.getText();
        System.out.println(t);

        File f = new File(t);

        try {
            Scanner sc = new Scanner(f);
            sc.close();
        }

        catch (FileNotFoundException e) {
            new Modal_Error().set_erroe_messege("НЕТ СОЕДИНЕНИЯ!");
            e.printStackTrace();
        }

        if(f!=null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(f.getAbsolutePath());
            alert.setHeaderText("TEST PATH IS : ");
            alert.show();
        }
        else System.out.println("Ошибка при назначении файла");
        f = null;
    }
}
