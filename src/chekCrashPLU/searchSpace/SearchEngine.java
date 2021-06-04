package chekCrashPLU.searchSpace;

import all_controllers.logicAnalitic.GlobalRulesBlock;
import animation_elements.WaitingAlarm;
import decriptor.ConsoleAVR;
import sample.Main;
import warehouse_plu.Ostatku;

import java.util.HashSet;

public class SearchEngine {

    /**
     * Поиск пересортов
     */

    private static int size = 1;
    private StringBuilder ex11 = new StringBuilder();
    private StringBuilder ex22 = new StringBuilder();

    private StringBuilder ex1 = new StringBuilder();
    private StringBuilder ex2 = new StringBuilder();

    public HashSet<Ostatku> getListSearch(){

        /**
         * вложенная проверка объектов друг с другом
         *
         *  Как не проверять то, что у же проверялось ? ...
         */

        WaitingAlarm wa = new WaitingAlarm();
        Thread thread   = new Thread(wa);
        thread.setDaemon(true);
        thread.start();

        HashSet<Ostatku> listSet = new HashSet<>();
        int count = 0;

        ConsoleAVR.printlnn("Инициирована проверка Пересортов...");

        for(Ostatku ost : Main.classOstatku){
            if(listSet.contains(ost)){continue;}
            ex1.setLength(0);
            ex2.setLength(0);
            count++;
            wa.setCountPercent(count);
            if (ost.getOddsCOUNT()==0){continue;}
            for (Ostatku ex2 : Main.classOstatku){
                if(listSet.contains(ex2)){continue;}
                if(ost.getPlu().equals(ex2.getPlu())){continue;}
                if(ex2.getOddsCOUNT()==0){continue;}
                if (!mainRules(ost,ex2)){continue;}

                 if (firstBoolean(ost,ex2)){
                    listSet.add(ost);
                    listSet.add(ex2);
                     System.out.println("SUCH 1 LVL : " + ost.getName() + " : " + ex2.getName());
                    break;
                 }

                 if (secondBoolean(ost,ex2)){
                     listSet.add(ost);
                     listSet.add(ex2);
                     System.out.println("SUCH 2 LVL : " + ost.getName() + " : " + ex2.getName());
                     break;
                 }
            }

        }
        wa.closeAlert();
        return listSet;
    }

    private boolean firstBoolean(Ostatku oneE,Ostatku twoE){
        /**
         * Первая проверка - на схожесть PLU
         */

        //Перевод в число
        //Проверка на одинаковую группу

        if (!oneE.getYu3().equals(twoE.getYu3())){return false;}

        ex11.append(oneE.getPlu());
        ex22.append(twoE.getPlu());

        long x = parseLong(ex11);
        long y = parseLong(ex22);

        ex11.setLength(0);
        ex22.setLength(0);

        // Сравнение по последнему знаку

        if(x+1==y){return true;}
        if(x-1==y){return true;}

        else return false;

    }

    private boolean secondBoolean(Ostatku oneE,Ostatku twoE){
        /**
         *  2 слова сравниваются друг с другом на 70 % схожести
         */

        if (!oneE.getYu3().equals(twoE.getYu3())){return false;}

        ex1.append(oneE.getName());
        ex2.append(twoE.getName());

        return ofIndexSTB();

    }

    private long parseLong(StringBuilder x){
        try{
            return Long.parseLong(x.toString());
        }
        catch (Exception e){
            return 0;
        }
    }

    private boolean ofIndexSTB(){
        // Сравнение в %
        // Узнаём размер
        int size1 = ex1.length();
        int size2 = ex2.length();

        // Если разница слишком большая, сращу выходим.

        int res = size1 - size2;
        if(Math.abs(res)>6){return false;}

        try {
            return ex1.substring(0, size1/3 + size).equals(ex2.substring(0, size2/3 + size));
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean mainRules(Ostatku ex1,Ostatku ex2){

        if(ex1.getOddsCOUNT()<0 && ex2.getOddsCOUNT()<0){return false;}
        if(ex1.getOddsCOUNT()>0 && ex2.getOddsCOUNT()>0){return false;}

        // Не СП
        if(!GlobalRulesBlock.nonBred(ex1)){return false;}
        if(!GlobalRulesBlock.nonBred(ex2)){return false;}

        // Только с расхождениями
        if(ex1.getOddsCOUNT()==0){return false;}
        if(ex2.getOddsCOUNT()==0){return false;}

        double x1 = Math.abs(ex1.getOddsCOUNT());
        double x2 = Math.abs(ex2.getOddsCOUNT());

        double res = x1-x2;

        if(res>2.0){return false;}

        return true;
    }
}
