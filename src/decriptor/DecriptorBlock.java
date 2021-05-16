package decriptor;

import all_paths.Paths_Main_File;
import error_package.Modal_Error;
import javafx.stage.FileChooser;
import sample.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DecriptorBlock {

    public DecriptorBlock() {
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFile() throws IOException {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(Main.stage_main_copy);
        ArrayList<StringBuilder> list = new ArrayList<>();

        if(f!=null) {

            BufferedReader bf = new BufferedReader(new FileReader(f));

            while (bf.read()>0){
                StringBuilder d = new StringBuilder();
                d.append(bf.readLine());
                list.add(d);
            }

            crypt(list);
        }
    }

    private void crypt(ArrayList<StringBuilder> list ) throws IOException {

        boolean startAdd = false;

        StringBuilder finalMessage = new StringBuilder();
        HashMap<String,String> map = new HashMap<>();

        map.put("test","#@0000:0@");

        for (StringBuilder sb : list){

            String [] fa = sb.toString().split("\\|");

            if(fa.length<2){sb.setLength(0);continue;}

            if(startAdd = false) {
                if (sb.toString().contains("Блокиров")) { startAdd = true; }
                continue;
            }

            if(fa.length<4){sb.setLength(0);continue;}

            String [] z = trimField(fa);
            sb.setLength(0);

            if(!notnull(z)){continue;}

            if(map.containsKey(z[0])){
                for (Map.Entry<String,String> entry : map.entrySet()){
                    if(entry.getKey().equals(z[0])){
                        StringBuilder temp = new StringBuilder();
                        temp.append(entry.getValue());
                        temp.append(z[1]+":"+z[3]+""+"@");
                        entry.setValue(temp.toString());
                        temp.setLength(0);
                        break;
                    }
                }
                continue;
            }
            map.put(z[0],"#"+z[1]+":"+z[3]+"@");
        } // end sb

        for (Map.Entry<String,String> entry : map.entrySet()){
            finalMessage.append(entry.getKey()).append(entry.getValue()).append(System.lineSeparator());
        }

        System.out.println(finalMessage.toString());
        System.out.println("End operation");

        saveOperation(finalMessage);

        new Modal_Error().set_erroe_messege("Сохранено успешно : " + Paths_Main_File.path_final_out);
    }

    private void saveOperation(StringBuilder finalMessage) throws IOException {

        FileWriter fw = new FileWriter(Paths_Main_File.path_final_out+"/docLock.txt");
        fw.write(String.valueOf(finalMessage));
        fw.flush();
        fw.close();
    }

    private String [] trimField(String ...fa){

        String [] z = new String[fa.length];
        for(int i = 0 ; i <fa.length; i++){
            z[i] = fa[i].trim();
            if(i==3){
                StringBuilder temp = new StringBuilder();
                temp.append(z[i]);
                if(temp.toString().contains("-")){
                    StringBuilder temp3 = new StringBuilder();
                    temp3.append(temp.toString().replace("-",""));
                    temp.setLength(0);
                    temp.append(temp3.toString());
                    temp3.setLength(0);
                }

                if(temp.toString().contains(",")){
                    String temp2 = temp.toString().replace(",",".");
                    double d = Double.parseDouble(temp2);
                    int x = (int) d;
                    z[i] = String.valueOf(x);
                    temp.setLength(0);
                    continue;
                }
                z[i] = temp.toString();
                temp.setLength(0);
                // Eto ppc... :D
                // Reverse of number-  TO -> -number
                // Delete char [,] cast to INT
            }
        }
        return z;
    }

    private boolean notnull(String... z){

        try{
            int d = Integer.parseInt(z[3]);
            if(d==0){return false;}
        }
        catch (Exception e){return true;}
        return true;
    }

}
