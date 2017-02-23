package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import group9.hsr.ch.businesstravelagent.R;

public class TransportTime {

    private Activity activity;
    private HelperDate helperDate = new HelperDate();
    private final String dateFormatDisplay = "HH:mm";
    private final String dateFormatSystem = "HH:mm";

    public TransportTime(Activity activity) {
        this.activity = activity;
    }

    private Button GetTimeButton() {
        return (Button) activity.findViewById(R.id.transportTimeButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ShowCurrentTimeOnButton() {
        Calendar currentDate = helperDate.GetCurrentDate();
        SetTime(currentDate);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void SetTime(Calendar date) {
        Button timeButton = GetTimeButton();
        timeButton.setText(helperDate.GetDateFormatted(date, dateFormatDisplay));
        timeButton.setTag(helperDate.GetDateFormatted(date, dateFormatSystem));
    }

    public void Register() {
        final Button timeButton = GetTimeButton();

        timeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar calendar = helperDate.GetCurrentDate();
                        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hour, minute, 0);

                        timeButton.setText(helperDate.GetDateFormatted(calendar, dateFormatDisplay));
                        timeButton.setTag(helperDate.GetDateFormatted(calendar, dateFormatSystem));
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }
}
