package graficAVR.scaleStepChoiceBox;

import all_controllers.logicAnalitic.GlobalRulesBlock;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sample.Main;
import warehouse_plu.Ostatku;

public class BundleTointerval {


    public static TextFlow getTextFrom0_to_step(int stepup, int stepout){

        int x = 0; // недостача
        int y = 0; // излишек
        int t = 0; // несчитано товара без КП
        int q = 0; // несчитано с учетом КП
        int qPLU = 0;
        for(Ostatku z : Main.classOstatku){

            if(!aimok(z,stepout,stepup)){continue;}

            if(!GlobalRulesBlock.getres(z)){continue;}
            if(z.getOddsSUM()==0){continue;}
            if(z.getOddsSUM()<0){

                x+=Math.abs(z.getOddsSUM());

                if(z.area_one_strok.contains("-")){

                    t+=Math.abs(z.getOddsSUM());

                    if(!z.getControlChek().equals("EMP")){
                        q+=Math.abs(z.getOddsSUM());
                    }
                }


            }

            if(z.getOddsSUM()>0){
                y+=Math.abs(z.getOddsSUM());
            }
            qPLU++;
        }

        int x1 = (int) (t*0.33);
        int x2 = (int) (x-x1);
        int x3 = (int) (x*0.33);
        int x4 = (int) (x2-x3-t*0.33);
        int x5 = (int) (x4-q);

        int y1 = x1;
        int y2 = y-y1;
        int y3 = (int) (y*0.22);
        int y4 = y2-y3;

        int kPD = y4-x5;


        Text text = new Text();
        String message = "В интервале от : " + stepup  + " до : " + stepout + System.lineSeparator()
                +"Недостача : " + x + System.lineSeparator()
                +"Излишек : " + y + System.lineSeparator()
                +"Товар без зон " + t + " (Нет КП)" + System.lineSeparator()
                +"Товар без зон " + q + " (Есть КП)" + System.lineSeparator();
                String mes = "КПД : " + kPD + " при (" + qPLU + " plu) " +  System.lineSeparator();

        Text text2 = new Text();
        text2.setText(mes);
        text2.setFill(Color.LAWNGREEN);

        text.setText(message);
        text.setFill(Color.AZURE);

        TextFlow ft = new TextFlow(text,text2);

        return ft;
    }

    public static Text calculate(){

        int x = 0; // недостача
        int y = 0; // излишек

        for(Ostatku z : Main.classOstatku){
            if(!GlobalRulesBlock.getres(z)){continue;}
            if(z.getOddsSUM()<0){x+=Math.abs(z.getOddsSUM());}
            if(z.getOddsSUM()>0){y+=Math.abs(z.getOddsSUM());}
        }

        System.out.println("Недостача : " + x);
        System.out.println("Излишек : " + y);

        Text t = new Text();

        String message = "Общая недостача : " + x + System.lineSeparator()
                +"Общий излишек : " + y + System.lineSeparator();

        t.setText(message);
        t.setFill(Color.WHITE);

        return t;
    }

    private static boolean aimok(Ostatku d, int start, int end){

        int res = (int) Math.abs(d.getOddsSUM());

        return (res > start) && (res < end);
    }
}
