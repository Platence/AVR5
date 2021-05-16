package peopleComisson;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import error_package.Modal_Error;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeopleComisson {

    public static String PEEK_MAN = "EMP";
    public static String MAN_PART_ONE = "EMP";
    public static String MAN_PART_TWO = "EMP";
    public static String MAN_PART_THREE = "EMP";
    public static String MAN_MOL        = "EMP";
    public static String PADEJ_SKRP = "EMP";
    public static String dol1 = "EMP";
    public static String dol2 = "EMP";
    public static String dol3 = "EMP";
    public static String dolMOL = "EMP";


    public void save(){

        try {
            String s = Rule_contollers_Main.main_controller.peek_field.getText();   // Пик
            String s1 = Rule_contollers_Main.main_controller.partOne.getText();     // Первый член комиссии
            String s2 = Rule_contollers_Main.main_controller.parttWo.getText();     // Второй член комиссии
            String s3 = Rule_contollers_Main.main_controller.partThree.getText();   // Третий член комиссии
            String s4 = Rule_contollers_Main.main_controller.tvoy_padej.getText();  // Фамилия в падеже

            String s5 = Rule_contollers_Main.main_controller.partMol.getText();    // МОЛ
            String d1 = Rule_contollers_Main.main_controller.d1.getText();         // Должность 1 чк
            String d2 = Rule_contollers_Main.main_controller.d2.getText();         // Должность 2
            String d3 = Rule_contollers_Main.main_controller.d3.getText();         // Должность 3
            String dMOL = Rule_contollers_Main.main_controller.dMOL.getText();       // Должность МОЛ

            PEEK_MAN = s;
            MAN_PART_ONE = s1;
            MAN_PART_TWO = s2;
            MAN_PART_THREE = s3;
            PADEJ_SKRP = s4;
            MAN_MOL = s5;
            dol1 = d1;
            dol2 = d2;
            dol3 = d3;
            dolMOL = dMOL;

            if(chekvoskZnak(s,s1,s2,s3,s4,s5,d1,d2,d3,dMOL)){new Modal_Error().set_erroe_messege("Нельзя использовать знак [!]");return;}

            StringBuilder sb = new StringBuilder();
            sb.append(s+"!"+System.lineSeparator());
            sb.append(s1+"!"+System.lineSeparator());
            sb.append(s2+"!"+System.lineSeparator());
            sb.append(s3+"!"+System.lineSeparator());
            sb.append(s4+"!"+System.lineSeparator());
            sb.append(s5+"!"+System.lineSeparator());
            sb.append(d1+"!"+System.lineSeparator());
            sb.append(d2+"!"+System.lineSeparator());
            sb.append(d3+"!"+System.lineSeparator());
            sb.append(dMOL+"!"+System.lineSeparator());

            try(FileWriter fw = new FileWriter(new File(Paths_Main_File.PATH_TO_PEOPLE_WORKER))){
                fw.write(sb.toString());
            }
            catch (Exception e){
                new Modal_Error().set_erroe_messege("Ошибка сохранения People " + e.getMessage());
            }

        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Ошибка присвоения имени членов коммисии!");
        }

    }

    public void loading(){

        try(FileReader fr = new FileReader(new File(Paths_Main_File.PATH_TO_PEOPLE_WORKER))){
            Scanner sc = new Scanner(fr);
            int count = 0;
            while (sc.hasNextLine()){
                // Regular match name

                if(count == 0){
                    PEEK_MAN = turnOffvosk(sc.nextLine());
                }
                if(count == 1){
                    MAN_PART_ONE = turnOffvosk(sc.nextLine());
                }
                if(count == 2){
                    MAN_PART_TWO = turnOffvosk(sc.nextLine());
                }
                if(count == 3){
                    MAN_PART_THREE = turnOffvosk(sc.nextLine());
                }

                if(count == 4){
                    PADEJ_SKRP = turnOffvosk(sc.nextLine());
                }

                if(count == 5){
                    MAN_MOL = turnOffvosk(sc.nextLine());
                }
                if(count == 6){
                    dol1 = turnOffvosk(sc.nextLine());
                }
                if(count == 7){
                    dol2 = turnOffvosk(sc.nextLine());
                }
                if(count == 8){
                    dol3 = turnOffvosk(sc.nextLine());
                }
                if(count == 9){
                    dolMOL = turnOffvosk(sc.nextLine());
                }


                count++;
            }

        }
        catch (Exception e){
            System.out.println("Error loading part commisions");
        }

        changeNames();

    }


    public static String turnOffvosk(String target){

        System.out.println(target);

        Pattern p = Pattern.compile("(.*?)!");
        Matcher m = p.matcher(target);

        while (m.find()){
            return m.group(1);
        }

        return "EMP";
    }


    public static void changeNames(){
        // Set loadings field
        Rule_contollers_Main.main_controller.peek_field.setText(PEEK_MAN);
        Rule_contollers_Main.main_controller.partOne.setText(MAN_PART_ONE);
        Rule_contollers_Main.main_controller.parttWo.setText(MAN_PART_TWO);
        Rule_contollers_Main.main_controller.partThree.setText(MAN_PART_THREE);
        Rule_contollers_Main.main_controller.tvoy_padej.setText(PADEJ_SKRP);

        Rule_contollers_Main.main_controller.d1.setText(dol1);
        Rule_contollers_Main.main_controller.d2.setText(dol2);
        Rule_contollers_Main.main_controller.d3.setText(dol3);
        Rule_contollers_Main.main_controller.dMOL.setText(dolMOL);
        Rule_contollers_Main.main_controller.partMol.setText(MAN_MOL);
    }

    public static boolean chekvoskZnak(String  ... a ){

        for(String z : a){
            if(z.contains("!")){return true;}
        }

        return false;
    }

}
