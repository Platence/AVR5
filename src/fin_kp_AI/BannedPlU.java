package fin_kp_AI;

import error_package.Modal_Error;
import error_package.SlideError.SlideModalError;

import java.util.ArrayList;

public class BannedPlU {

    /*
    Удаленные PLU До просчета сверки разниц, получат
    запрет на добавление в акты
     */

    public static ArrayList<String> listPluBanned = new ArrayList<>();


    public static void clearList(){
        listPluBanned.clear();
    }


    public static void addPosition2(String s,String description,String reazon){

        listPluBanned.add(s);
        String message = "Временный запрет для PLU : "
                +System.lineSeparator()
                +System.lineSeparator()
                + s;

        new SlideModalError().setMessageWithComand(
                message,SlideModalError.comandAddButton,s,description,reazon);

    }

    public static boolean containsBanned(String s){
        // Забаненная плю не может быть добавлена!

        if(listPluBanned.contains(s)){return true;}
        return false;
    }
}
