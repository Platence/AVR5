package newEngine.StringClass.constructor;

import newEngine.ResultsOSTATKU.ListTemps;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsWithoutLink {

    /**
     *  Конструктор для ячеек без ссылок
     */


    public void addNewPosition(StringBuilder st){

        try {

            Pattern p = Pattern.compile("\"(.*?)\" s");
            Matcher m = p.matcher(st.toString());
            StringBuilder sbkeyCell = new StringBuilder();
            while (m.find()) {
                //System.out.println(m.group(1));
                sbkeyCell.append(m.group(1));
            }

            // Получение номера ячейки
            StringBuilder sbkeylinked = new StringBuilder();

            if(st.toString().contains("</v")) {
                Pattern p2 = Pattern.compile("v>(.*?)</v");
                Matcher m2 = p2.matcher(st.toString());

                while (m2.find()) {
                    ///System.out.println(m2.group(1));
                    sbkeylinked.append(m2.group(1));
                }
            }

            if(st.toString().contains("</t>")) {
                Pattern p2 = Pattern.compile("t>(.*?)</t");
                Matcher m2 = p2.matcher(st.toString());

                while (m2.find()) {
                    ///System.out.println(m2.group(1));
                    sbkeylinked.append(m2.group(1));
                }
            }

            // Получение ссылки на строку для этой ячейки

            readyToRead(sbkeyCell.toString(),sbkeylinked.toString());

            sbkeyCell.setLength(0);
            sbkeylinked.setLength(0);
            st.setLength(0);


        }

        catch (Exception e){
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            e.printStackTrace();
        }

    }

    public void readyToRead(String key, String value){

        // Получаем ячейка - значение.
        // .

        //System.out.println("key = " + key +  "  value = " + value);

        if(tryParse(key)){
            ListTemps.tryAddNewPosition(key,value);
        }
        // Проверка на букву ячейки
    }

    public static boolean tryParse(String key){
        try{
            int x = Integer.parseInt(key);
            return false;
        }
        catch (Exception e){
            return true;
        }
    }


    // SAP READ

    public void addNewPosition(StringBuilder st,int operation){

        try {

            Pattern p = Pattern.compile("\"(.*?)\" s");
            Matcher m = p.matcher(st.toString());
            StringBuilder sbkeyCell = new StringBuilder();
            while (m.find()) {
                //System.out.println(m.group(1));
                sbkeyCell.append(m.group(1));
            }

            // Получение номера ячейки
            StringBuilder sbkeylinked = new StringBuilder();

            if(st.toString().contains("</v")) {
                Pattern p2 = Pattern.compile("v>(.*?)</v");
                Matcher m2 = p2.matcher(st.toString());

                while (m2.find()) {
                    ///System.out.println(m2.group(1));
                    sbkeylinked.append(m2.group(1));
                }
            }

            if(st.toString().contains("</t>")) {
                Pattern p2 = Pattern.compile("t>(.*?)</t");
                Matcher m2 = p2.matcher(st.toString());

                while (m2.find()) {
                    ///System.out.println(m2.group(1));
                    sbkeylinked.append(m2.group(1));
                }
            }

            // Получение ссылки на строку для этой ячейки

            readyToRead(sbkeyCell.toString(),sbkeylinked.toString(),0);

            sbkeyCell.setLength(0);
            sbkeylinked.setLength(0);
            st.setLength(0);


        }

        catch (Exception e){
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            e.printStackTrace();
        }

    }

    public void readyToRead(String key, String value,int operation){

        // Получаем ячейка - значение.
        // .

        //System.out.println("key = " + key +  "  value = " + value);

        if(tryParse(key)){new ListTemps().tryAddNewPosition(key,value,0);}
        // Проверка на букву ячейки
    }

}
