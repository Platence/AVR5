package newEngine.StringClass;

import newEngine.StringClass.constructor.ConsWithoutLink;
import newEngine.StringClass.constructor.ConstructorWithLink;

public class ThirdClassAtempt {

    /**
     *  Класс обработки строк
     */

    public static final String isLinked = "t=\"s\"";

    public void getAttempt(StringBuilder sb){

        if(sb.toString().contains(isLinked)){
            // Если строка содержит islinked - то значение этой строки v = ссылка
           // System.out.println("Содержит ссылку ");
           // System.out.println(sb.toString());
            new ConstructorWithLink().addNewPosition(sb);
            sb.setLength(0);
            return;
        }
        if(!sb.toString().contains(isLinked)){
            // Иначе значение проставлено прямо тут!
            //System.out.println("Прямое значение ");
           // System.out.println(sb.toString());
            new ConsWithoutLink().addNewPosition(sb);



        }

        sb.setLength(0);
    }



    //SapRead


    public void getAttempt(StringBuilder sb,int operation){

        if(sb.toString().contains(isLinked)){
            // Если строка содержит islinked - то значение этой строки v = ссылка
           // System.out.println("Содержит ссылку ");
           // System.out.println(sb.toString());
            new ConstructorWithLink().addNewPosition(sb,0);
            sb.setLength(0);
            return;
        }
        if(!sb.toString().contains(isLinked)){
            // Иначе значение проставлено прямо тут!
            //System.out.println("Прямое значение ");
           // System.out.println(sb.toString());
            new ConsWithoutLink().addNewPosition(sb,0);

        }

        sb.setLength(0);
    }



}
