package group9.hsr.ch.businesstravelagent.Model;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HelperDate {
    Calendar CreateDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar;
    }

    public Calendar CreateDate(String dateAsString, String dateFormat) {
        Calendar date = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        try {
            date.setTime(format.parse(dateAsString));
        } catch (ParseException e) {
            return null;
        }

        return date;
    }


    public String GetDateFormatted(Calendar calendar, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(calendar);
    }

    Calendar GetCurrentDate() {
        return Calendar.getInstance();
    }

    public Calendar Convert8601StringToDate(final String iso8601string) {
        Calendar calendar = Calendar.getInstance();
        String dateAsString = iso8601string.replace("Z", "+00:00");

        try {
            dateAsString = dateAsString.substring(0, 22) + dateAsString.substring(23);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        try {
            calendar.setTime(format.parse(dateAsString));
        } catch (java.text.ParseException e) {
            return null;
        }

        return calendar;
    }
}
