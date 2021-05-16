package parseIntFromTextField;

import error_package.Modal_Error;

public class ParseField {


    public  int getIntFromString(String word){

        try {
             return Integer.parseInt(word);
        }
        catch (Exception e){
            new Modal_Error().set_erroe_messege("Невозможно прочесть число из строки [" + word+ "]");
        }

        return 0;
    }
}
