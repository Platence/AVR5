package fin_kp_AI;

import error_package.Modal_Error;
import sample.Main;
import warehouse_plu.Ostatku;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SetPriorityOstatkuList {

    public static LocalDate dateNow  = LocalDate.now();

    public static LocalDate dateNPR1  = LocalDate.now();
    public static LocalDate dateNPR2 = LocalDate.now();
    public static LocalDate dateNPR3 = LocalDate.now();

    public void start(){

        if(!testDateTime()){new Modal_Error().set_erroe_messege("Конверт даты доложил об ошибке : " + System.lineSeparator()+""  + System.lineSeparator()+"" +
                "Пожалуйста, обратитесь к разработчику и передайте ваш файл непродаваек." + System.lineSeparator()+"" +
                "Добавить непродоваемые товары невозможно. Автозамена"+System.lineSeparator()+"" +
                "Возможно файл непродаваемых товаров ещё не был загружен."+System.lineSeparator()+
        "Убедитесь что файл NPE.xlsx находится в выбранной вами директории");return;

        }

        DateTimeFormatter formatterImport = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterSale = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        dateNPR1 = dateNPR1.minusDays(37);
        dateNPR2 = dateNPR2.minusDays(20);
        dateNPR3 = dateNPR3.minusDays(6);


        for(Ostatku ostatku : Main.classOstatku){


            if(!ostatku.isneprodam){continue;}

            if(ostatku.lastDateSale.equals("EMP") && ostatku.lastDateImport.equals("EMP")){
                ostatku.priority_NPR = 1; continue;
            }

            if(!ostatku.lastDateSale.equals("EMP") && !ostatku.lastDateImport.equals("EMP")) {


                LocalDate tempDateImport = LocalDate.parse(ostatku.lastDateImport, formatterImport);
                LocalDate tempDateSale = LocalDate.parse(ostatku.lastDateSale, formatterSale);

                if (tempDateImport.isBefore(dateNPR1) && tempDateSale.isBefore(dateNPR1)) {
                    ostatku.priority_NPR = 1;
                    continue;
                }
                if (tempDateImport.isBefore(dateNPR2) && tempDateSale.isBefore(dateNPR2)) {
                    ostatku.priority_NPR = 2;
                    continue;
                }
                if (tempDateImport.isBefore(dateNPR3) && tempDateSale.isBefore(dateNPR3)) {
                    ostatku.priority_NPR = 3;
                    continue;
                }

                if (tempDateImport.isBefore(dateNPR1) && tempDateSale.isBefore(dateNPR2)) {
                    ostatku.priority_NPR = 2;
                    continue;
                }
                if (tempDateImport.isBefore(dateNPR2) && tempDateSale.isBefore(dateNPR3)) {
                    ostatku.priority_NPR = 3;
                    continue;
                }

                if (tempDateImport.isBefore(dateNPR1) && tempDateSale.isBefore(dateNPR1)) {
                    ostatku.priority_NPR = 1;
                    continue;
                }
                if (tempDateImport.isBefore(dateNPR2) && tempDateSale.isBefore(dateNPR2)) {
                    ostatku.priority_NPR = 2;
                    continue;
                }
                if (tempDateImport.isBefore(dateNPR3) && tempDateSale.isBefore(dateNPR3)) {
                    ostatku.priority_NPR = 3;
                    continue;
                }
            }

            if(ostatku.lastDateSale.length()>3 && !ostatku.lastDateImport.equals("EMP")) {
                ostatku.priority_NPR = 2;continue;
            }

            if(ostatku.lastDateSale.length()>3 && ostatku.lastDateImport.equals("EMP")) {
                ostatku.priority_NPR = 2;continue;
            }

            if(!ostatku.lastDateSale.equals("EMP") && ostatku.lastDateImport.length()>3) {
                ostatku.priority_NPR = 3;continue;
            }

              ostatku.priority_NPR = 4;

        }

       // showInfo();
    }

    public static boolean testDateTime(){

        /**
         *  тестирование внутренней даты, если форматтер не подходит, бросить парсинг.
         */

        DateTimeFormatter formatterImport = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterSale = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for(Ostatku ost : Main.classOstatku){
            if(ost.isneprodam){
                if(!ost.lastDateImport.equals("EMP")){
                    if(!ost.lastDateSale.equals("EMP")){
                        try{
                            LocalDate tempDateImport = LocalDate.parse(ost.lastDateImport,formatterImport);
                            LocalDate tempDateSale = LocalDate.parse(ost.lastDateSale,formatterSale);
                            return true;
                        }
                        catch (Exception e){
                            return false;
                        }
                    }
                }
            }
        }

        return false;

    }

    public static void showInfo(){

//        for(Ostatku z : Main.classOstatku){
//            if(z.priority_NPR==0){
//                System.out.println(z.getPlu() + " / " + z.lastDateImport + " / " + z.lastDateSale);
//            }
//        }

        for(Ostatku z : Main.classOstatku){
            if(z.priority_NPR==1){
                System.out.println(z.getPlu() + " / " + z.lastDateImport + " / " + z.lastDateSale);
                System.out.println(z.to_String_detail(z));
                System.out.println(z.toString());

            }
        }
//        for(Ostatku z : Main.classOstatku){
//            if(z.priority_NPR==2){
//                System.out.println(z.getPlu() + " / " + z.lastDateImport + " / " + z.lastDateSale);
//            }
//        }
//        for(Ostatku z : Main.classOstatku){
//            if(z.priority_NPR==3){
//                System.out.println(z.getPlu() + " / " + z.lastDateImport + " / " + z.lastDateSale);
//            }
//        }
    }


}
