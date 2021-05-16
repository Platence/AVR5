package graficAVR;

import all_controllers.Rule_contollers_Main;

public class ValueSlider {

    public static double minValue = 300;


    public void setValue(String name){
        minValue = 300;
        new GraficAvrProd().drawGrafic();
        //if(GraficAvrProd.currentGroup.equals("ALL")){new GraficAvrProd().drawGrafic();}
        //if(!GraficAvrProd.currentGroup.equals("ALL")){new GraficAvrProd().drawGrafic(GraficAvrProd.currentGroup);}
        //Rule_contollers_Main.main_controller.labelSliderGrath.setText(" 0 = " + (int)minValue + " ->");
    }
}
