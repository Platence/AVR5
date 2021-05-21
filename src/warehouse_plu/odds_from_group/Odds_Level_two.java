package warehouse_plu.odds_from_group;

import java.util.ArrayList;
import java.util.TreeMap;

public class Odds_Level_two extends Odds_level_One {


    private char [] name;
    private int sum;

    public Odds_Level_two(String name, int sum) {
        this.name = name.toCharArray();
        this.sum = sum;
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
}
