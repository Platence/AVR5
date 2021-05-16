package all_controllers;

import all_paths.List_xlsx_files;
import all_paths.Paths_Main_File;
import javafx.stage.DirectoryChooser;
import sample.Main;

import java.io.File;
import java.util.ArrayList;

public class Add_path_address {

    public ArrayList<File> listFiles = new ArrayList<>();
    public boolean dir_accept = false;

    public static final String STANDART_NAME_INV_15 = "INV15";
    public static final String STANDART_NAME_KP_SKLAD = "KPSKLAD";

    public void add_new_path(){

        // Получаем выбранную директорию
        try {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Main.stage_main_copy);
            String d = file.getPath();
            Paths_Main_File.path_final_out = d;
            go_all_around(file);
        }
        catch (Exception e){
            Rule_contollers_Main.main_controller.info_path_field.setText("Директория не выбрана...(Укажите путь к папке содержащей списки разниц)");
        }

    }

    public String add_new_pathS(){
        // Получаем выбранную директорию
        try {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Main.stage_main_copy);
            String d = file.getPath();
            return d;

        }
        catch (Exception e){
            Rule_contollers_Main.main_controller.info_path_field.setText("Директория не выбрана...(Укажите путь к папке содержащей списки разниц)");
        }
        return "YET";
    }

    public void go_all_around(File path){

        List_xlsx_files.list_paths_xlsx.clear();
        listFiles  = tree_file(path);


        for(File lll : listFiles){

            if(lll.getName().contains(STANDART_NAME_INV_15)){continue;}    // Исключает посторонние файлы
            if(lll.getName().contains(STANDART_NAME_KP_SKLAD)){continue;}    // Исключает посторонние файлы


            List_xlsx_files.list_paths_xlsx.add(new List_xlsx_files(lll.getPath(),lll.getName()));

        }
        System.out.println("Обход дерева завершен");

        if(List_xlsx_files.list_paths_xlsx.size()>0){dir_accept=true;}

    }


    public ArrayList<File> tree_file(File folder){

        //Возвращает все файлы из папки

        File[] folderEntries = folder.listFiles();
        ArrayList<File> listTEmp = new ArrayList<>();

        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                continue;
            }
            if(entry.getName().contains("xlsx"))
            {
                listTEmp.add(entry);
            }
        }

        return listTEmp;
    }

}
