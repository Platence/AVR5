package tgODDS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class UpdateList {


    public void addToListSomeObjects(LinkedList<InfoForTable> list){
        list.add(createAlcoObj());
        list.add(createCoffe());
        list.add(createTea());
        list.add(createAnothreBakaley());
        list.add(createKonditer());
        list.add(createMeet());
        list.add(createMilk());
        list.add(createCold());
        list.add(createFish());
        list.add(createO2());
        list.add(createAnimalsEat());
        list.add(createFrov());
        list.add(createCigarets());
        list.add(createShapAndCreme());
        list.add(createRapira());
        list.add(createSoput());
        list.add(createInAut());


    }

    private InfoForTable createAlcoObj(){
        // Подсчет алкоголя
        ArrayList<String> group = new ArrayList<>();
        group.add("Алкоголь");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Алкоголь");
    }

    private InfoForTable createCoffe(){
        // Подсчет Кофе
        ArrayList<String> group = new ArrayList<>();
        group.add("Кофе, какао, шоколад");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL3,group,ignore,"Кофе");
    }

    private InfoForTable createTea(){
        // Подсчет Чая
        ArrayList<String> group = new ArrayList<>();
        group.add("Чай");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL3,group,ignore,"Чай");
    }

    private InfoForTable createKonditer(){
        // Подсчет Чая
        ArrayList<String> group = new ArrayList<>();
        group.add("Кондитерские изделия (Food)");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Кондитер");
    }

    private InfoForTable createMeet(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Мясная гастрономия");
        group.add("Мясо и мясные изделия");
        group.add("Птица и изделия из птицы");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Мясо,колбасы,птица");
    }

    private InfoForTable createMilk(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Молочная гастрономия");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Молочные");
    }

    private InfoForTable createCold(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Замороженные продукты");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Заморозка");
    }

    private InfoForTable createFish(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Рыбная гастрономия");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Рыбный гастроном");
    }

    private InfoForTable createAnothreBakaley(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Бакалея (Food)");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("Чай");
        ignore.add("Кофе, какао, шоколад");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Прочая Бакалея");
    }

    private InfoForTable createO2(){
        // Подсчет Мяса,колбас
        ArrayList<String> group = new ArrayList<>();
        group.add("Соки, воды, пиво");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Напитки");
    }

    private InfoForTable createAnimalsEat(){
        // Подсчет Корм для животных
        ArrayList<String> group = new ArrayList<>();
        group.add("Корма для животных");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Корм для жив.");
    }

    private InfoForTable createFrov(){
        // Подсчет Овощи
        ArrayList<String> group = new ArrayList<>();
        group.add("Овощи - Фрукты");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"ФРОВ");
    }

    private InfoForTable createCigarets(){
        // Подсчет Сигарет
        ArrayList<String> group = new ArrayList<>();
        group.add("Табачные изделия и аксессуары (Food");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"сигареты");
    }

    private InfoForTable createShapAndCreme(){
        // Подсчет Средства ухода
        ArrayList<String> group = new ArrayList<>();
        group.add("Средства для ухода за бельем и одеж");
        group.add("Средства для душа");
        group.add("Средства для волос");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL3,group,ignore,"Шампуни,порошки");
    }

    private InfoForTable createRapira(){
        // Подсчет Средства ухода
        ArrayList<String> group = new ArrayList<>();
        group.add("Средства для бритья");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("");
        return SearchData.getObject(SearchData.yuL3,group,ignore,"Средства для бритья");
    }

    private InfoForTable createSoput(){
        // Подсчет Средства ухода
        ArrayList<String> group = new ArrayList<>();
        group.add("Сопутствующие товары (Food)");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("Средства для бритья");
        ignore.add("Средства для душа");
        ignore.add("Средства для волос");
        ignore.add("Средства для ухода за бельем и одеж");
        return SearchData.getObject(SearchData.yuL2,group,ignore,"Сопутка(Остальное)");
    }

    private InfoForTable createInAut(){
        ArrayList<String> group = new ArrayList<>();
        group.add("");
        ArrayList<String> ignore = new ArrayList<>();
        ignore.add("Алкоголь");
        ignore.add("Бакалея (Food)");
        ignore.add("Замороженные продукты");
        ignore.add("Корма для животных");
        ignore.add("Молочная гастрономия");
        ignore.add("Мясная гастрономия");
        ignore.add("Мясо и мясные изделия");
        ignore.add("Овощи - Фрукты");
        ignore.add("Птица и изделия из птицы");
        ignore.add("Рыбная гастрономия");
        ignore.add("Соки, воды, пиво");
        ignore.add("Сопутствующие товары (Food)");
        ignore.add("Табачные изделия и аксессуары (Food");
        return SearchData.getObject(SearchData.yuL2Ex,group,ignore,"Ин-АУТ(+стики)");
    }





}
