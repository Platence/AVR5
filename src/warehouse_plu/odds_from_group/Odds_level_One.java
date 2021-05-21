package warehouse_plu.odds_from_group;

import java.util.ArrayList;
import java.util.TreeMap;

public class Odds_level_One implements Comparable<Odds_level_One>{


    public static TreeMap<Odds_level_One,ArrayList<Odds_Level_two>> all_list_map = new TreeMap<>();

    private char []  name;
    private int sum;

    /*

     */


    public Odds_level_One(String name, int sum) {
        this.name = name.toCharArray();
        this.sum = sum;
    }

    public static void clear_map(){
        all_list_map.clear();

    }


    public String getName() {
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < name.length;i++){
            s.append(this.name[i]);
        }
        return s.toString();
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Odds_level_One(){}

    @Override
    public int compareTo(Odds_level_One o) {

        return this.getName().compareTo(o.getName());
    }
}
