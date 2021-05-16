package chekCrashPLU;

import error_package.Modal_Error;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ShadowNONPLU {

    public static ArrayList<String> shadowPLU = new ArrayList<>();


    public ShadowNONPLU() {
        //createShadowList();
    }

    public void createShadowList(){
        StringBuilder sb = sbbb();
        decryption(sb);
        showShodowPLU();
    }

    public void decryption(StringBuilder sb){
        int count = 0;
        int startInd = 0;

        for(int i = 0 ; i < sb.length(); i++){
            char x = sb.toString().charAt(i);
            if(x=='*'){count++;}
            if(count==2){startInd = i+1;break;}
        }

        String temp = "";

        for(int i = startInd; i < sb.length(); i++){
            if(sb.charAt(i)==','){shadowPLU.add(temp);temp = "";continue;}
            temp+=sb.charAt(i);
        }
        sb.setLength(0);

    }

    public StringBuilder sbbb (){
        StringBuilder sb = new StringBuilder();
        try(FileReader reader = new FileReader("SHADOWPLU.txt"))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                sb.append((char)c);
            }
        }
        catch(FileNotFoundException ex){
            createNewFileIfNotFound();
            System.out.println(ex.getMessage());
        }
        catch (IOException ep){
            new Modal_Error().set_erroe_messege("Неудачная попытка загрузки теневых PLU");
        }
        return sb;
    }

    public void createNewFileIfNotFound(){
        try(FileWriter writer = new FileWriter("SHADOWPLU.txt"))
        {
            // запись всей строки
            String text = "*ДОБАВЛЯЙТЕ PLU ЧЕРЕЗ ЗАПЯТУЮ , И AVR БУДЕТ ИГНОРИРОВАТЬ ИХ ПРИ СОЗДАНИИ АКТОВ)*";
            text+=System.lineSeparator();
            text+=System.lineSeparator();
            text+="0,";
            writer.write(text);
            // запись по символам
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void showShodowPLU(){
        System.out.println("ПОЛУЧЕНЫ ТЕНЕВЫЕ PLU - ");
        System.out.println();

        for(String z : shadowPLU){
            System.out.print(z);
        }
        System.out.println();
    }
}






