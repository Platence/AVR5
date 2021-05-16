package PieAVR;

import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.FlowPane;
import sample.Main;

import java.util.LinkedList;
import java.util.Map;

public class CretePane {

    public static boolean shOrSO = false;


    public void drawGrathiks(TaskPie pieC){
        if (Main.classOstatku.size()<1){new Modal_Error().set_erroe_messege("Сначала расчитайте список разниц!");
            return;
        }
        Rule_contollers_Main.main_controller.flowPanePie.getChildren().add(getFlowpaneGrafic(pieC));

    }
    public FlowPane getFlowpaneGrafic(TaskPie pieC){

        PieChart pieChart = new PieChart();
        LinkedList<PieChart.Data> listPie = new LinkedList<>();
        System.out.println(shOrSO);

        if(shOrSO){
            for (Map.Entry<String,Double> sss : pieC.getListYU2Surplus().entrySet()){
                double val = sss.getValue(); int vaal = (int) val;
                listPie.add(new PieChart.Data(sss.getKey(),vaal));
            }
        }

         if(!shOrSO) {
            for (Map.Entry<String, Double> sss : pieC.getListYU2Shortage().entrySet()) {
                double val = sss.getValue();int vaal = Math.abs((int) val);
                listPie.add(new PieChart.Data(sss.getKey(), vaal));
            }
        }



        for(PieChart.Data zx : listPie ){
            pieChart.getData().add(zx);
        }
        pieChart.setLegendVisible(false);
        pieChart.setMinSize(800,750);

        FlowPane box = new FlowPane();
        box.getChildren().add(pieChart);
        return box;

    }
}
