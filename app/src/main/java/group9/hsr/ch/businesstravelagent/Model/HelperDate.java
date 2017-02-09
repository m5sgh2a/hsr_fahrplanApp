package group9.hsr.ch.businesstravelagent.Model;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

public class HelperDate {
    public Calendar CreateDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

    public String GetDateFormatted(Calendar calendar, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(calendar);
    }

    public Calendar GetCurrentDate() {
        return Calendar.getInstance();
    }
}
