package inv15docpack;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import exportXLSX.ExportExcel;
import exportXLSX.inv15i.ExportInv15;
import interfaces_all.JustSaveInformation;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import organizationClass.AllOrganization;
import sklad_KP_AI.settngsHa.MetaHalfAutoSet;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inv15Field implements JustSaveInformation {

    public static String currentPrixko = "EMP";
    public static String currentRko = "EMP";

    public static double currentSummREESTR = 0;
    public static double currentSUMMFACT = 0;

    public static int OPERATION_PKO_CHANGE   = 1;
    public static int OPERATION_RKO_CHANGE   = 2;
    public static int OPERATION_REESTR_PARSE = 3;
    public static int OPERATION_FACT_SUMM    = 4;


    public static void setSummOrWord(String word, int operation){

        if(operation == OPERATION_PKO_CHANGE){
            // cnahge PKO
            currentPrixko = word;return;
        }

        if(operation == OPERATION_RKO_CHANGE){
            // cnahge RKO
            currentRko = word;return;
        }
        if(operation == OPERATION_REESTR_PARSE){
            // Parse Reestr
            word = replaceSimbol(word);
            currentSummREESTR = parsingDouble(word);
            return;
        }
        if(operation == OPERATION_FACT_SUMM){
            // cnahge SUMM FACT
            word = replaceSimbol(word);
            currentSUMMFACT = parsingDouble(word);

        }

    }

    public static String replaceSimbol(String target){

        // Если в качестве разделителя была запятая, заменим ее

        String temp = target.replace(',','.');

        return temp;

    }

    public static double parsingDouble(String target){

        double d = 0;

        try {
            d = Double.parseDouble(target);
            System.out.println("Parsing " + " " + d);
        }

        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка при переводе строки в число " + target + " / " + e.getMessage());
        }

        return d;

    }

    public static void setHandler(){

        // Установка слушателей

        Rule_contollers_Main.main_controller.pkoField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setSummOrWord(Rule_contollers_Main.main_controller.pkoField.getText(),OPERATION_PKO_CHANGE);
            }
        });



        Rule_contollers_Main.main_controller.rko_field.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setSummOrWord(Rule_contollers_Main.main_controller.rko_field.getText(),OPERATION_RKO_CHANGE);
            }
        });



        Rule_contollers_Main.main_controller.reestr_summ.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setSummOrWord(Rule_contollers_Main.main_controller.reestr_summ.getText(),OPERATION_REESTR_PARSE);
            }
        });

        Rule_contollers_Main.main_controller.fact_summ.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setSummOrWord(Rule_contollers_Main.main_controller.fact_summ.getText(),OPERATION_FACT_SUMM);
            }
        });
    }

    public static void startUpload(){

        if(AllOrganization.currentPickNamePrganization.equals("EMP")){
            new Modal_Error().set_erroe_messege("Вы не назначили организацию и номер приказа!" + System.lineSeparator()+"" +
                    "Смотрите вкладку \" Подготовка \" ");
            return;
        }

        try {
            ExportInv15 exportInv15 = new ExportInv15();
            exportInv15.genereteallCellInv15();
            exportInv15.exportFile(ExportExcel.COMAND_TO_EXPORT_INV_15, ExportInv15.getCellListInv15(), Paths_Main_File.path_final_out);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Что-то пошло не так... Файл не выгружен!" + e.getMessage());
            e.printStackTrace();
        }

        if(Rule_contollers_Main.main_controller.save_all_field_inv_15.isSelected()){
            new Inv15Field().save(Paths_Main_File.PATH_INFORMATION_INV15);
        }



    }

    public void setInformationToField(){
        Rule_contollers_Main.main_controller.fact_summ.setText(String.valueOf(currentSUMMFACT));
        Rule_contollers_Main.main_controller.reestr_summ.setText(String.valueOf(currentSummREESTR));
        Rule_contollers_Main.main_controller.rko_field.setText(currentRko);
        Rule_contollers_Main.main_controller.pkoField.setText(currentPrixko);


    }


    @Override
    public void save(String z) {
        StringBuilder sb = new StringBuilder();
        // ПКО#РКО#СуммаФакт#СуммаРеестр#
        sb.append(currentPrixko+"#"+currentRko+"#"+currentSUMMFACT+"#"+currentSummREESTR+"#");
        System.out.println("Save information " + sb.toString());

        FileWriter fw = null;
        try {
            fw = new FileWriter(z);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null;
            fw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        File file = new File(Paths_Main_File.PATH_INFORMATION_INV15);

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(fr);


        while (sc.hasNextLine()){
            String j = sc.nextLine(); // Вызов разделителя
            // добавление в listMeta;
            patternrd(j);
        }

        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = null;

        setInformationToField();
    }

    public void patternrd(String zxc){

        // Данные каждого класса это 4
        // переменные которые мы обработаем
        // по отдельности

        Pattern p = Pattern.compile("(.*?)#");
        Matcher m = p.matcher(zxc);

        String res1 = "";
        String res2 = "";
        String res3 = "";   // Сумма факт
        String res4 = "";   // Сумма реестр

        int x = 0;

        // ПКО#РКО#СуммаФакт#СуммаРеестр#

        while (m.find()){

            if(x==0){res1 = m.group(1);}
            if(x==1){res2 = m.group(1);}
            if(x==2){res3 = m.group(1);}
            if(x==3){res4 = m.group(1);}
            x++;
        }


        try {
            double fact = Double.parseDouble(res3);
            double reestr = Double.parseDouble(res4);
            currentSummREESTR = reestr;
            currentSUMMFACT = fact;

            currentPrixko = res1;
            currentRko = res2;
        }
        catch (Exception e){
            System.out.println("Ошибка присвоения числа Meta Hand_a + 142 line");
        }



    }
}
