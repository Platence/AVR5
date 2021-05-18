package tgODDS;

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


}
