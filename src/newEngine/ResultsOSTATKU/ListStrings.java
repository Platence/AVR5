package newEngine.ResultsOSTATKU;

import java.util.LinkedList;

public class ListStrings {

    public static LinkedList<String> pluList = new LinkedList<>();
    public static LinkedList<String> nameList = new LinkedList<>();
    public static LinkedList<String> yu2List = new LinkedList<>();
    public static LinkedList<String> yu3List = new LinkedList<>();



    public static String getPluString(short index) throws Exception{
        return  pluList.get(index);
    }

    public static String getNameString(short index) throws Exception{
        return  nameList.get(index);
    }
    public static String getyu2String(short index) throws Exception{
        return  yu2List.get(index);
    }
    public static String getyu3String(short index) throws Exception{
        return  yu3List.get(index);
    }

    public static short addNewStringPLU(String a){

        // Возвращает индекс, и одновременно добавляет позицию если ее нет
        if(pluList.size()==0){pluList.add(a);return 0;}

        for(int i = 0 ; i < pluList.size(); i++){
            if(pluList.get(i).equals(a)){return (short) i;}
        }
        pluList.add(a);
        return (short) pluList.size();
    }

    public static short addNewStringNAME(String a){

        // Возвращает индекс, и одновременно добавляет позицию если ее нет
        if(nameList.size()==0){nameList.add(a);return 0;}

        for(int i = 0 ; i < nameList.size(); i++){
            if(nameList.get(i).equals(a)){return (short) i;}
        }
        nameList.add(a);
        return (short) nameList.size();
    }

    public static short addNewStringYU2(String a){

        // Возвращает индекс, и одновременно добавляет позицию если ее нет
        if(yu2List.size()==0){yu2List.add(a);return 0;}

        for(int i = 0 ; i < yu2List.size(); i++){
            if(yu2List.get(i).equals(a)){return (short) i;}
        }
        yu2List.add(a);
        return (short) yu2List.size();
    }

    public static short addNewStringYU3(String a){

        // Возвращает индекс, и одновременно добавляет позицию если ее нет
        if(yu3List.size()==0){yu3List.add(a);return 0;}

        for(int i = 0 ; i < yu3List.size(); i++){
            if(yu3List.get(i).equals(a)){return (short) i;}
        }
        yu3List.add(a);
        return (short) yu3List.size();
    }

    public static void clearAllList(){
        pluList.clear();
        nameList.clear();
        yu2List.clear();
        yu3List.clear();
    }

}
