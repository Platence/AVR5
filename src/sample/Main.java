package sample;


import chekCrashPLU.PasxaPLU;
import decriptor.ConsoleAVR;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import warehouse_plu.Ostatku;

import javax.swing.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main extends Application {

   public static ArrayList<Ostatku> classOstatku = new ArrayList<>();
   public static Stage stage_main_copy;   // Необходимо для FileChooser


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage_main_copy = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AVR5");
        Scene scene = new Scene(root,1250,655);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        stage_main_copy.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ConsoleAVR.alive = false;
                System.exit(0);
            }
        });

        PasxaPLU ps = new PasxaPLU();

    }

    public static void main(String[] args) {

       setStandatCharSets();

        try { launch(args); }

        catch (Exception e){
            System.out.println(e);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void setStandatCharSets(){

        try {
            System.setProperty("file.encoding", "UTF-8");
            Field charset = null;
            try {
                charset = Charset.class.getDeclaredField("defaultCharset");
            }
            catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        charset.setAccessible(true);
            try {
                charset.set(null, null);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
