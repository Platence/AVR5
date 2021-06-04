package all_controllers.logicAnalitic;

import decriptor.ConsoleAVR;
import error_package.Modal_Error;
import exportXLSX.exportNULL.ExportNullOstatku;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import warehouse_plu.Ostatku;
import warehouse_plu.RememberSkladPosition;

import java.util.ArrayList;
import java.util.LinkedList;

public class BundleForNegativeCount {

    public static ArrayList<Ostatku> nullnumberOstatku = new ArrayList<>();

    public static TitledPane getBundleFromNegative(){


        cheknullnumber();

        LinkedList<Label> labels = new LinkedList<>();
        FlowPane fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);

        ScrollPane sp = new ScrollPane();

        addLabelUpload(fp);                     // Добавление лейбла ( кнопки ) на выгрузку отчета


        if(nullnumberOstatku.isEmpty()){
            Label lb = new Label();
            lb.setText("Не обнаружены");
            labels.add(lb);
        }

        for(Label lll : labels){
            fp.getChildren().add(lll);
            fp.setOrientation(Orientation.VERTICAL);
        }

        sp.setContent(fp);

        TitledPane result = new TitledPane();
        result.setText("Экспорт в Excel ");
        result.setContent(sp);

        return result;
    }

    public static void cheknullnumber(){

        // Расчитать нулевые остатки

        nullnumberOstatku.clear();

        for(Ostatku ost : Main.classOstatku){
            double x1 = ost.getOddsSUM();

            double x2 = ost.getOddsCOUNT();


            if(x1==0&&x2>0.0){nullnumberOstatku.add(ost);}
            if(x1==0&&x2<0.0){nullnumberOstatku.add(ost);}

        }

        ConsoleAVR.printlnn(" ");
        ConsoleAVR.printlnn("NULL POINTER CHECK");
        ConsoleAVR.printlnn(" ");


    }

    public static void addLabelUpload(FlowPane fp){
        Label lbs = new Label("Нулевые цены экспорт -  в Excel");
        new CreateLabel_to_YU3().setMouse_entered_clicker(lbs);

        Label lb2 = new Label("Разница подсчетов ");
        new CreateLabel_to_YU3().setMouse_entered_clicker(lb2);

        Label oddsGroup = new Label("Разница по группам экспорт");
        new CreateLabel_to_YU3().setMouse_entered_clicker(oddsGroup);

        lbs.setOnMouseClicked (event -> new ExportNullOstatku().startUpload());
        lb2.setOnMouseClicked (event -> openWindowSettingsODDS(Main.stage_main_copy));
        oddsGroup.setOnMouseClicked(event -> new OddsFromTg().createOddsGroup());


        fp.getChildren().add(lbs);
        fp.getChildren().add(lb2);
        fp.getChildren().add(oddsGroup);
    }

    public static void openWindowSettingsODDS(final Stage stage){
        // открывает настройки листа ODDS

        // New window (Stage)
        FlowPane sl = new FlowPane();
        Scene secondScene = new Scene(sl, 330, 180);

        Stage newWindow = new Stage();
        newWindow.setTitle("Проверка разницы по сумме");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(stage);

        // Set position of second window, related to primary window.
        newWindow.setX(stage.getX() + 200);
        newWindow.setY(stage.getY() + 100);

        new AddPaneToOddsList().addToPane(sl);

        newWindow.show();

    }



}
