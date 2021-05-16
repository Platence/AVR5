package animation_elements;

import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;
import interfaces_all.JustSaveInformation;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import sample.Main;
import settings_main.Adres_Path_Picture;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Scanner;

public class AddPicutre implements JustSaveInformation {


    public void setImage(String path_Image)  {


        System.out.println("Подгрузка изображения");
        File fie = new File(path_Image);
        Image image  = null;
        try {
            image = new Image(fie.toURL().toString(),1250,655,false,false);
        } catch (MalformedURLException e) {
            new Modal_Error().set_erroe_messege("Сожалеем, ваше изображение " + path_Image + " " + System.lineSeparator() + "" +
                    "Не удалось присвоить");
        }
        Rule_contollers_Main.main_controller.image_main.setImage(image);
        Rule_contollers_Main.main_controller.image_main.setOpacity(0.4);
        fie = null;
    }

    public void get_PathToPodgotovkaPicture(){

        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(Main.stage_main_copy);
        String s = file.getPath();

        setImage(s);
        save(s);
    }


    @Override
    public void save(String z) {

        File file = new File(Adres_Path_Picture.path_to_Path_picture_inf);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fw.write(z);
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

        File file = new File(Adres_Path_Picture.path_to_Path_picture_inf);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fr);

        String s = "";
        while (sc.hasNextLine()){
            s = sc.nextLine();
        }

        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = null;

        setImage(s);

    }


}
