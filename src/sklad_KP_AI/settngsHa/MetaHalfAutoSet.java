package sklad_KP_AI.settngsHa;

import error_package.Modal_Error;
import interfaces_all.JustSaveInformation;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetaHalfAutoSet implements JustSaveInformation {

    /*
        Здесь храним переменные для TableView Hand-Auto
     */

    private String nameYu3Field ;

    private int count_qf_min;

    private int max_count_plu;

    public static ArrayList<MetaHalfAutoSet> listMeta = new ArrayList<>();
    public static String path_settingsMHF = "src/binn/kpAiManager/list.txt";


    public MetaHalfAutoSet(String nameYu3Field, int count_qf_min, int max_count_plu) {
        this.nameYu3Field = nameYu3Field;
        this.count_qf_min = count_qf_min;
        this.max_count_plu = max_count_plu;
    }

    public MetaHalfAutoSet() {
    }

    public String getNameYu3Field() {
        return nameYu3Field;
    }

    public void setNameYu3Field(String nameYu3Field) {
        this.nameYu3Field = nameYu3Field;
    }

    public int getCount_qf_min() {
        return count_qf_min;
    }

    public void setCount_qf_min(int count_qf_min) {
        this.count_qf_min = count_qf_min;
    }

    public int getMax_count_plu() {
        return max_count_plu;
    }

    public void setMax_count_plu(int max_count_plu) {
        this.max_count_plu = max_count_plu;
    }

    @Override
    public void save(String z) {

        StringBuilder sb = new StringBuilder();
        // УИ3 + # + кф + # + кк + #
        for(MetaHalfAutoSet ma : listMeta){
            sb.append(ma.getNameYu3Field()).append("#").append(ma.getCount_qf_min()).append("#").append(ma.getMax_count_plu()).append("#").append(System.lineSeparator());
        }
        System.out.println(sb.toString());

        FileWriter fw = null;
        try {
            fw = new FileWriter(path_settingsMHF);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert fw != null;
            fw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load() {

        listMeta.clear();

        File file = new File(path_settingsMHF);

        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(fr);


        while (sc.hasNextLine()){
            String j = sc.nextLine(); // Вызов разделителя
                                      // добавление в listMeta;
            patternrd(j);
        }

        sc.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = null;

    }

    public void patternrd(String zxc){

        // Данные каждого класса это три
        // переменные которые мы обработаем
        // по отдельности

        Pattern p = Pattern.compile("(.*?)#");
        Matcher m = p.matcher(zxc);

        String res1 = "";
        String res2 = "";
        String res3 = "";
        int x = 0;
        int qf = 0;
        int qplu = 0;

        while (m.find()){

            if(x==0){res1 = m.group(1);}
            if(x==1){res2 = m.group(1);}
            if(x==2){res3 = m.group(1);}
            x++;
           }

        try {
            if (res2.length() > 0) {
                qf = Integer.parseInt(res2);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка присвоения числа Meta Hand_a + 142 line");
        }

        try {
            if (res3.length() > 0) {
                qplu = Integer.parseInt(res3);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка присвоения числа Meta Hand_a + 151 line");
        }

        listMeta.add(new MetaHalfAutoSet(res1,qf,qplu));

    }

    public static void tryAddNewGroup(String s){

        for(MetaHalfAutoSet  name: listMeta){

            // Если такая группа уже есть, то добавлять мы ее не будем
            if(s.equals(name.getNameYu3Field())){new Modal_Error().set_erroe_messege("Такая группа уже есть!");
            return;
            }
        }
        listMeta.add(new MetaHalfAutoSet(s,7,3));
        new Modal_Error().set_erroe_messege("Группа " + s + " Успешно добавлена ");

    }

    public static void trytoDeletePosition(MetaHalfAutoSet obs){
        listMeta.remove(obs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaHalfAutoSet that = (MetaHalfAutoSet) o;
        return Objects.equals(nameYu3Field, that.nameYu3Field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameYu3Field);
    }
}
