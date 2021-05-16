package exportXLSX.exportAppPrikaz;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTomorrow {


    public String getMeDateTomorrow(String s){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date currentDate = new Date();
        try {
            currentDate = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(currentDate);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate = instance.getTime();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        return df.format(newDate);

        /*
        Класс преобразует поступающую дату, и прибавляет к ней один день.

         */

    }
}
