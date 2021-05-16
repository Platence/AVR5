package newEngine.StringClass;

public class SecondLevelDest {



    public boolean startSecLev(char[] arr,int index){

        StringBuilder stringFull = new StringBuilder();

        int indexEnd = 0;

        // Выясняем конец строки,
        // Полученный результат отправляем в обработчик.

        for(int i = index ; i < arr.length ; i++){
            stringFull.append(arr[i]);


            if(stringFull.toString().contains(DestroyStringBuilder.example)){
                indexEnd = i;
                //System.out.println("Start = " + index  + " End = " + indexEnd);
                new ThirdClassAtempt().getAttempt(stringFull);
                stringFull.setLength(0);
                return true;
            }

        }
        return false;
    }


    // Sap READ

    public boolean startSecLev(char[] arr,int index, int operation){

        StringBuilder stringFull = new StringBuilder();

        int indexEnd = 0;

        // Выясняем конец строки,
        // Полученный результат отправляем в обработчик.

        for(int i = index ; i < arr.length ; i++){
            stringFull.append(arr[i]);

            if(stringFull.toString().contains(DestroyStringBuilder.example)){
                indexEnd = i;
                //System.out.println("Start = " + index  + " End = " + indexEnd);
                new ThirdClassAtempt().getAttempt(stringFull,0);
                stringFull.setLength(0);
                arr = null;
                return true;
            }

        }
        arr = null;
        return false;
    }

}
