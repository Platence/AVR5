package parseNeprodam;

import all_controllers.Rule_contollers_Main;
import all_paths.Paths_Main_File;
import com.sun.javafx.css.Rule;
import error_package.Modal_Error;
import fin_kp_AI.AddPositionToTableAndList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import parser_xml.not_standart_parser.unziper.Process_UnZip;
import sample.Main;

import java.io.File;

public class StartNeprodamParse {

    public static String currentPathToNeprodam = "";
    public static String PATH_EXTRACT = "src/binn/neprodam/";
    public static String FINAL_PATH_NEPRODAM_SHEET1 = "src/binn/neprodam/xl/worksheets/sheet1.xml";
    public static int COUNT_ITERATION = 21;
    private static boolean init = false;

    public static void startUnzip(){

        if(Main.classOstatku.size()<1){new Modal_Error().set_erroe_messege("Сначала проведите раcчет Списка разниц");return;}

        File s = new File(Paths_Main_File.path_final_out+"/NPE.xlsx");
        currentPathToNeprodam = s.getPath();

        if(!s.getName().contains(".xlsx")){new Modal_Error().set_erroe_messege("Можно добавлять только XLSX файлы!");return;}

        if(s.exists()){
            if(new StartNeprodamParse().unzipFile(currentPathToNeprodam)) {
                new Modal_Error().set_erroe_messege("Файл был успешно добавлен" + System.lineSeparator()+"" +
                        "Обновлено " + ParseNeprod.countUpdateTMC + " позиций" + System.lineSeparator()+"" +
                        "Предупреждение : " +System.lineSeparator() + "" +
                        "В этом разделе, рассматриваются непродаваемые товары." + System.lineSeparator()+System.lineSeparator()+"" +
                        "1.Установите ползунок [Мак. кол-во PLU] в требуемое значение."+System.lineSeparator()+"" +
                        "2.Укажите макс.Qфин, и допустимые расхождения(по модулю, без знака)"+System.lineSeparator()+"" +
                        "3.Нажмите кнопку [добавить в акт]."+ System.lineSeparator() + System.lineSeparator()+"" +
                        "Рекомендуемые значения QPLU = 30 или 35 / QF =  5 или 7 / ODDS =  0 или 1 ");
                        setPositionToButton();
                s = null;
            }
        }
        else {new Modal_Error().set_erroe_messege("Файл NPE.xlsx не обнаружен в директории  " + System.lineSeparator()+"" +
                Paths_Main_File.path_final_out);}

    }

    public boolean unzipFile(String path){

        Process_UnZip.beginn(path,PATH_EXTRACT);
        boolean test =  testExist();
        if(!test){new Modal_Error().set_erroe_messege("Файл не обнаружен по пути " + FINAL_PATH_NEPRODAM_SHEET1);return false;}
        new ParseNeprod().startParseNeprod();

        return true;
    }


    public static boolean testExist(){
        File d = new File(FINAL_PATH_NEPRODAM_SHEET1);

        if(d.exists()){d=null;return true;}
        d = null;
        return false;
    }

    public static void loadPlatform(){
        Platform.runLater(() -> {
            setVisiblePriority();
        });
    }

    public static void setPositionToButton(){

       // Rule_contollers_Main.main_controller.add_neprodam_but.setLayoutX(695);
       // Rule_contollers_Main.main_controller.add_neprodam_but.setLayoutY(14);
        loadPlatform();

        if(!init) {
            setButtonNeprodamAddPosition();
            init = true;
        }
    }

    public static void setVisiblePriority(){

        setVisible(Rule_contollers_Main.main_controller.but_add_neprodam_ct);
        setVisible(Rule_contollers_Main.main_controller.field_min_q_neprodam);
        setVisible(Rule_contollers_Main.main_controller.field_max_odds_nepr);
        setVisible(Rule_contollers_Main.main_controller.qfInfoLab);
        setVisible(Rule_contollers_Main.main_controller.maxrash);
        setVisible(Rule_contollers_Main.main_controller.slider_neprodam);
        setVisible(Rule_contollers_Main.main_controller.label_info_neprodan_max_Count);
        Rule_contollers_Main.main_controller.slider_neprodam.setMax(60);
    }

    public static void setVisible(Node e){
        if(!e.isVisible()){e.setVisible(true);}
        //if(e.isVisible()){e.setVisible(false);}
    }

    public static void setButtonNeprodamAddPosition(){

        Rule_contollers_Main.main_controller.slider_neprodam.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) Rule_contollers_Main.main_controller.slider_neprodam.getValue();
                String text = String.valueOf(x);
                Rule_contollers_Main.main_controller.label_info_neprodan_max_Count.setText(text);
            }
        });




        Rule_contollers_Main.main_controller.but_add_neprodam_ct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                double x = Rule_contollers_Main.main_controller.slider_neprodam.getValue();
                int xx = (int) x;
                AddPositionToTableAndList.MAX_COUNT_NEPRODAM = xx;

                if(AddPositionToTableAndList.MAX_COUNT_NEPRODAM == 0){
                    new Modal_Error().set_erroe_messege("Установите значение на слайдере \"Мак.кол-во PLU\"" + System.lineSeparator()+"" +
                            "Текущее значение - " + AddPositionToTableAndList.MAX_COUNT_NEPRODAM);
                    return;
                }

                new AddPositionToTableAndList().addPosition("Непродам",
                        testParse(Rule_contollers_Main.main_controller.field_min_q_neprodam.getText()),
                                testParse(Rule_contollers_Main.main_controller.field_max_odds_nepr.getText()),true);
            }
        });

    }

    public static int testParse(String text){
        try{
            int x = Integer.parseInt(text);
            return x;
        }
        catch (Exception e){
            return 0;
        }
    }

}
