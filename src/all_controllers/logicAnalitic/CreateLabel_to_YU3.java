package all_controllers.logicAnalitic;

import error_package.Modal_Error;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateLabel_to_YU3 {


    public static int minimal_sum = 300;  // Минимальный порог


    public static ArrayList<String> give_me_yu3_LEVEL_4(String group_name_level_3){

        /*
             На вход подается УИ3, мы возвращаем список
             Всех причастных к этой группе данных.
         */

        ArrayList<String> word_and_sum = new ArrayList<>();  // TEMP

        for(Ostatku target : Main.classOstatku){

            if(Math.abs(target.oddsSUM)<minimal_sum){continue;}
            if(target.getYu3().equals(group_name_level_3)){
                word_and_sum.add(target.plu + " " + target.getName()+ "->[ " + target.oddsSUM  + " ]");
            }
        }

        return word_and_sum;

    }

    public void create_flow_pane_extended_LEVEL_FOR(TitledPane tlp, String name_group){

        ArrayList<String> workList = give_me_yu3_LEVEL_4(name_group);

        FlowPane fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);

        for(String x : workList){

            Label label = new Label(x);                                       // Заполняем всё обычными Label
            label.setOnMouseClicked(event -> listner_for_label_tmc(label));   // Добавление полного описания
            label.setOnMouseEntered(event -> setMouse_entered_clicker(label)); // Добавление курсора
            label.setOnMouseExited(event -> unclicker(label));


            label.setTextFill(Color.YELLOW);

            fp.getChildren().add(label);
        }
        tlp.setContent(fp);

    }

    public void listner_for_label_tmc(Label l){

        // У каждого лейбла будет слушатель
        // Который вернет детальную статистику
        // о товаре

        String temp = " " ;
        try{temp = l.getText();}catch (Exception e){ System.out.println(e.getMessage());}


        temp = get_name_regular(temp);
        String temp_detail = "";

        for(Ostatku xxx : Main.classOstatku){

            if(xxx.plu.equals(temp)){                     //Сравнение по PLU
                temp_detail = xxx.to_String_detail(xxx);
                break;
            }
        }

        new Modal_Error().set_erroe_messege(temp_detail);  // Вызов окна с результатами

    }

    public static String get_name_regular(String target){

        /*
           Метод возвращает полное первое число, т.е PLU
           На вход подаётся Label из разницы по группам

           Получить PLU из лейбла
         */

        if(target==null){return "";}

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(target);

        while (m.find()){
            return m.group();
        }

        return "";
    }

    public void setMouse_entered_clicker(Label l){

        // Добавим курсор при наведении
        l.setCursor(Cursor.CLOSED_HAND);
        l.setTextFill(Color.GREY);
        l.setEffect(new DropShadow(3,Color.BLUE));

    }

    public void unclicker(Label l){
        // Добавим курсор при наведении
        l.setCursor(Cursor.DEFAULT);
        l.setTextFill(Color.YELLOW);


    }

}
