package chekCrashPLU;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;
import interfaces_all.JustSaveInformation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import parser_xml.SAXPars;
import sample.Main;
import sklad_KP_AI.Container_KP_Sklad;
import sklad_KP_AI.TableViewClass.InitializateTable;
import sklad_KP_AI.TableViewClass.ListKPSkladOBJ;
import warehouse_plu.Ostatku;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CrashesPlu implements JustSaveInformation {


    public static ArrayList<CrashesPlu> listCrashes = new ArrayList<>();

    public static boolean wasOpen = false;
    public static boolean haveErrors = false;
    public static StringBuilder stringBuilder = new StringBuilder();

    private String plu;
    private String description;


    public CrashesPlu(String plu, String description) {
        this.plu = plu;
        this.description = description;
    }

    public CrashesPlu() {

    }

    public static void setTableView(){
        Rule_contollers_Main.main_controller.table_crashes.setItems(FXCollections.observableArrayList(listCrashes));
    }



    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void deletePosition(String plu,String description){
        for(CrashesPlu ss : listCrashes){
            if(ss.getPlu().equals(plu)){listCrashes.remove(ss);return;}
        }

    }


    @Override
    public void save(String z)  {

        // Приходящее значение не обязательно
        // Ведется проход по уже измененному листу

        StringBuilder sb = new StringBuilder();
        for(CrashesPlu list : listCrashes){
            sb.append(list.getPlu()+"!"+list.getDescription()+"!"+System.lineSeparator());
            if(list.getDescription().contains("!")){new Modal_Error().set_erroe_messege("Запрещено сохранять данный символ - [!]");return;}
            if(list.getPlu().contains("!")){new Modal_Error().set_erroe_messege("Запрещено сохранять данный символ - [!]");return;}
            // name ! number ! + LineSeparator
        }


        try {
            FileOutputStream fous = new FileOutputStream(new File(Paths_Main_File.CRASHES_PLU_ADDRES));
            byte [] content = sb.toString().getBytes();
            try {
                fous.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fous.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Error with save file organization line 96");
            new Modal_Error().set_erroe_messege("Возникла ошибка при сохранении PPLU. " );
        }


    }

    @Override
    public void load() {

        listCrashes.clear();                                     // Clear old list

        try(FileReader fr = new FileReader(new File(Paths_Main_File.CRASHES_PLU_ADDRES))){

            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()){

                listCrashes.add(getloaderOrganization(sc.nextLine()));

                // Try add new(old) position from Pattern. return new Object in list
            }
            sc.close();

        }
        catch (Exception e){
            System.out.println("Error with loading order and name organization line 33");
        }



        Rule_contollers_Main.main_controller.plu_crashes.setCellValueFactory(new PropertyValueFactory("plu"));
        Rule_contollers_Main.main_controller.description_crashes.setCellValueFactory(new PropertyValueFactory("description"));

        Rule_contollers_Main.main_controller.plu_crashes.setCellFactory(TextFieldTableCell.forTableColumn());
        Rule_contollers_Main.main_controller.description_crashes.setCellFactory(TextFieldTableCell.forTableColumn());

        Rule_contollers_Main.main_controller.plu_crashes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CrashesPlu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CrashesPlu, String> event) {
                String oldWord = event.getOldValue();
                String newWord = event.getNewValue();
                System.out.println(oldWord + " " + newWord);
                updateFieldPLU(oldWord,newWord);
                setTableView();
                save("");
                System.out.println("Save succefull");
            }
        });

        Rule_contollers_Main.main_controller.description_crashes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CrashesPlu, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CrashesPlu, String> event) {
                String oldWord = event.getOldValue();
                String newWord = event.getNewValue();
                System.out.println(oldWord + " " + newWord);
                updateFieldDESC(oldWord,newWord);
                setTableView();
                save("");
                System.out.println("Save succefull");
            }
        });




    }

    public static CrashesPlu getloaderOrganization(String line){

        // Name ! number !

        Pattern p = Pattern.compile("(.*?)!");
        Matcher m = p.matcher(line);

        String tempMassive [] = new String[2];

        int count = 0;

        while (m.find()){
            tempMassive[count] = m.group(1);
            count++;
        }

        return new CrashesPlu(tempMassive[0],tempMassive[1]);
    }

    public static void setOpene(){
        if(wasOpen){wasOpen = false;return;}
        else wasOpen = true;
    }

    public static void updateFieldPLU(String old,String ups){

        for(CrashesPlu cr : listCrashes){
            if(cr.getPlu().equals(old)){
                cr.setPlu(ups);
                return;
            }
        }

    }

    public static void updateFieldDESC(String old,String ups){

        for(CrashesPlu cr : listCrashes){
            if(cr.getDescription().equals(old)){
                cr.setDescription(ups);
                return;
            }
        }


    }


    public static void chek_all_point_PLU() {
        /*
            Проверка нерабочих, или запрещенных PLU, хлеб СП
         */
       try{

        haveErrors = false;
        stringBuilder.setLength(0);
        StringBuilder sbSP = new StringBuilder();
        StringBuilder sbTara = new StringBuilder();
        StringBuilder pasxa = new StringBuilder();

        Ostatku.count_sklad_plu = 0;

            for(Ostatku xxx : Main.classOstatku){
                if(xxx.getYu2().equals("Хлеб СП")&& xxx.qfinal>0){
                    if(xxx.oddsSUM<9000 && xxx.oddsSUM>0){sbSP.append("Обнаружен Хлеб СП " + xxx.plu + " " + xxx.oddsSUM + " " + System.lineSeparator());}}
                if(xxx.getYu2().equals("Тара возвратная") && xxx.controlChek.equals("EMP")){sbTara.append("Нет КП по таре : " + xxx.getName() + System.lineSeparator());}

                for(CrashesPlu plu : listCrashes){
                    if(xxx.getPlu().equals(plu.getPlu())){
                        stringBuilder.append(xxx.getPlu() + " [ " + plu.getDescription() + " ]");
                        stringBuilder.append(System.lineSeparator());
                    }
                }
                if(PasxaPLU.havePaskha(xxx.getPlu())){pasxa.append("Обнаружена PLU ПАСХА : " + xxx.getPlu() + System.lineSeparator());}
                xxx.add_count(xxx);
            }

            stringBuilder.append(sbSP.toString());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(sbTara);
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(pasxa);

           if (SAXPars.newsverka){setColorRedOnTab2();}


            System.out.println("work");

            if(stringBuilder.length()>1){

                Platform.runLater((()->{
                    //new Modal_Error().setMessage_Only("Обнаружены Проверочные PLU " + System.lineSeparator() + stringBuilder.toString());

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information from AVR5");
                    alert.setHeaderText("AVR BOT SAY...");
                    alert.getDialogPane().setPrefSize(650,450);
                    Label area = new Label();
                    area.setWrapText(true);

                    //new Modal_Error().set_erroe_messege("Возможные ошибки/Проверочные PLU : " + System.lineSeparator() + " " + stringBuilder.toString());
                    stringBuilder.append(System.lineSeparator() + "ИНФОРМАЦИЯ О ПРИСУТСТВИИ ПРОВЕРОЧНЫХ PLU");
                    stringBuilder.append(System.lineSeparator());
                    stringBuilder.append(System.lineSeparator() + "Внесено в склад  : " + Ostatku.count_sklad_plu );
                    stringBuilder.append(System.lineSeparator());
                    stringBuilder.append(System.lineSeparator());

                    try { ListKPSkladOBJ.fillAllPos(); InitializateTable.updateTable();
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Загружен последний(Старый) удачно созданный список КП Склада");
                        Rule_contollers_Main.main_controller.info11.setText("Загружены последние PLU акта КПС.");

                    }



                    catch (Exception e){
                        new SlideModalError().setMessage("Ошибка при загрузке складских PLU ");
                    }

                    area.setText(stringBuilder.toString());
                    ScrollPane sp = new ScrollPane();
                    sp.setPrefSize(650,450);
                    sp.setContent(area);
                    alert.getDialogPane().setContent(sp);

                    System.out.println("Обнаружены битые PLU!");

                    haveErrors = true;
                    alert.show();
                }));

            }

    }

       catch (Exception e){
           new Modal_Error().set_erroe_messege("ОШИБКА В ПРОХОДЕ КАРТЫ ПРОВЕРКИ!!!!");
       }

    }

    public static void setColorRedOnTab2(){
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("БЫЛА АКТИВИРОВАНА ВЕРСИЯ CROSS");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("Если данные отображаются некорректно :");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("Удалите в сверке разниц все столбцы после L");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("Столбец L удалять не нужно");
        CrashesPlu.stringBuilder.append(System.lineSeparator());
        CrashesPlu.stringBuilder.append("Рассчитайте сверку разниц ещё раз");
        CrashesPlu.stringBuilder.append(System.lineSeparator());

    }
}
