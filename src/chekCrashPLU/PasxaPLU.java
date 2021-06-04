package chekCrashPLU;

import all_paths.Paths_Main_File;
import decriptor.ConsoleAVR;
import error_package.Modal_Error;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasxaPLU {

    public static ArrayList<String> paskhaPLU = new ArrayList<>();

    // Лист для хранения семян пасхи

    static {
        try {
            FileReader fr = new FileReader(Paths_Main_File.PATH_TO_PASKHA);
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()){
                String s = sc.nextLine();
                s.replace(',',' ');
                s.trim();
                paskhaPLU.add(s);

            }
            ConsoleAVR.printlnn(paskhaPLU.toString());
            ConsoleAVR.printlnn("Семена-пасха(ИНИЦИИРОВАНО)");
            sc.close();
            fr.close();
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка загрузки семян пасхи" + e.getLocalizedMessage());
        }

    }


    public static boolean havePaskha (String req){

        if(paskhaPLU.contains(req)){return true;}
        return false;

    }



}
