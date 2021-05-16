package newEngine.StringClass.constructor;

import newEngine.ResultsOSTATKU.ListTemps;
import newEngine.StringClass.CurrentLinkedString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConstructorWithLink {


    /**
     *  Принимает значения с ссылками
      */

    public void addNewPosition(StringBuilder st){

        try {

            Pattern p = Pattern.compile("\"(.*?)\" s");
            Matcher m = p.matcher(st.toString());
            StringBuilder sbkeyCell = new StringBuilder();
            while (m.find()) {
               // System.out.println(m.group(1));
                sbkeyCell.append(m.group(1));
            }

            // Получение номера ячейки


            Pattern p2 = Pattern.compile("v>(.*?)</v");
            Matcher m2 = p2.matcher(st.toString());
            StringBuilder sbkeylinked = new StringBuilder();
            while (m2.find()) {
               // System.out.println(m2.group(1));
                sbkeylinked.append(m2.group(1));
            }

            // Получение ссылки на строку для этой ячейки

            readyToRead(sbkeyCell.toString(),sbkeylinked.toString());

            sbkeyCell.setLength(0);
            sbkeylinked.setLength(0);
            st.setLength(0);

            sbkeyCell = null;
            sbkeylinked = null;
            st = null;

        }

        catch (Exception e){
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");

        }

    }


    public void readyToRead(String key, String link){

        // Получаем ячейка - ссылка.
        // Ссылку нужно распарсить в другой файл.
        try
        {

        int l = Integer.parseInt(link);
        String z = CurrentLinkedString.getCurrentLinkd(l);

            //System.out.println(" Cell = " + key + "  value = " + z + " link =" + l);
            ListTemps.tryAddNewPosition(key,z);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }


    // Sap Read

    public void addNewPosition(StringBuilder st,int operation){

        try {

            Pattern p = Pattern.compile("\"(.*?)\" s");
            Matcher m = p.matcher(st.toString());
            StringBuilder sbkeyCell = new StringBuilder();
            while (m.find()) {
                // System.out.println(m.group(1));
                sbkeyCell.append(m.group(1));
            }

            // Получение номера ячейки


            Pattern p2 = Pattern.compile("v>(.*?)</v");
            Matcher m2 = p2.matcher(st.toString());
            StringBuilder sbkeylinked = new StringBuilder();
            while (m2.find()) {
                // System.out.println(m2.group(1));
                sbkeylinked.append(m2.group(1));
            }

            // Получение ссылки на строку для этой ячейки

            readyToRead(sbkeyCell.toString(),sbkeylinked.toString(),0);

            sbkeyCell.setLength(0);
            sbkeyCell = null;
            sbkeylinked.setLength(0);
            sbkeylinked = null;
            st.setLength(0);
            st = null;


        }

        catch (Exception e){
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");
            System.out.println("ОШИБКА ПРИ ЧТЕНИИ С ПОМОЩЬЮ ПАТТЕРНА!!!!");

        }

    }


    public void readyToRead(String key, String link,int operation){

        // Получаем ячейка - ссылка.
        // Ссылку нужно распарсить в другой файл.
        try
        {

            int l = Integer.parseInt(link);
            StringBuilder z = new StringBuilder() ;
            z.append(CurrentLinkedString.getCurrentLinkd(l));

            //System.out.println(" Cell = " + key + "  value = " + z + " link =" + l);
            new ListTemps().tryAddNewPosition(key,z.toString(),0);
            z.setLength(0);
            z = null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
    }




}
