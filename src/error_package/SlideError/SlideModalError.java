package error_package.SlideError;

import all_controllers.Rule_contollers_Main;
import chekCrashPLU.Karantin.ControlClassKarantin;
import javafx.animation.TranslateTransition;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class SlideModalError {

    private static TranslateTransition showStatus;
    public volatile static TranslateTransition hideStatus;
    public volatile static boolean showsStatus = false;

    public static final String comandAddButton = "TIMEBAN";

    public static String tempPLU = "";
    public static String tempdesc = "";
    public static String tempreazon = "";


    public void startDefPostition(){
        Rule_contollers_Main.main_controller.flow_Message.setLayoutX(2000);
        Rule_contollers_Main.main_controller.flow_Message.setLayoutY(5);
        Rule_contollers_Main.main_controller.flow_Message.setPrefSize(440,125);
    }


    public void defultXYFlowPane(){

        startDefPostition();

        showStatus = new TranslateTransition(Duration.millis(360), Rule_contollers_Main.main_controller.flow_Message);
        showStatus.setByX(-1130.0);
        showStatus.setOnFinished(event -> showsStatus = true);
        hideStatus = new TranslateTransition(Duration.millis(10), Rule_contollers_Main.main_controller.flow_Message);
        hideStatus.setByX(1130.0);
        hideStatus.setOnFinished(event -> startDefPostition());

    }

    public void setMessage(String message){

        Label label = new Label();
        label.setWrapText(true);
        label.setLayoutX(25);
        label.setLayoutY(20);

        label.setFont(Font.font("FreeMono", 14));
        label.setTextFill(Color.web("#fffb03"));
        label.setText(message);

        Rule_contollers_Main.main_controller.flow_Message.setPrefSize(440,(55+message.length()/2));

        Rule_contollers_Main.main_controller.flow_Message.getChildren().clear();


        Rule_contollers_Main.main_controller.flow_Message.getChildren().add(label);
        if(showsStatus){return;}

        if(Rule_contollers_Main.main_controller.flow_Message.getLayoutX()<700){
            Rule_contollers_Main.main_controller.flow_Message.setLayoutX(2000);
        }

        showStatus.play();
        showsStatus = true;

        SlideLiveTimer slt = new SlideLiveTimer();
        Thread thread = new Thread(slt);
        thread.setDaemon(true);
        thread.start();
    }

    public void setMessageWithComand(String message,String comand,String plu,
                                     String description,String reazon){

        Label label = new Label();
        label.setWrapText(true);
        label.setLayoutX(25);
        label.setLayoutY(20);

        label.setFont(Font.font("FreeMono", 14));
        label.setTextFill(Color.web("#fffb03"));
        label.setText(message);

        tempPLU = plu;tempdesc = description; tempreazon = reazon;



        Rule_contollers_Main.main_controller.flow_Message.setPrefSize(440,(100+message.length()/2));

        Rule_contollers_Main.main_controller.flow_Message.getChildren().clear();

        Rule_contollers_Main.main_controller.flow_Message.getChildren().add(label);

        if (comand.equals(comandAddButton)){
            Button button = new Button("Забанить навсегда");
            Rule_contollers_Main.main_controller.flow_Message.getChildren().add(button);
            Rule_contollers_Main.main_controller.flow_Message.setOrientation(Orientation.VERTICAL);
            button.setOnMouseClicked(e-> ControlClassKarantin.createPosition(tempPLU,tempdesc,tempreazon));
        }

        if(showsStatus){;return;}

        showStatus.play();
        showsStatus = true;

        SlideLiveTimer slt = new SlideLiveTimer();
        Thread thread = new Thread(slt);
        thread.setDaemon(true);
        thread.start();
    }

}
