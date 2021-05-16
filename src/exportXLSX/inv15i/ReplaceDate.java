package exportXLSX.inv15i;

import all_controllers.Rule_contollers_Main;
import error_package.Modal_Error;

public class ReplaceDate {


    public static String restart_date_month(String alert){

        if(!Rule_contollers_Main.main_controller.month_to_words.isSelected()){return alert;}

        System.out.println(alert);

        String day = "";
        String month = "";
        String year = "";

        //20.10.2019 naprimer

        for(int i = 0 ; i < alert.length(); i++){

            if(i<2){       day+=alert.charAt(i); }
            if(i>=3&&i<5){ month+=alert.charAt(i);  }
            if(i>5) {      year+=alert.charAt(i); }
        }

        // Parse month

        int month_int = 0;
        int day_int = 0;
        try{month_int = Integer.parseInt(month);day_int = Integer.parseInt(day);day=String.valueOf(day_int);}
        catch (Exception e){
            System.out.println(e);
            new Modal_Error().set_erroe_messege("Ошибка парсинга даты в месяц");
        }

        switch (month_int){
            case 1 : month = "января";break;
            case 2 : month = "февраля";break;
            case 3 : month = "марта";break;
            case 4 : month = "апреля";break;
            case 5 : month = "мая";break;
            case 6 : month = "июня";break;
            case 7 : month = "июля";break;
            case 8 : month = "августа";break;
            case 9 : month = "сентября";break;
            case 10 : month = "октября";break;
            case 11 : month = "ноября";break;
            case 12 : month = "декабря";break;

            default:return "ERROR";
        }

        return day + " " + month + " " + year +" г.";


    }


    public static String raplace_organization(String s){
        if(s.equals("Агроаспект ООО")){return "ООО «Агроаспект»";}
        if(s.equals("Агроторг ООО")){return "ООО «Агроторг»";}
        if(s.equals("БАЗА 25 ООО")){return "ООО «БАЗА 25»";}
        if(s.equals("ГАСТРОНОМ\"ЦЕНТРАЛЬНЫЙ\"ООО")){return "ООО «ГАСТРОНОМ\"ЦЕНТРАЛЬНЫЙ\"»";}
        if(s.equals("КОПЕЙКА-ВОРОНЕЖ ООО")){return "ООО «КОПЕЙКА-ВОРОНЕЖ»";}
        if(s.equals("КОПЕЙКА-МОСКВА ООО")){return "ООО «КОПЕЙКА-МОСКВА»";}
        if(s.equals("Купец ООО")){return "ООО «Купец»";}
        if(s.equals("ООО \"ЕВРО-РИТЕЙЛ\"")){return "ООО «ЕВРО-РИТЕЙЛ»";}
        if(s.equals("ООО «Военторг-Ритейл»")){return "ООО «Военторг-Ритейл»";}
        if(s.equals("РегионПродукт ООО")){return "ООО «Регион-Продукт»";}
        if(s.equals("Регион-Продукт ООО")){return "ООО «Регион-Продукт»";}
        if(s.equals("Ритейл Трейд ООО")){return "ООО «Ритейл Трейд»";}
        if(s.equals("СтройИнвест ООО")){return "ООО «СтройИнвест»";}
        if(s.equals("ТД Перекресток АО")){return "АО «Торговый дом «ПЕРЕКРЕСТОК»";}

        return s;

    }



}
