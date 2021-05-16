package newEngine.StringClass;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestroyStringBuilder {

    public byte sibmollength = 3;

    public static String example =  "r=\"";


    /**
     *  Destroy - имеется в виду получение и уничтожение после обработки
     *  Shared - ссылки
     *  destroy - обычный файл
     */

    public void destroy (StringBuilder sb){

        char [] arrraySymbol = returnArrayChar(sb);
        sb.setLength(0);

        int startIndex = 0;

        for(int i = 0 ; i < arrraySymbol.length ; i ++){

            sb.append(arrraySymbol[i]);
            // Добавим каждый символ

            if(sb.length() == sibmollength){
                // Читаем по 3 символа, если попадается r=" то считываем во вторую переменную
                // Сохраняя индекс
                if(!sb.toString().equals(example)){
                    sb.setLength(0);
                    i-=2; continue;
                    // Если нет, то сбрасываем, и отступаем назад на 2 символа
                }

                if(sb.toString().equals(example)){
                    // Если находится, отправляем считывать в одну строку
                    startIndex = i;
                    new SecondLevelDest().startSecLev(arrraySymbol,startIndex);
                    startIndex = 0;
                    sb.setLength(0);
                }

            }

        }


        sb.setLength(0);
    }

    public void destroyShared(StringBuilder sb){


        Pattern p = Pattern.compile("<si>(.*?)</si>",Pattern.MULTILINE | Pattern.DOTALL);
        Matcher m = p.matcher(sb.toString());
        LinkedList<StringBuilder> list = new LinkedList<>();

        // Разбиение строки на подгруппы si

        while (m.find()){
            list.add(new StringBuilder().append(m.group(1)));
        }

        for(StringBuilder s  : list){

            // Отправка каждого элемента на второй уровень
            // Получение и создание непосредственного объекта
            sharedSecondLevel(s.toString());
        }

        for(StringBuilder sbs : list){
            sbs.setLength(0);
        }

    }

    public void sharedSecondLevel(String sb){

        if(sb.contains("<t/>") || sb.equals("<t/>")){
            CurrentLinkedString.linked.add("EMP");
            return;

             /*

            Страховка на случай пустой строки.
            Должно работать )/ Соответственно, выходим из метода.

            */
        }

        Pattern p = Pattern.compile(">(.*?)</t>");
        Matcher m = p.matcher(sb);

       StringBuilder sbb = new StringBuilder();

        while (m.find()){
            sbb.append(m.group(1));
            break;
        }
        CurrentLinkedString.linked.add(sbb.toString());


        // В отличии от всех остальных методов, получение ссылки самое простое.
        sb = null;
        sbb.setLength(0);
    }


    public static char [] returnArrayChar (StringBuilder sb){

        // Возвращает массив символов из всего документа
        int length_sb = sb.length();

        char arrraySymbol []  = new char [length_sb];

        sb.getChars(0,length_sb,arrraySymbol,0);
        length_sb = 0;
        sb.setLength(0);

        return arrraySymbol;
    }




    public void destroy (StringBuilder sb,int x){

        char [] arrraySymbol = returnArrayChar(sb);
        sb.setLength(0);

        int startIndex = 0;

        StringBuilder temp = new StringBuilder();


        for(int i = 0 ; i < arrraySymbol.length ; i ++){

            temp.append(arrraySymbol[i]);
            // Добавим каждый символ

            if(temp.length() == sibmollength){
                // Читаем по 3 символа, если попадается r=" то считываем во вторую переменную
                // Сохраняя индекс
                if(!temp.toString().equals(example)){
                    temp.setLength(0);
                    i-=2; continue;
                    // Если нет, то сбрасываем, и отступаем назад на 2 символа
                }

                if(temp.toString().equals(example)){
                    // Если находится, отправляем считывать в одну строку
                    startIndex = i;
                    new SecondLevelDest().startSecLev(arrraySymbol,startIndex,0);
                    startIndex = 0;
                    temp.setLength(0);
                }

            }

        }


        temp.setLength(0);
        arrraySymbol = null;
    }
}
