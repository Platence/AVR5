package animation_elements;

import error_package.SlideError.SlideModalError;

public class Repiat {

    private static int count = 2;

    public Repiat() {
        show();
    }

    private void show(){
        if(count<0){return;}
        new SlideModalError().setMessage("Не забудьте открыть WRS");
        count--;
    }
}
