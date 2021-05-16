package error_package;

import all_controllers.logicAnalitic.CreateLabel_to_YU3;
import graficAVR.ListOddsFromGropGR;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.LinkedList;

public class Controller_Error2 {

    public Accordion accordeon;

    public void initialize(){
         Modal_Error.controller_error2 = this;
    }

    public TitledPane annNewLabels(){

        LinkedList<Ostatku> temp = ListOddsFromGropGR.lastTempList;


        FlowPane fp = new FlowPane();
        fp.setOrientation(Orientation.VERTICAL);

        ScrollPane scr = new ScrollPane();
        scr.setContent(fp);

        for(Ostatku d : temp){
            Label label = new Label();
            label.setText(d.getPlu() + " [ " + d.getName() + " ]  :    " + d.getOddsSUM());

            // Установка на клик

            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String plu  = CreateLabel_to_YU3.get_name_regular(label.getText());

                    for(Ostatku ost : Main.classOstatku){
                        if(plu.equals(ost.getPlu())){
                            new Modal_Error().set_erroe_messege(ost.to_String_detail(ost));
                            break;
                        }
                    }
                    plu = null;
                }
            });

            //Визуал

            label.setOnMouseEntered(event -> new CreateLabel_to_YU3().setMouse_entered_clicker(label));
            label.setOnMouseExited(event -> new CreateLabel_to_YU3().unclicker(label));

            fp.getChildren().add(label);
            fp.setOrientation(Orientation.VERTICAL);

            // Добавим в общий лист


        }

        TitledPane tp =  new TitledPane();
        tp.setContent(scr);
        tp.setExpanded(true);

        return tp;

    }

    public void setNer(){
        accordeon.getPanes().add(annNewLabels());
        accordeon.getPanes().get(0).setExpanded(true);
    }

}
