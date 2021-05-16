package newEngine;

import newEngine.StringClass.DestroyStringBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ParsingXML {


    public void prepeare(String pathTarget) {

        try
        {
            File file = new File(pathTarget);
            printText(file);
            file = null;
        }

        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public static void printText(File file) throws IOException {

        FileInputStream fis = new FileInputStream(file);

        int i;

        StringBuilder sb = new StringBuilder();

        while((i=fis.read())!= -1){
            sb.append((char)i);
        }

        //System.out.println(sb.toString());
        fis.close();

        new DestroyStringBuilder().destroy(sb);

        sb.setLength(0);


    }

    public void prepeareSharedString(String pathTarget) {


        // Обработка ссылок
        try
        {
            File file = new File(pathTarget);
            printShared(file);
            file = null;
        }

        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public static void printShared(File file) throws IOException {

        // Для SharedString

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis,StandardCharsets.UTF_8);
        int i;

        StringBuilder sb = new StringBuilder();

        while((i=isr.read())!= -1){
            sb.append((char)i);
        }

        //System.out.println(sb.toString());
        isr.close();
        fis.close();

        new DestroyStringBuilder().destroyShared(sb);

        sb.setLength(0);


    }


    // Ниже обработка для сап файлов !!!!!!


    public void prepeare(String pathTarget,int operation) {

        try
        {
            File file = new File(pathTarget);
            printText(file,0);
            file = null;
        }

        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public static void printText(File file,int operation) throws IOException {

        FileInputStream fis = new FileInputStream(file);

        int i;

        StringBuilder sb = new StringBuilder();

        while((i=fis.read())!= -1){
            sb.append((char)i);
        }

        //System.out.println(sb.toString());
        fis.close();

        new DestroyStringBuilder().destroy(sb,0);

        sb.setLength(0);


    }




}


