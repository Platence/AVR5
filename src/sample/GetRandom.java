package sample;

public class GetRandom {

    /*
       Класс возвращает случайные числа или строки
     */



    public static String getRandomNumber(){

        int random = (int) (Math.random()*100);

        return String.valueOf(random);

    }

    public int getRandomNumberINT(int size){

        return (int) (Math.random()*size);

    }

}
