package chekVersion;

import all_controllers.Add_path_address;
import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import sample.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class CheckDefoltFolder {

    public static String lastAdress = "";
    public static String adressToPathSet = "src/path.txt";
    private Label label;

    public void load() {

        Scanner sc;

        try{
             sc = new Scanner(new File(adressToPathSet)); }
        catch (Exception e){
             save();
             return;
            }

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.equals("NO")) {
                    break;
                }
                if(s.equals("YET")){save();break;}
                lastAdress = s;
                Paths_Main_File.path_final_out = lastAdress;
                Controller.add_directory_path_DEFOLT();
            }
            sc.close();
        }



    public void save() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("AVR GLOBAL MES");
        alert.setHeaderText("Хотите использовать единственную папку для файлов ?");
        alert.setContentText("Нажмите (OK) чтобы назначить."+ System.lineSeparator() +
                "Нажмите (Cancel) и каждый раз выбирайте путь к папке." + System.lineSeparator() + "" +
                "Вы всегда можете исправить выбор в меню (Переменные среды - Настройки)");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("No selection!");
        } else if (option.get() == ButtonType.OK) {
            Add_path_address add_path_address = new Add_path_address();
            String z = add_path_address.add_new_pathS();
            setDefoltAdress(z);
        } else if (option.get() == ButtonType.CANCEL) {
            setNO();
        } else {
            this.label.setText("-");
        }
    }

    public void startLogicDefolt(){
        this.load();
    }





    public void setDefoltAdress(String z){

        Paths_Main_File.path_final_out = z;

        try(FileWriter writer = new FileWriter(adressToPathSet, false))
        {
            // запись всей строки
            String text = z;
            writer.write(text);
            // запись по символам
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        Controller.add_directory_path_DEFOLT();
    }

    public void setNO(){
        try(FileWriter writer = new FileWriter(adressToPathSet, false))
        {
            // запись всей строки
            String text = "NO";
            writer.write(text);
            // запись по символам
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}






