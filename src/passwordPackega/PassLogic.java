package passwordPackega;

import error_package.Modal_Error;
import info_page.ButtonAction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import numberShopPack.ShopDescription;
import sample.Controller;


public class PassLogic {

    public static String logerOnter = "sskrr71";
    public static boolean status = false;

    public void createPass(Controller cr){

        cr.sskrrpasswordFIELD.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(cr.sskrrpasswordFIELD.getText().equals(logerOnter)){
                    cr.tabsskrr.setDisable(false);
                    cr.kp_sklad_tab.setDisable(true);
                    cr.analitik_tab.setDisable(false);
                    //new AnnotaionMainMenu().goAnimationMainMenuAdvanced();
                    new Modal_Error().set_erroe_messege("Режим ССКРР Успешно включен.Перейдите во вкладку SSKRR" + System.lineSeparator() + " " +
                            "Поиск магазинов отключен");

                }

            }
        });
    }







}
