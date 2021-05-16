package decriptor;

public class Cription {


    public static StringBuilder criptAllWord(StringBuilder sb){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < sb.length(); i ++){
            char z = sb.charAt(i);
            char newCHAR = replaceSby(z);
            stringBuilder.append(newCHAR);
        }
        return stringBuilder;
    }

    public static char replaceSby(char z){

        return 0;
    }
}
