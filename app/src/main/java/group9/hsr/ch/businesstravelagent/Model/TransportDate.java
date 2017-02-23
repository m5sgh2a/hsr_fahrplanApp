package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import group9.hsr.ch.businesstravelagent.R;

import static android.icu.util.Calendar.*;

public class TransportDate {

    private Activity activity = null;
    private HelperDate helperDate = new HelperDate();
    private final String dateFormatDisplay = "EEE dd.MM.yyyy";
    private final String dateFormatSystem = "yyyy-MM-dd";

    public TransportDate(Activity activity) {
        this.activity = activity;
    }

    private Button GetDateButton() {
        return (Button) activity.findViewById(R.id.transportDateButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ShowCurrentDateOnButton() {
        Calendar date = helperDate.GetCurrentDate();
        SetDate(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void SetDate(Calendar date)
    {
        Button dateButton = GetDateButton();
        dateButton.setText(helperDate.GetDateFormatted(date, dateFormatDisplay));
        dateButton.setTag(helperDate.GetDateFormatted(date, dateFormatSystem));
    }

    public void Register() {
        final Button dateButton = GetDateButton();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar calendar = getInstance();
                int year = calendar.get(YEAR);
                int month = calendar.get(MONTH);
                int day = calendar.get(DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar date = helperDate.CreateDate(year, month, day);
                        SetDate(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
