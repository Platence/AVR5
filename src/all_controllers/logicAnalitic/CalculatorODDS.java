package all_controllers.logicAnalitic;

import warehouse_plu.Ostatku;

public class CalculatorODDS {


    /**
     * Непосредственно просчет групп
     */


    public static void setCalc(OddsFromTg tgOBJ, Ostatku ost){



        if(ost.getYu2().equals("Алкоголь")){tgOBJ.setAlckogol(tgOBJ.getAlckogol()+ost.getOddsSUM());return;}
        if(ost.getYu3().contains("бственное производств")){return;}

        if(ost.getYu2().equals("Корма для животных")){tgOBJ.setKorm(tgOBJ.getKorm()+ost.getOddsSUM());return;}
        if(ost.getYu2().contains("акалея (Food")){tgOBJ.setBakaleya(tgOBJ.getBakaleya()+ost.getOddsSUM());return;}
        if(ost.getYu2().contains("Готовая кулинария, салаты")){tgOBJ.setBakaleya(tgOBJ.getBakaleya()+ost.getOddsSUM());return;}
        if(ost.getYu2().contains("Диабетическое питание")){tgOBJ.setBakaleya(tgOBJ.getBakaleya()+ost.getOddsSUM());return;}
        if(ost.getYu2().contains("терские изделия")){tgOBJ.setKonditerka(tgOBJ.getKonditerka()+ost.getOddsSUM());return;}

        if(ost.getYu2().equals("Детские товары (Food)")){tgOBJ.setKids_pit(tgOBJ.getKids_pit()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Детское питание (Food)")){tgOBJ.setKids_pit(tgOBJ.getKids_pit()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Детское питание (Fresh)")){tgOBJ.setKids_pit(tgOBJ.getKids_pit()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Детские товары (NonFood)")){tgOBJ.setKids_tov(tgOBJ.getKids_tov()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Соки, воды, пиво")){tgOBJ.setSok_voda_Pivo(tgOBJ.getSok_voda_Pivo()+ost.getOddsSUM());return;}

        if(ost.getYu2().equals("Замороженные продукты")){tgOBJ.setZamorozka(tgOBJ.getZamorozka()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Молочная гастрономия")){tgOBJ.setMoloko(tgOBJ.getMoloko()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Мясная гастрономия")){tgOBJ.setColbasa_myaso(tgOBJ.getColbasa_myaso()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Рыбная гастрономия")){tgOBJ.setColbasa_myaso(tgOBJ.getColbasa_myaso()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Птица и изделия из птицы")){tgOBJ.setColbasa_myaso(tgOBJ.getColbasa_myaso()+ost.getOddsSUM());return;}

        if(ost.getYu2().equals("Мясо и мясные изделия")){tgOBJ.setColbasa_myaso(tgOBJ.getColbasa_myaso()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Овощи - Фрукты")){tgOBJ.setFrov(tgOBJ.getFrov()+ost.getOddsSUM());return;}


        if(ost.getYu2().equals("Медиа")){tgOBJ.setMedia(tgOBJ.getMedia()+ost.getOddsSUM());return;}

        if(itsInautOrNot(ost)){tgOBJ.setIn_aut(tgOBJ.getIn_aut()+ost.getOddsSUM());return;}

        if(ost.getYu2().contains("опутствующи")){
            if(ost.plu.equals("3300573")){tgOBJ.setSpec_akc(tgOBJ.getSpec_akc()+ost.getOddsSUM());return;}
            tgOBJ.setSoputka(tgOBJ.getSoputka()+ost.getOddsSUM());return;}

        if(ost.getYu2().equals("Одежда")){tgOBJ.setOdejda(tgOBJ.getOdejda()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Обувь")){tgOBJ.setOdejda(tgOBJ.getOdejda()+ost.getOddsSUM());return;}

        if(ost.getYu2().contains("овары спец")){tgOBJ.setSpec_akc(tgOBJ.getSpec_akc()+ost.getOddsSUM());return;}
        if(ost.getYu2().equals("Дисконтные карты, билеты")){tgOBJ.setSpec_akc(tgOBJ.getSpec_akc()+ost.getOddsSUM());return;}


        if(ost.getYu2().contains("абачные")){return;}

        tgOBJ.setAnother(tgOBJ.getAnother()+ost.getOddsSUM());
        tgOBJ.anotherName.add(ost.getYu2());

    }

    public static boolean itsInautOrNot(Ostatku ots){

        String s = ots.getYu2();

        if(s.equals("Галантерея")){return true;}
        if(s.equals("Товары для праздника")){return true;}
        if(s.equals("Товары для дома")){return true;}
        if(s.equals("Текстиль")){return true;}
        if(s.equals("Канцелярские товары")){return true;}
        if(s.equals("Отдых")){return true;}
        if(s.equals("Товары для животных")){return true;}
        if(s.equals("Спорт")){return true;}
        if(s.equals("Сад и Огород")){return true;}
        if(s.equals("Авто аксессуары")){return true;}
        if(s.equals("Бытовая техника")){return true;}
        if(s.equals("Товары для ремонта")){return true;}


        return false;


    }


}
