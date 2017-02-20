package group9.hsr.ch.businesstravelagent.Model;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class HelperDate {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Calendar CreateDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String GetDateFormatted(Calendar calendar, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(calendar);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Calendar GetCurrentDate() {
        return Calendar.getInstance();
    }
}
