package warehouse_plu;

import java.util.LinkedList;
import java.util.Objects;

public class BaseGroup {

    private String name;
    private short index;

    public static LinkedList <BaseGroup> linkedgroup = new LinkedList<BaseGroup>();
    private static final String defauleresponse = "UNWKNOWN";

    public BaseGroup(String name, short index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getIndex() {
        return index;
    }

    public void setIndex(short index) {
        this.index = index;
    }

    public static String getMeNameFromIndex(short index){
        for (BaseGroup baseGroup : linkedgroup){
            if(baseGroup.getIndex()==index){
                return baseGroup.getName();
            }
        }
        return defauleresponse;
    }

    public static short addnewGroupIndexAndName(String name){
        for (BaseGroup baseGroup : linkedgroup){
            if (baseGroup.getName().equals(name)){return baseGroup.getIndex();}
        }
        linkedgroup.add(new BaseGroup(name, (short) (linkedgroup.size())));
        short s = (short) linkedgroup.size();
        s--;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseGroup baseGroup = (BaseGroup) o;
        return getName().equals(baseGroup.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
