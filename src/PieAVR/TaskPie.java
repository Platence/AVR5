package PieAVR;

import sample.Main;
import warehouse_plu.Ostatku;

import java.util.Map;
import java.util.TreeMap;

public class TaskPie {

    private TreeMap<String,Double> listYU2Surplus;
    private TreeMap<String,Double> listYU2Shortage;

    public void createListYU2(){

        listYU2Surplus = new TreeMap<>();

        //For Shurplus
        int count = 0;
        int stat = 0; // 0 - Добавляем после поиска, 1 - не добавляем

        for(Ostatku ost : Main.classOstatku) {
            String word = replaceYU3(ost);
            if(word.equals("ДРУГОЕ")){continue;}
            stat = 0;
            if(!checkRulez(ost)){continue;}   // Проверка на +
            if(ost.getOddsSUM()<0){continue;} // Проверка на СП и прочую шушуру

            if(count==0){listYU2Surplus.put(word,ost.getOddsSUM());count++;continue;}
            // Заполним чем-нибудь

             for(Map.Entry<String,Double> maps : listYU2Surplus.entrySet()){
                if(maps.getKey().equals(word)){
                    double x = maps.getValue();
                    maps.setValue(x+ost.getOddsSUM());
                    stat=1;break;
                }
             }
                if(stat==1){continue;}
                listYU2Surplus.put(word,ost.getOddsSUM());
         }

        //-------

        //-------

        //-------


        listYU2Shortage = new TreeMap<>();

        //For Shortage
        int count2 = 0;
        int stat2 = 0;
        // 0 - Добавляем после поиска,
        // 1 - не добавляем


        for(Ostatku ost : Main.classOstatku) {
            String word = replaceYU3(ost);
            if(word.equals("ДРУГОЕ")){continue;}
            stat2 = 0;
            if(!checkRulez(ost)){continue;}   //  Проверка на СП и прочую шушуру
            if(ost.getOddsSUM()>0){continue;} //  Проверка на -

            if(count2==0){listYU2Shortage.put(word,ost.getOddsSUM());count2++;continue;}
            // Заполним чем-нибудь

            for(Map.Entry<String,Double> maps : listYU2Shortage.entrySet()){
                if(maps.getKey().equals(word)){
                    double x = maps.getValue();
                    maps.setValue(x+ost.getOddsSUM());
                    stat2=1;break;
                }
            }
            if(stat2==1){continue;}
            listYU2Shortage.put(word,ost.getOddsSUM());
        }

    }

    public TreeMap<String, Double> getListYU2Surplus() {
        return listYU2Surplus;
    }

    public void setListYU2Surplus(TreeMap<String, Double> listYU2Surplus) {
        this.listYU2Surplus = listYU2Surplus;
    }

    public TreeMap<String, Double> getListYU2Shortage() {
        return listYU2Shortage;
    }

    public void setListYU2Shortage(TreeMap<String, Double> listYU2Shortage) {
        this.listYU2Shortage = listYU2Shortage;
    }

    public static boolean checkRulez(Ostatku s){
        if(s.getYu3().contains("луфабрикаты для пек")){return false;}
        if(s.getYu3().contains("ственное производс")){return false;}
        if(s.getYu2().contains("Алко")){return true;}
        if(Math.abs(s.getOddsSUM())>9000){
            return !(Math.abs(s.getOddsSUM() / s.getOddsCOUNT()) > 9000);
        }
        return true;
    }

    public static String replaceYU3(Ostatku d){

        if(d.getYu2().equals("Алкоголь")){return "Алкоголь";}

        if(d.getYu3().equals("Автохимия, косметика")){return "Химия,сопутка";}
        if(d.getYu3().equals("Ароматизаторы")){return "Химия,сопутка";}
        if(d.getYu3().equals("Средства ухода за обувью")){return "Химия,сопутка";}
        if(d.getYu3().equals("Моющие,чистящие средства")){return "Химия,сопутка";}
        if(d.getYu3().equals("Целлюлозная и ватная продукция")){return "Химия,сопутка";}
        if(d.getYu3().equals("Средства для ухода за бельем и одеж")){return "Химия,сопутка";}
        if(d.getYu3().equals("Инсектициды")){return "Химия,сопутка";}
        if(d.getYu3().equals("Упаковочные материалы (Food)")){return "Химия,сопутка";}
        if(d.getYu3().equals("Упаковочные материалы (NonFood)")){return "Химия,сопутка";}
        if(d.getYu3().equals("Аудио-Видео аксессуары")){return "ИНАУТ";}
        if(d.getYu3().equals("Канцелярские товары")){return "ИНАУТ";}
        if(d.getYu3().equals("Лампы накаливания")){return "ИНАУТ";}
        if(d.getYu3().equals("Текстиль для дома")){return "ИНАУТ";}
        if(d.getYu3().equals("Товары для ванной")){return "ИНАУТ";}
        if(d.getYu3().equals("Товары для кошек")){return "ИНАУТ";}
        if(d.getYu3().equals("Садовый декор")){return "ИНАУТ";}
        if(d.getYu3().equals("Садовый инвентарь")){return "ИНАУТ";}
        if(d.getYu3().equals("Сервировочная посуда")){return "ИНАУТ";}
        if(d.getYu3().equals("Багаж")){return "ИНАУТ";}
        if(d.getYu3().equals("БАД")){return "Бакалея О";}
        if(d.getYu3().equals("Бакалея диабетическая")){return "Бакалея О";}
        if(d.getYu3().equals("Бараночные изделия")){return "Бакалея О";}
        if(d.getYu3().equals("Жевательная резинка")){return "Бакалея О";}
        if(d.getYu3().equals("Яйцо")){return "Бакалея О";}
        if(d.getYu3().equals("Крупы и зерновые")){return "Бакалея О";}
        if(d.getYu3().equals("Сухие завтраки, мюсли")){return "Бакалея О";}
        if(d.getYu3().equals("Сухие компоненты и смеси (Food)")){return "Бакалея О";}
        if(d.getYu3().equals("Сухофрукты")){return "Бакалея О";}
        if(d.getYu3().equals("Соусы")){return "Бакалея О";}
        if(d.getYu3().equals("Соль")){return "Бакалея О";}
        if(d.getYu3().equals("Специи")){return "Бакалея О";}
        if(d.getYu3().equals("Сахар")){return "Бакалея О";}
        if(d.getYu3().equals("Семечки")){return "Бакалея О";}
        if(d.getYu3().equals("Макаронные изделия")){return "Бакалея О";}
        if(d.getYu3().equals("Масло растительное")){return "Бакалея О";}
        if(d.getYu3().equals("Рыба вяленая, сушеная")){return "Бакалея О";}
        if(d.getYu3().equals("Рыба копченая")){return "Бакалея О";}
        if(d.getYu3().equals("Рыбная кулинария")){return "Бакалея О";}
        if(d.getYu3().equals("Продукция быстрого приготовления")){return "Бакалея О";}
        if(d.getYu3().equals("Орехи")){return "Бакалея О";}
        if(d.getYu3().equals("Мука")){return "Бакалея О";}
        if(d.getYu3().equals("Бытовые принадлежности")){return "Химия,сопутка";}
        if(d.getYu3().equals("Варенье, мёд")){return "Кондитер";}
        if(d.getYu3().equals("Вафли")){return "Кондитер";}
        if(d.getYu3().equals("Кондитерка диабетическая")){return "Кондитер";}
        if(d.getYu3().equals("Печенье, крекер")){return "Кондитер";}
        if(d.getYu3().equals("Пироги, изделия сдобные")){return "Кондитер";}
        if(d.getYu3().equals("Верхняя одежда")){return "ИНАУТ";}
        if(d.getYu3().equals("Чулочно-носочные изделия")){return "ИНАУТ";}
        if(d.getYu3().equals("Вода")){return "Напитки Сок";}
        if(d.getYu3().equals("Соки, нектары")){return "Напитки Сок";}
        if(d.getYu3().equals("Напитки, квас, тоник")){return "Напитки Сок";}
        if(d.getYu3().equals("Водный спорт")){return "ИНАУТ";}
        if(d.getYu3().equals("Игры для улицы")){return "ИНАУТ";}
        if(d.getYu3().equals("Восточные сладости, халва")){return "Кондитер";}
        if(d.getYu3().equals("Кексы, рулеты")){return "Кондитер";}
        if(d.getYu3().equals("Зефир, мармелад, пастила")){return "Кондитер";}
        if(d.getYu3().equals("Пряники")){return "Кондитер";}
        if(d.getYu3().equals("Конфеты")){return "Кондитер";}
        if(d.getYu3().equals("Торты, пирожные свежие")){return "Кондитер";}
        if(d.getYu3().equals("Галантерейные аксессуары")){return "ИНАУТ";}
        if(d.getYu3().equals("Кухонная посуда")){return "ИНАУТ";}
        if(d.getYu3().equals("Кожгалантерея")){return "ИНАУТ";}
        if(d.getYu3().equals("Кемпинг")){return "ИНАУТ";}
        if(d.getYu3().equals("Книги, диски, пресса")){return "ИНАУТ";}
        if(d.getYu3().equals("Одноразовая посуда")){return "ИНАУТ";}
        if(d.getYu3().equals("Предметы украшения")){return "ИНАУТ";}
        if(d.getYu3().equals("Хозяйственные принадлежности")){return "ИНАУТ";}
        if(d.getYu3().equals("Гигиена рта")){return "Химия,сопутка";}
        if(d.getYu3().equals("Крема и товары для красоты")){return "Химия,сопутка";}
        if(d.getYu3().equals("Средства для волос")){return "Химия,сопутка";}
        if(d.getYu3().equals("Средства для душа")){return "Химия,сопутка";}
        if(d.getYu3().equals("Средства личной гигиены")){return "Химия,сопутка";}
        if(d.getYu3().equals("Головные уборы и аксессуары")){return "ИНАУТ";}
        if(d.getYu3().equals("Пикник")){return "ИНАУТ";}
        if(d.getYu3().equals("Домашняя обувь")){return "ИНАУТ";}
        if(d.getYu3().equals("Гот.блюда охл.ПРИСТ")){return "Пресервы";}
        if(d.getYu3().equals("Пресервы")){return "Пресервы";}
        if(d.getYu3().equals("Крабовые палочки и мясо охлажденные")){return "Пресервы";}
        if(d.getYu3().equals("Мясо и П/Ф охлажденые")){return "Пресервы";}
        if(d.getYu3().equals("Птица и П/Ф охлажденые")){return "Пресервы";}
        if(d.getYu3().equals("Грибы сушеные")){return "Бакалея О";}
        if(d.getYu3().equals("Икра")){return "Бакалея О";}
        if(d.getYu3().equals("Декор для дома")){return "ИНАУТ";}
        if(d.getYu3().equals("Товары для собак")){return "ИНАУТ";}
        if(d.getYu3().equals("Декоративная косметика, туал вода.")){return "Химия,сопутка";}
        if(d.getYu3().equals("Деликатесы")){return "Колбаса,сосиски";}
        if(d.getYu3().equals("Колбасные изделия")){return "Колбаса,сосиски";}
        if(d.getYu3().equals("Десерты")){return "Кондитер";}
        if(d.getYu3().equals("Детские консервированные смеси")){return "Детское(FOOD)";}
        if(d.getYu3().equals("Детские молочные продукты")){return "Детское(FOOD)";}
        if(d.getYu3().equals("Детские смеси")){return "Детское(FOOD)";}
        if(d.getYu3().equals("Детские соки, чай, вода")){return "Детское(FOOD)";}
        if(d.getYu3().equals("Детские средства гигиены")){return "Детское(NOF)";}
        if(d.getYu3().equals("Посуда для новорожденных")){return "Детское(NOF)";}
        if(d.getYu3().equals("Игрушки для девочек")){return "Детское(NOF)";}
        if(d.getYu3().equals("Товары для новорожденных и мам")){return "Детское(NOF)";}
        if(d.getYu3().equals("Игрушки для мальчиков")){return "Детское(NOF)";}
        if(d.getYu3().equals("Прочее детское питание")){return "Детское(NOF)";}
        if(d.getYu3().equals("Замороженная плодоовощная продукция")){return "Заморож.прод";}
        if(d.getYu3().equals("Замороженное мясо и субпродукты")){return "Заморож.прод";}
        if(d.getYu3().equals("Замороженное мясо птицы")){return "Заморож.прод";}
        if(d.getYu3().equals("Замороженные морепродукты")){return "Заморож.прод";}
        if(d.getYu3().equals("Замороженные полуфабрикаты")){return "Заморож.прод";}
        if(d.getYu3().equals("Рыба замороженная")){return "Заморож.прод";}
        if(d.getYu3().equals("Мороженое")){return "Заморож.прод";}

        if(d.getYu3().equals("Консервы мясные")){return "Бакалея О";}
        if(d.getYu3().equals("Консервы овощные")){return "Бакалея О";}
        if(d.getYu3().equals("Консервы рыбные")){return "Бакалея О";}
        if(d.getYu3().equals("Консервы фруктовые, ягодные")){return "Бакалея О";}
        if(d.getYu3().equals("Корма для кошек")){return "Корм";}
        if(d.getYu3().equals("Корма для собак")){return "Корм";}
        if(d.getYu3().equals("Кофе, какао, шоколад")){return "Кофе";}
        if(d.getYu3().equals("Масложировые продукты")){return "Молоч.изд";}
        if(d.getYu3().equals("Сливки")){return "Молоч.изд";}
        if(d.getYu3().equals("Соврем.кислом.прод-ия")){return "Молоч.изд";}
        if(d.getYu3().equals("Традиц.кислом.прод-ия")){return "Молоч.изд";}
        if(d.getYu3().equals("Сыры")){return "Молоч.изд";}
        if(d.getYu3().equals("Молоко")){return "Молоч.изд";}
        if(d.getYu3().equals("Нижнее белье")){return "ИНАУТ";}
        if(d.getYu3().equals("Обувь для бассейна и пляжа")){return "ИНАУТ";}
        if(d.getYu3().equals("Одежда для сна и отдыха")){return "ИНАУТ";}
        if(d.getYu3().equals("Повседневная обувь")){return "ИНАУТ";}
        if(d.getYu3().equals("Овощи/Грибы")){return "FROV";}
        if(d.getYu3().equals("Овощи/Зелень")){return "FROV";}
        if(d.getYu3().equals("Фрукты/Экзотика")){return "FROV";}
        if(d.getYu3().equals("Фрукты/Ягоды")){return "FROV";}
        if(d.getYu3().equals("Пиво")){return "Пиво";}
        if(d.getYu3().equals("Слабоалкогольные напитки")){return "Пиво";}
        if(d.getYu3().equals("Чай")){return "Чай";}
        if(d.getYu3().equals("Шоколад, батончики")){return "Шоколад";}


        return "ДРУГОЕ";
    }
}
