package chekCrashPLU;

import error_package.Modal_Error;
import javafx.scene.control.CheckBox;

import java.io.*;
import java.util.ArrayList;

public class IgnoredPlu {

    private CheckBox boxIgnoreFull;
    private CheckBox boxIgnoreWrs;

    private String pli;
    private String desc;

    public static ArrayList<IgnoredPlu> listIgnoredPly = new ArrayList<>();
    public static String pathToLoadFole = "src/binn/baseFile/ignoredFile";

    public IgnoredPlu(String pli, String desc,CheckBox boxIgnoreFull, CheckBox boxIgnoreWrs) {
        this.boxIgnoreFull = boxIgnoreFull;
        this.boxIgnoreWrs = boxIgnoreWrs;
        this.pli = pli;
        this.desc = desc;
    }



    public static void saveCurrentList(String ... a){

       if(a.length>2){ addNewPosition(a);}

        File file = new File(pathToLoadFole);

        createFile(file);

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for(IgnoredPlu part : listIgnoredPly){

            String s = part.getPli()+"!"+part.getDesc()+"!";

            if(part.getBoxIgnoreFull().isSelected()){s+="0"+"!";}
              if(!part.getBoxIgnoreFull().isSelected()){s+="1"+"!";}

            if(part.getBoxIgnoreWrs().isSelected()){s+="0"+System.lineSeparator();}
              if(!part.getBoxIgnoreWrs().isSelected()){s+="1"+System.lineSeparator();}
              sb.append(s);
        }

        try {
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

    public static void loadOldList(){

        File file = new File(pathToLoadFole);
        createFile(file); // Попытка создать файл, если его нет

        BufferedReader bis = null;
        try {
            bis = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                if (!bis.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            String temp = null;

            try {
                temp = bis.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


            String mas  [] = temp.split("!");

            addNewPosition(mas);

            // Разделяем по ! и отслыаем массив на обработку
            // 0 - это истина
            // 1 - это ложь
        }

        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void createFile(File file ){
        if(!file.exists()){
            try {
                boolean x =  file.createNewFile();
            } catch (IOException e) {
                new Modal_Error().set_erroe_messege("Ошибка при создании нового файла IGNORED");
            }
        }
    }

    public static void addNewPosition(String ... a){
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        CheckBox boxFull = new CheckBox();
        CheckBox boxWRS = new CheckBox();

        try {
             s1 = a[0]; if (s1.contains("!")){new Modal_Error().set_erroe_messege("Запрещено использовать !");return;}
             s2 = a[1]; if (s2.contains("!")){new Modal_Error().set_erroe_messege("Запрещено использовать !");return;}
             s3 = a[2];
             s4 = a[3];
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Возникла ошибка при обработке индексов. Входящий текст не соответствует параметрам.");
            return;
        }
        if(s3.equals("0")){boxFull.setSelected(true);}
        if(s4.equals("0")){boxWRS.setSelected(true);}
        listIgnoredPly.add(new IgnoredPlu(s1,s2,boxFull,boxWRS));

    }

    public CheckBox getBoxIgnoreFull() {
        return boxIgnoreFull;
    }

    public void setBoxIgnoreFull(CheckBox boxIgnoreFull) {
        this.boxIgnoreFull = boxIgnoreFull;
    }

    public CheckBox getBoxIgnoreWrs() {
        return boxIgnoreWrs;
    }

    public void setBoxIgnoreWrs(CheckBox boxIgnoreWrs) {
        this.boxIgnoreWrs = boxIgnoreWrs;
    }

    public String getPli() {
        return pli;
    }

    public void setPli(String pli) {
        this.pli = pli;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void updateField(String old,String n){

        for(IgnoredPlu ggg : listIgnoredPly){
            if(old.equals(ggg.getPli())){
                ggg.setPli(n);
                return;
            }
        }

        for(IgnoredPlu ggg : listIgnoredPly){
            if(old.equals(ggg.getDesc())){
                ggg.setDesc(n);
                return;
            }
        }



    }

    public static void deletePosition(String plu){
        for (IgnoredPlu fff : listIgnoredPly){
            if(plu.equals(fff.getPli())){
                listIgnoredPly.remove(fff);return;
            }
        }
    }

    public static boolean checkFromTotal(String plu){

        /**
         *  Возвращает ложь, если стоит галочка на тотальный игнор
         */


        for(IgnoredPlu ggg : listIgnoredPly){
            if(plu.equals(ggg.getPli()))
            {
                if(ggg.getBoxIgnoreFull().isSelected()){return false;}
            }
        }

        return true;
    }

    public static boolean chekFromWRS(String plu){

        /**
         *  Возвращает ложь, если стоит галочка на игноре WRS,Акты
         */

        for(IgnoredPlu ggg : listIgnoredPly){
            if(plu.equals(ggg.getPli()))
            {
                if(ggg.getBoxIgnoreWrs().isSelected()){
                    System.out.println("Нельзя добавить в акт " + plu);
                    return false;
                }
            }
        }

        return true;
    }
}
