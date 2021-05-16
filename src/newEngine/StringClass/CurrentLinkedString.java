package newEngine.StringClass;


import java.util.LinkedList;


public class CurrentLinkedString {


    public static LinkedList<String> linked = new LinkedList<>();


    public static void clearMap(){
        linked.clear();
    }

    public static String getCurrentLinkd(int index) throws Exception{

        return linked.get(index);

        // Может выкинуть ошибку, если индекса нет
    }
}
