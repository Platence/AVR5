package sklad_KP_AI;

import error_package.SlideError.SlideModalError;
import sample.Main;
import warehouse_plu.Ostatku;

public class CheckZonHall {

    private static int count = 2;


    public CheckZonHall() {
        count--;
    }

    public static boolean getcheck(){

        if(count<=0){
            new SlideModalError().setMessage("Проверка сладского акта на зоны"+System.lineSeparator()+"" +
                    "временно отключена пользователем.");
            return false;
        }
        CheckZonHall checkZonHall = new CheckZonHall();

        return checkZonHall.formate();

        // Возвращает TRUE если ошибка
    }


    private boolean formate(){
        // Возвращает FALSE если всё хорошо.
        for(String plu : Container_KP_Sklad.list_kp_sklad){
            for(Ostatku d : Main.classOstatku){
                if(plu.equals(d.getPlu())){
                    String area = d.getArea_one_strok();
                    if(containsHallArea(area)){return true;}
                    break;
                }
            }
        }
        return false;
    }

    private boolean containsHallArea(String cr){
        // Возвращает FALSE если всё хорошо.
        if(cr.contains("A")){return true;}
        if(cr.contains("B")){return true;}
        if(cr.contains("C")){return true;}
        if(cr.contains("D")){return true;}
        if(cr.contains("Q")){return true;}
        if(cr.contains("Z")){return true;}
        if(cr.contains("H")){return true;}
        if(cr.contains("R")){return true;}
        if(cr.contains("W")){return true;}
        if(cr.contains("F")){return true;}
        if(cr.contains("V")){return true;}
        if(cr.contains("G")){return true;}
        if(cr.contains("999")){return true;}
        if(cr.contains("-")){return true;}
        return false;
    }

    public static String getMessageError(){

        int tempcount = count+1;

        return "Внимание, вы пытаетесь выгрузить акт "+
                System.lineSeparator()+
                "в котором есть подсчеты из зала"+
                System.lineSeparator()+
                "Чтобы сбросить это сообщение, попробуйте еще"+
                System.lineSeparator()+
                tempcount +" раз";
    }
}
