package numberShopPack;

import all_controllers.Rule_contollers_Main;
import error_package.SlideError.SlideModalError;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class AddNewMagazine {


    private static TextField number;
    private static TextField sap;
    private static TextField adress;
    private static TextField yl;
    private static TextField index;

    public AddNewMagazine(String numberT){
        showDesk(numberT);
    }

    private void showDesk(String numberT){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setContent(createPaneToNewShop(numberT));
        alert.setTitle("Временно добавить магазин");
        ButtonType foo = new ButtonType("Закрыть", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().set(0,foo);
        alert.showAndWait();
    }

    private VBox createPaneToNewShop(String numberT){
        VBox b = new VBox();

        b.setPrefSize(400,230);

        number = new TextField();
        number.setText(numberT);

        sap    = new TextField();
        sap.setPromptText("SAP Номер");

        index  = new TextField();
        index.setPromptText("Индекс");

        adress = new TextField();
        adress.setPromptText("Город или(Область,Посёлок),Улица,дом №");

        yl  = new TextField();
        yl.setPromptText("Юр лицо (Например : Агроторг ООО ");

        Button complite = new Button("Принять / Добавить данные");
        complite.setStyle("-fx-border-radius : 10");

        complite.setOnAction(e->actionButton());

        b.getChildren().add(number);
        b.getChildren().add(sap);
        b.getChildren().add(adress);
        b.getChildren().add(index);
        b.getChildren().add(getLabelDesc());
        b.getChildren().add(complite);

        return b;
    }

    private void actionButton(){

        try {

            String art = number.getText() + "-Пятерочка";


            if(index.getText().length()<1){new SlideModalError().setMessage("Нет индекса");return;}
            if(adress.getText().length()<1){new SlideModalError().setMessage("Нет адреса");return;}
            if(sap.getText().length()<1){new SlideModalError().setMessage("Нет сап номера");return;}


            ShopDescription.tryAddnewPosition(sap.getText()
                    , art
                    , number.getText()
                    , "UNKNOWN"
                    , index.getText()
                    , adress.getText()
                    , yl.getText())
                    ;

            Rule_contollers_Main.main_controller.buttonAddNewMag.setVisible(false);
            Rule_contollers_Main.main_controller.number_shop.setText(number.getText());
            Rule_contollers_Main.main_controller.number_shop.setStyle("-fx-background-color:Green");
            new SlideModalError().setMessage("Магазин " + number.getText() + " добавлен.");
        }

        catch (Exception e){
            new SlideModalError().setMessage("Ошибка при создании нового маназина");
        }
    }

    private Text getLabelDesc(){
        Text label = new Text();
        String s1 = "Во избежании порчи базы данных магазинов";
        String s2 = System.lineSeparator();
        String s3 = "AVR удалит даннные по этому магазину";
        String s5 = System.lineSeparator();
        String s6 = "После закрытия программы";

        String res = s1+s2+s3+s5+s6;
        label.setText(res);
        label.setFill(Color.RED);

        return label;
    }


}
