package wak.system;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by chris_000 on 23.10.2015.
 */
public class Formatter {

    public static String dateFormatter(Timestamp stamp){
        Calendar start = Calendar.getInstance();
        start.setTimeInMillis( stamp.getTime() );
        java.util.Date date = start.getTime();
        DateFormat date_format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return  date_format.format(date);
    }
    /**
     * Benötigt einen Double Wert, der dann als € Einheit in einem String zurückgegeben wird.
     * @param d
     * @return
     */
    public static  String formatdouble(Double d){
        DecimalFormat format = new DecimalFormat("#####0.00");
        return format.format(d)+"&#8364";
    }
}
