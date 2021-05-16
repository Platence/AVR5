package all_controllers.logicAnalitic;

import graficAVR.ListXY;
import graficAVR.scaleStepChoiceBox.BundleTointerval;
import graficAVR.scaleStepChoiceBox.SetStandartScale;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import sample.Controller;
import sample.Main;
import warehouse_plu.Ostatku;

public class BundleResultGraph {

    public static Controller controller;
    private static boolean updwn = true;
    private static String textup = "Поднять подробную информацию";
    private static String textdwn = "Убрать подробную информацию";
    private static Button button = new Button(textup);

    public BundleResultGraph(int comand){

        if(comand==1){changepositionPane();}
        if(comand==2){setFillPane();}

    }

    public BundleResultGraph(Controller controller) {
        BundleResultGraph.controller = controller;
        setColor();
        setButtonAction();
    }

    public void setColor(){
        Platform.runLater(()->{controller.extendPaneGrapth.setStyle("-fx-background-color : transparent");});
    }

    private void setButtonAction(){
        button.setOnAction(e->controller.updownPane());
        button.setText(textup);
    }

    public void changepositionPane(){

        setFillPane();

        if(!updwn){up();return;}

        dwn();
    }

    private void up(){
        updwn = true;
        controller.extendPaneGrapth.setLayoutY(550);
        button.setText(textup);
    }

    private void dwn(){
        updwn = false;
        controller.extendPaneGrapth.setLayoutY(75);
        button.setText(textdwn);
    }


    private void setFillPane(){

        ListXY.sizeStep = SetStandartScale.getCurrentStep();
        int size = ListXY.sizeStep;
        int count = 2000/size;
        int sizeStart = 1;
        int sizeFuture = ListXY.sizeStep;

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(350,650);

        scrollPane.setLayoutY(25);

        TilePane tilePane = new TilePane();
        tilePane.setPrefSize(330,4000);



        for(int i = 0 ; i < count; i++){
            if(sizeStart>=2000){break;}
            tilePane.getChildren().add(BundleTointerval.getTextFrom0_to_step(sizeFuture,sizeStart));
            sizeStart+=size;
            sizeFuture+=size;
        }

        tilePane.setStyle("-fx-background-color:transparent");
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        scrollPane.setContent(tilePane);



        controller.extendPaneGrapth.getChildren().clear();
        controller.extendPaneGrapth.setVisible(false);
        Pane trash = new Pane();
        trash.setPrefSize(350,1000);
        trash.setStyle("-fx-background-color:Black");
        trash.setOpacity(0.80);
        controller.extendPaneGrapth.getChildren().add(trash);
        controller.extendPaneGrapth.getChildren().add(button);
        controller.extendPaneGrapth.getChildren().add(scrollPane);
        controller.extendPaneGrapth.setVisible(true);



    }


}
