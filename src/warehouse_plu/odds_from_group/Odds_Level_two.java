package warehouse_plu.odds_from_group;

import java.util.ArrayList;
import java.util.TreeMap;

public class Odds_Level_two extends Odds_level_One {


    private String name;
    private int sum;

    public Odds_Level_two(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
