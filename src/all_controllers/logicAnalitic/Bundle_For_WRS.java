package all_controllers.logicAnalitic;

import all_paths.Paths_Main_File;
import autoOpen.AutoOpen;
import chekCrashPLU.IgnoredPlu;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import mailMessage.ConsolidationInformation;
import megaBlock.MegaBlockYU2;
import warehouse_plu.Ostatku;
import java.io.*;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bundle_For_WRS {

    public static int was_press_plu = 0;  //Считано PLU
    public static int sub_zero_plu = 0;  // Кол-во меньше 0 PLU
    public static double result_PU = 0;   //Результат ПИ
    public static int was_in_KP = 0 ; //Кол-во внесенных в КП PLU
    public static int from_300_RU = 0; //от 300 рублей PLU +-
    public static int non_count_summ = 0; //Несчитанного товара
    public static int non_in_hall_plu = 0; //не выставлено в зал PLu
    public static int vsego_plu = 0;       // Всего PLU
    public static int rashojdenyu = 0;    // Расхождений шт
    public static int vbito_pl = 0;      // Внесено в КП
    public static int sigarets = 0;     // сигареты общая
    public static int cigarettesSURPLUS = 0;
    public static int cigarettesShortage = 0;


    public Bundle_For_WRS(){
        set_null_for_all_field_WRS();
    }



    public static void set_null_for_all_field_WRS(){
        was_in_KP = 0;
        sub_zero_plu = 0;
        result_PU = 0;
        from_300_RU = 0;
        non_count_summ = 0;
        non_in_hall_plu = 0;
        was_press_plu = 0;
        vsego_plu = 0;
        rashojdenyu = 0;
        vbito_pl = 0;
        sigarets = 0;
        cigarettesSURPLUS = 0;
        cigarettesShortage = 0;
    }

    public static void start_lit(Ostatku target){

        //Обрабатываем каждый объект

        if(!IgnoredPlu.chekFromWRS(target.getPlu())){
            System.out.println(target.getPlu() + " не допущена к проверке");return;
        }

        setWas_press_plu(target);
        setFrom_300_RU(target);
        get_zal(target);
        set_sub_zero(target);
        set_rasxojdenyua(target);
        vbito_kp(target);
        get_non_count_Summ(target);
        set_result_sum_fin(target);
        setSigarets(target);

    }

    public static void setWas_press_plu(Ostatku ddd){

        vsego_plu++;

        if(ddd.qfinal!=0){
            was_press_plu++;
            return;
        }
        if(!ddd.controlChek.equals("EMP")){
            was_press_plu++;
            return;
        }
    }

    public static void setSigarets(Ostatku ddd){

       if(ddd.getYu2().contains("бачные изделия и аксессуары (Foo")){
           if(ddd.oddsSUM>0){cigarettesSURPLUS+=ddd.getOddsSUM();}
           if(ddd.oddsSUM<0){cigarettesShortage+=ddd.getOddsSUM();}
           sigarets+=ddd.oddsSUM;
       }
    }

    public static void setFrom_300_RU(Ostatku ddd){
        int x = (int) Math.abs(ddd.oddsSUM);
        if(x>=300){from_300_RU++;}
    }

    public static void get_zal(Ostatku ddd){

        //if(ddd.yu3.contains("обственное произв")){return;}
        if(MegaBlockYU2.chekPositionToMainRulesYU2(ddd)){return;}

        String x = ddd.area_one_strok;

        if(x.contains("777")){return;}
        if(x.contains("888")){return;}

        if(!Enable_zone.permissible_group(ddd.getYu2())){return;}


        Pattern p = Pattern.compile("[A-Z]+");
        Matcher m = p.matcher(x);

        TreeSet<String> kops = new TreeSet<>();

        while (m.find()){
            kops.add(m.group());
        }

        if(kops.contains("S")){
            if(kops.size()<=1){
                non_in_hall_plu++;
                return;
            }

        }

    }

    public static void get_non_count_Summ(Ostatku ddd){

        if(ddd.getYu3().contains("обственное прои")){return;}

        if(ddd.qfinal==0){
            if(ddd.controlChek.equals("EMP")){
                non_count_summ+=ddd.oddsSUM;
            }
        }
    }

    public static void vbito_kp(Ostatku ddd){
        if(!ddd.controlChek.equals("EMP")){vbito_pl++;}
    }

    public static void set_sub_zero(Ostatku ddd){
        if(ddd.qychetnoe<0){sub_zero_plu++;}
    }

    public static void set_rasxojdenyua(Ostatku ddd){
        if(Math.abs(ddd.oddsCOUNT)>0){rashojdenyu++;}
    }

    public static void set_result_sum_fin(Ostatku ddd){
        if(ddd.getYu3().contains("обственное произв")){return;}
        if(ddd.getYu3().contains("фабрикаты для пекар")){return;}

        //System.out.println("Подсчет Объекта : " + ddd.getName() + ddd.getOddsSUM());
        //System.out.println(result_PU);

        result_PU+=ddd.oddsSUM;
    }

    public static TitledPane get_bundle_WRS_Pane(){

        FlowPane fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);

        Label current_res = new Label();
        current_res.setText("Результат ПИ  :         " + resultOnString() + " руб. ");

        Label sigarets_info = new Label();
        sigarets_info.setText("Сигареты   :             " + sigarets + " руб." +
                " [ " + cigarettesShortage + " недостача ]" + " [ " + cigarettesSURPLUS + "  Излишек ]");

        Label correct_Control = new Label();
        correct_Control.setText("Считано PLU :          " + (was_press_plu) +" шт." + " Не / " + (vsego_plu - was_press_plu));

        Label rasxojd_label = new Label();
        rasxojd_label.setText("Расхождений  :        " + rashojdenyu + " По кол-ву(Не руб.)");

        Label sub_zero_label = new Label();
        sub_zero_label.setText("Qу. < 0                :     " + sub_zero_plu + " шт.");


        Label ne_v_zale = new Label();
        ne_v_zale.setText("Только склад  :        " + Ostatku.count_sklad_plu +" " );

        Label ot_300 = new Label();
        ot_300.setText("От 300р. PLU    :        " + from_300_RU + " шт.");

        Label control_EMP = new Label();
        control_EMP.setText("КОРРЕКТИРОВОК :   " +vbito_pl + " шт.");

        Label ostalos_Summ = new Label();
        ostalos_Summ.setWrapText(true);
        ostalos_Summ.setText("Не посчит. в руб. :   " +non_count_summ + " руб." + System.lineSeparator());

        fp.getChildren().add(current_res);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(sigarets_info);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(correct_Control);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(rasxojd_label);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(sub_zero_label);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(ne_v_zale);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(ot_300);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(control_EMP);
        fp.getChildren().add(create_empty_label());

        fp.getChildren().add(ostalos_Summ);



        TitledPane result = new TitledPane();
        result.setText("Общие данные / WRS");

        Button buttonreport = new Button("Создать письмо");
            buttonreport.setOnMouseEntered(e -> buttonreport.setCursor(Cursor.HAND));
            buttonreport.setOnAction(event -> uploadinf());
            fp.getChildren().add(buttonreport);


        result.setContent(fp);



        return result;
    }

    public static Label create_empty_label(){
        return new Label();
    }

    public static String resultOnString(){
        String result = String.format("%.0f",result_PU);
        if(result_PU>0){return "+"+result+",00р.";}
        return result+",00"+"р.";
    }

    public static String resultOnSmoke(){
        if(sigarets>0){return "+"+sigarets+"р.";}
        return (sigarets)+"р.";
    }



    public static void uploadinf(){
        /*
        Отправка письма
        Открытие разницы по группам
        Итоговый отчет
         */

        try(FileWriter fw = new FileWriter(Paths_Main_File.path_final_out+"/test.txt")){
            fw.write(addResult());
            new AutoOpen(Paths_Main_File.path_final_out+"/test.txt");

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        //Platform.runLater(()->{new ConsolidationInformation().composeEmail();});
        Platform.runLater(()-> new OddsFromTg().createOddsGroup());
    }
    public static String addResult(){
        StringBuilder sb = new StringBuilder();
        sb.append("Результат ПИ         " + resultOnString() + " руб. ");
        sb.append(System.lineSeparator());
        sb.append("Сигареты             " + sigarets + " руб." +
                " [ " + cigarettesShortage + " недостача ]" + " [ " + cigarettesSURPLUS + "  Излишек ]");
        sb.append(System.lineSeparator());
        sb.append("Считано PLU          " + String.valueOf(was_press_plu) +" шт." + " Не / " + (vsego_plu - was_press_plu));
        sb.append(System.lineSeparator());
        sb.append("Расхождений          " + rashojdenyu + " По кол-ву(Не руб.)");
        sb.append(System.lineSeparator());
        sb.append("От 300р. PLU         " + from_300_RU + " шт.");
        sb.append(System.lineSeparator());
        sb.append("КОРРЕКТИРОВОК        " +vbito_pl + " шт.");
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        sb.append(new ConsolidationInformation().getFullInformationString());

        return sb.toString();
    }

    public static String getresultformail(){
        if(result_PU<=0){return "Недостача  " + resultOnString() + " руб.";}
        return "Излишек  " + resultOnString() + " руб.";
    }

    }

