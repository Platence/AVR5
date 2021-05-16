package all_controllers.mouse_event;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class All_event_from_mouse extends Task<Void> {

    public static ScrollPane pane_for_yu_tg = new ScrollPane();
    public static TitledPane Temp_pane_for_scroll = new TitledPane();
    public static Pane pane_test = new Pane();
    public static double count_current = 0.0;

    public void handle(TitledPane paneF){

        //Закрашиваем в желтый цвет, затем возвращаем обратно красный

        Temp_pane_for_scroll = paneF;
        String temp = "";
                try {

                    temp = paneF.getBorder().getStrokes().get(0).getBottomStroke().toString();
                }
                catch (Exception e){}

                if(temp!=null) {
                    if (temp.equals("0xffff00ff")) {
                        pane_test.setLayoutY(0.1);
                        System.out.println(paneF.getParent().getLayoutY());
                        paneF.setBorder(new Border(new BorderStroke(Color.rgb(28,32,34) , BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                        return;
                    }
                }
                System.out.println(paneF.getChildrenUnmodifiable().get(0).getLayoutY());

                pane_test.setLayoutY(0.9);


                paneF.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    }

    public void handle_scroll_analitic_tg(Accordion accordionYu2){

        System.out.println("Second Work");
        System.out.println(accordionYu2.getLayoutX() + " / " + accordionYu2.getLayoutY() + " SECOND");

    }

    @Override
    protected Void call() throws Exception {

        while (true){
            Thread.sleep(1000);
            set_count();
            break;
        }



        return null;
    }

    public double set_count(){
        return count_current;
    }


}
