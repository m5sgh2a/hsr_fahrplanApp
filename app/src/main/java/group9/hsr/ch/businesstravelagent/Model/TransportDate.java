package group9.hsr.ch.businesstravelagent.Model;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class TransportDate {

    private MainActivity mainActivity = null;
    private HelperDate helperDate = new HelperDate();
    private final String dateFormat = "EEE dd.MM.yyyy";

    public TransportDate(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private Button GetDateButton() {
        return (Button) mainActivity.findViewById(R.id.transportDateButton);
    }

    public void ShowCurrentDateOnButton() {;
        Calendar calendar = helperDate.GetCurrentDate();
        GetDateButton().setText(helperDate.GetDateFormatted(calendar, dateFormat));
    }

    public void Register() {
        final Button dateButton = GetDateButton();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(mainActivity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar calendar = helperDate.CreateDate(year, month, day);
                        dateButton.setText(helperDate.GetDateFormatted(calendar, dateFormat));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
