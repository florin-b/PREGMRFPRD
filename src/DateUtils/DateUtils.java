package DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils
{
  public static Date addDays(Date date, int days) {
    Calendar cal = Calendar.getInstance(new Locale("ro"));
    cal.setTime(date);
    cal.add(5, days);
    return cal.getTime();
  }
  
  public static int diffHours(Date dateStart, Date dateStop) {
    long diff = dateStop.getTime() - dateStart.getTime();
    
    long diffHours = diff / 3600000L % 24L;


    
    return Math.round((float)diffHours);
  }
}
