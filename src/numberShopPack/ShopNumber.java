package numberShopPack;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import interfaces_all.JustSaveInformation;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class ShopNumber implements JustSaveInformation {

    public static String currentNumberShop = "EMP";

    @Override
    public void save(String z) {
        currentNumberShop = z;
        try(FileWriter fw = new FileWriter(new File(Paths_Main_File.PATH_TO_LAST_SHOP))){
            fw.write(z);
        }
        catch (Exception e){
            System.out.println("Error save current shop number");
        }
    }

    @Override
    public void load() {

        try(FileReader fr = new FileReader(new File(Paths_Main_File.PATH_TO_LAST_SHOP))){
            Scanner sc = new Scanner(fr);
            String temp = sc.nextLine();
            sc.close();
            currentNumberShop = temp;
        }
        catch (Exception e){
            System.out.println("Error with loading current shop number");
        }
    }

    public static void setDefaultHandler(){
        new ShopNumber().load();
        Rule_contollers_Main.main_controller.number_shop.setText(currentNumberShop);

    }
}
