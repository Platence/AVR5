package graficAVR;


import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;

import error_package.SlideError.SlideModalError;
import graficAVR.scaleStepChoiceBox.SetStandartScale;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Main;

public class GraficAvrProd {

    public static String currentGroup = "ALL";
    public static int stepMax = 0;
    public static int stepMin = 0;


    private static StringBuilder secondInterval = new StringBuilder();


    public void drawGrafic(){

        if (Main.classOstatku.size()<1){
            new Modal_Error().set_erroe_messege("Сначала расчитайте список разниц!");
            return;
        }

        Rule_contollers_Main.main_controller.flowPaneGrafics.getChildren().clear();
        Rule_contollers_Main.main_controller.flowPaneGrafics.getChildren().add(getFlowpaneGrafic());

    }

    public static int chekEternal(String s){
        int step = 0;
        if(s.length()<2){step = Integer.MAX_VALUE;}
        else
            try{
            int x = Integer.parseInt(s);
                step = x;
            }

            catch (Exception e){
                step = Integer.MAX_VALUE;
                new SlideModalError().setMessage("Ошибка преобразования в число");
        }
            return step;
    }

    public FlowPane getFlowpaneGrafic(){

        /**
         * Заполнение панели графиком
         */

        ListXY.sizeStep = SetStandartScale.getCurrentStep();
        //Назначение шага

        stepMin = chekEternal(Rule_contollers_Main.main_controller.fieldNan.getText());
        stepMax = chekEternal(Rule_contollers_Main.main_controller.fieldNan1.getText());

        if((ListXY.sizeStep>=stepMax)){new SlideModalError().setMessage("Шаг не может превышать максимальный интервал ДО");return null;}
        //if((ListXY.sizeStep>stepMin)){new SlideModalError().setMessage("Шаг не может быть больше мин.интервала");return null;}

        if(Math.abs(stepMin)<100){new SlideModalError().setMessage("Минимальный порог - 100 руб.");return null;}
        if(Math.abs(stepMin)>Math.abs(stepMax)){new SlideModalError().setMessage("Максимальный порог должен быть больше");return null;}
        if((stepMin < 0) || stepMax < 0){new SlideModalError().setMessage("Отрицательные значения недопустимы");return null;}

        ListOddsFromGropGR.list.clear();

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("(Интервал от (Сумма))");


        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("RUR");


        AreaChart stackedAreaChart = new AreaChart(xAxis, yAxis);
        stackedAreaChart.setMinSize(1150,515);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Недостача");



        for(XYChart.Data<Integer,Integer> data : ListXY.getShortage()){
            dataSeries2.getData().add(data);
            data.setNode(getNodeSfromSortage(data.getXValue(),data.getYValue()));
        }
        stackedAreaChart.getData().add(dataSeries2);


        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Излишек");

        for(XYChart.Data<Integer,Integer> data : ListXY.getSurplus()){
            dataSeries1.getData().add(data);
            data.setNode(getNodeSfromsurplus(data.getXValue(),data.getYValue()));

        }

        stackedAreaChart.getData().add(dataSeries1);
        stackedAreaChart.setCreateSymbols(true);



        FlowPane box = new FlowPane();
        box.getChildren().add(stackedAreaChart);
        return box;
    }

    public FlowPane getFlowpaneGrafic(String nameGroup){

        /**
         * Заполнение панели графиком
         */

        ListOddsFromGropGR.list.clear();

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("(Интервал от (Сумма))");


        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("PLU");


        AreaChart stackedAreaChart = new AreaChart(xAxis, yAxis);
        stackedAreaChart.setMinSize(1000,450);

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Недостача");

        for(XYChart.Data<Integer,Integer> data : ListXY.getShortage(nameGroup)){
            dataSeries2.getData().add(data);
            data.setNode(getNodeSfromSortage(data.getXValue(),data.getYValue()));
        }
        stackedAreaChart.getData().add(dataSeries2);


        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Излишек");


        for(XYChart.Data<Integer,Integer> data : ListXY.getSurplus(nameGroup)){
            dataSeries1.getData().add(data);
            data.setNode(getNodeSfromsurplus(data.getXValue(),data.getYValue()));


        }
        stackedAreaChart.getData().add(dataSeries1);

        FlowPane box = new FlowPane();
        box.getChildren().add(stackedAreaChart);
        return box;
    }



    public Node getNodeSfromsurplus(int x , int y){

        /**
         *  Поместим Тул тип на каждый пункт
         */

        //StringBuilder sb = new StringBuilder();
        secondInterval.setLength(0);
        secondInterval.append(Math.abs(x)==2000 ? "+100500" : String.valueOf((x+ListXY.sizeStep)));

        if(y==0){return null;}

//        sb.append("Сумма RUR ~  : " + y  );
//        sb.append(System.lineSeparator());
//        sb.append("Интерввл RUR : " + x  + " - " + secondInterval);
//        sb.append(System.lineSeparator());


        AnchorPane stack = new AnchorPane();
        stack.setMinSize(2,2);
        stack.setMaxSize(2,2);

        int firstInter = 0;
        try { firstInter = Integer.parseInt(secondInterval.toString()); }
        catch (Exception e){ System.out.println(e.getMessage()); }

        ListOddsFromGropGR.addNewPositionSurplus(stack,firstInter,x);     // addpoSitionToList

        //sb.setLength(0);
        Text text = new Text(String.valueOf(y));
        text.setFill(Color.YELLOW);

        text.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                text.setCursor(Cursor.HAND);
            }
        });


        Pane ppp = new Pane();
        ppp.setPrefSize(3,3);
        ppp.setLayoutX(20);
        ppp.setLayoutY(8);
        ppp.setStyle("-fx-background-color:yellow");

        stack.getChildren().add(ppp);
        stack.getChildren().add(text);

        stack.setStyle("-fx-background-color : transparent");

        return stack;
    }

    public Node getNodeSfromSortage(int x , int y){


        /**
         *  Поместим Тул тип на каждый пункт
         */

        if(y==0){return null;}

        //StringBuilder sb = new StringBuilder();
        secondInterval.setLength(0);
        secondInterval.append(Math.abs(x)==2000 ? "-100500" : String.valueOf((x-ListXY.sizeStep)));


//        sb.append("Сумма RUR ~ : " + y  );
//        sb.append(System.lineSeparator());
//        sb.append("Интерввл RUR : " + x  + " ( -> ) " + secondInterval);
//        sb.append(System.lineSeparator());


        AnchorPane stack = new AnchorPane();
        stack.setMinSize(2,2);
        stack.setMaxSize(3,3);

        int firstInter = 0;
        try { firstInter = Integer.parseInt(secondInterval.toString()); }
        catch (Exception e){ System.out.println(e.getMessage()); }

        ListOddsFromGropGR.addNewPosition(stack,firstInter,x);     // addpoSitionToList

        //sb.setLength(0);

        stack.setStyle("-fx-background-color : transparent");

        Text text = new Text(String.valueOf(y));
        text.setFill(Color.AZURE);

        text.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                text.setCursor(Cursor.HAND);
            }
        });

        Pane ppp = new Pane();
        ppp.setPrefSize(3,3);
        ppp.setLayoutX(20);
        ppp.setLayoutY(8);
        ppp.setStyle("-fx-background-color:red");

        stack.getChildren().add(ppp);
        stack.getChildren().add(text);

        return stack;
    }


}
