package dateClass;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import interfaces_all.JustSaveInformation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class DateIni {

    public static String dateFromPlanInventory = "";
    public static String dateFromOrderMonth = "";

    public static final int OPERATION_FROM_DATE = 1;
    public static final int OPERATION_FROM_ORDER_DATE = 2;


    public DateIni() {

    }

    public void createStandartDate(){
        load(Paths_Main_File.PATH_TO_DATE,OPERATION_FROM_DATE);              // Load date inventory
        load(Paths_Main_File.PATH_TO_ORDER_DATE,OPERATION_FROM_ORDER_DATE);  // Load date Order

        Rule_contollers_Main.main_controller.calendar_main.getEditor().setText(dateFromPlanInventory);
        Rule_contollers_Main.main_controller.calendar_order.getEditor().setText(dateFromOrderMonth);

    }


    public void save(String dateTemp, String type, int operation) {

        if(operation == OPERATION_FROM_DATE){
            dateFromPlanInventory = dateTemp;
            System.out.println("new update dateFromPlanInventory " + dateFromPlanInventory);
        }

        if(operation == OPERATION_FROM_ORDER_DATE){
            dateFromOrderMonth = dateTemp;
            System.out.println("new update dateFromOrderMonth " + dateFromOrderMonth);
        }

        try(FileWriter fw = new FileWriter(new File(type))){
            fw.write(dateTemp);
        }

        catch (Exception e){
            System.out.println("Error save settings in Dateini line 34");
        }

    }



    public void load(String path, int operation) {

        try(FileReader fr = new FileReader(new File(path))){

            Scanner sc = new Scanner(fr);

            String datt = sc.nextLine();       // here only one read
            sc.close();
            if(operation == OPERATION_FROM_DATE) {
                dateFromPlanInventory = datt;
            }

            if(operation == OPERATION_FROM_ORDER_DATE){
                dateFromOrderMonth = datt;
            }

        }
        catch (Exception e){
            System.out.println("System Error in write date time line 35");
        }
    }




}
