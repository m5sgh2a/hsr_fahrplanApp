package group9.hsr.ch.businesstravelagent.Model;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class TransportTime {

    private MainActivity mainActivity = null;
    private HelperDate helperDate = new HelperDate();
    private final String dateFormat = "HH:mm";

    public TransportTime(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private Button GetDateButton() {
        return (Button) mainActivity.findViewById(R.id.transportTimeButton);
    }

    public void ShowCurrentTimeOnButton() {;
        Calendar calendar = helperDate.GetCurrentDate();
        GetDateButton().setText(helperDate.GetDateFormatted(calendar, dateFormat));
    }

    public void Register() {
        final Button timeButton = GetDateButton();

        timeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(mainActivity, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar calendar = helperDate.GetCurrentDate();
                        calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hour, minute, 0);

                        timeButton.setText(helperDate.GetDateFormatted(calendar, dateFormat));
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }
}
