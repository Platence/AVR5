package chekCrashPLU.Karantin;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import error_package.SlideError.SlideLiveTimer;
import error_package.SlideError.SlideModalError;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ControlClassKarantin {

    public static HashSet<KarantinPLU> listKarantinPlu = new HashSet<>();
    public static final String controlChar = "@";

    public static void addPosition(String plu,String desc,String reazon){
        if(plu.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак " + controlChar);return;}
        if(desc.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак "+ controlChar);return;}
        if(reazon.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак "+ controlChar);return;}

        listKarantinPlu.add(new KarantinPLU(plu,desc,reazon));
        saveListAndUpdateTable();
    }

    public static void createPosition(String plu,String desc,String reazon){
        if(plu.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак " + controlChar);return;}
        if(desc.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак "+ controlChar);return;}
        if(reazon.contains(controlChar)){new Modal_Error().set_erroe_messege("Нельзя использовать знак "+ controlChar);return;}

        listKarantinPlu.add(new KarantinPLU(plu,desc,reazon));
        saveListAndUpdateTable();
        new Modal_Error().set_erroe_messege("Вы навсегда запретили АКТАМ КП доступ к PLU "
                +System.lineSeparator()
                +plu
                +System.lineSeparator()
                +desc
                );
        SlideLiveTimer.counts+=150;
    }

    public static void saveListAndUpdateTable(){

        try(FileWriter wf = new FileWriter(Paths_Main_File.PATH_KARANTIN_PLU);){
            StringBuilder sb = new StringBuilder();
            for(KarantinPLU kar : listKarantinPlu){
                sb.append(kar.getPlu()+controlChar);
                sb.append(kar.getDesctiption()+controlChar);
                sb.append(kar.getReazon()+controlChar);
                sb.append(System.lineSeparator());
            }
            wf.write(sb.toString());
        } catch (IOException e) {
            new Modal_Error().set_erroe_messege("Ошибка записи ДЫРЯВЫХ PLU");
        }

        ControlTableKarantin.updateTable();


    }

    public static void loadListKarantinPLU(){
        try(Scanner sc = new Scanner(new File(Paths_Main_File.PATH_KARANTIN_PLU))){
            StringBuilder sss = new StringBuilder();
            while (sc.hasNextLine()){
                sss.append(sc.nextLine());
                descan(sss.toString());
                sss.setLength(0);
            }
        }
        catch (Exception e){
            System.out.println("Файл карантина отсутствует");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        ControlTableKarantin.initializateTableSkladKP();
    }

    public static void descan(String x){
        System.out.println(x);
        String[] obk = x.split(controlChar);
        addPosition(obk[0],obk[1],obk[2]);
    }

}
