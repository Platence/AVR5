package tgODDS;

import java.util.LinkedList;

public class ControlListObjects {

    public LinkedList<InfoForTable> list;

    public ControlListObjects() {
        list = new LinkedList<>();
        UpdateList updateList = new UpdateList();
        updateList.addToListSomeObjects(this.list);
    }
}
