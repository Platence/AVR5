package dateClass;

import WRSOnLine.SettingsOnWrs;
import all_controllers.Rule_contollers_Main;
import chekCrashPLU.Karantin.ControlTableKarantin;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import javafx.application.Platform;
import javafx.scene.control.Tab;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class OpenWrs {

    public static boolean wasOpened = false;

    public OpenWrs() {

        this.open_wrs();

    }

    public void open_wrs() {

        if(wasOpened){return;}

        Platform.runLater(()->{
            wasOpened = true;
            try{
                System.out.println(Rule_contollers_Main.main_controller.organization_box.getSelectionModel().getSelectedItem().toString());
                new SlideModalError().setMessage(
//                        System.lineSeparator()+
//                        System.lineSeparator()+
//                        System.lineSeparator()+

                        "Замечание : " + System.lineSeparator()+ "WRS не посещался.Перенаправление на //wrs.x5.ru...");
            }
            catch (Exception e){
                new SlideModalError().setMessage(
//                                System.lineSeparator()+
//                                System.lineSeparator()+
//                                System.lineSeparator()+
                        ifWRSOff());
            }
            });

        if(!SettingsOnWrs.onWebPage){
            return;
        }

        Platform.runLater(()->{
            Rule_contollers_Main.main_controller.main_pain.getSelectionModel().select(Rule_contollers_Main.main_controller.awrsos);
            Rule_contollers_Main.main_controller.mainPain2.getSelectionModel().select(1);
            Rule_contollers_Main.main_controller.mainPain2.getSelectionModel().select(0);
        });
    }

    private static String ifWRSOff(){
        if(!SettingsOnWrs.onWebPage){
            return "Замечание : " + System.lineSeparator()+ "ВЫ НЕ ВЫБРАЛИ ПРИКАЗ"+ System.lineSeparator() + "WRS: отключен";
        }

        return "Замечание : " + System.lineSeparator()+ "ВЫ НЕ ВЫБРАЛИ ПРИКАЗ"+ System.lineSeparator() + "WRS: нет посещений.";
    }

}
