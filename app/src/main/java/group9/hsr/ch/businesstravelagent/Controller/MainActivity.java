package group9.hsr.ch.businesstravelagent.Controller;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Date;
import java.util.Locale;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import group9.hsr.ch.businesstravelagent.R;

public class MainActivity extends AppCompatActivity {
    /*private IOpenTransportRepository repo;

    public MainActivity() throws OpenDataTransportException {
        repo = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
    }

    ConnectionList connectionList = repo.searchConnections("Buchs SG", "Zürich HB");
    */

    private void SetupConnectionDateButton()
    {
        final Button dateButton = (Button)findViewById(R.id.connectionDateButton);

        dateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Locale locale = getResources().getConfiguration().getLocales().get(0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd.MM.yyyy", locale);
                        Calendar calendarSelect = Calendar.getInstance();
                        calendarSelect.set(year, month, day);

                        dateButton.setText(dateFormat.format(calendarSelect));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void SetupConnectionTimeButton()
    {
        final Button timeButton = (Button)findViewById(R.id.connectionTimButton);

        timeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar calendarSelect = Calendar.getInstance();
                        calendarSelect.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hour, minute, 0);
                        Locale locale = getResources().getConfiguration().getLocales().get(0);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm", locale);

                        timeButton.setText(dateFormat.format(calendarSelect));
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    private void SetupOppositeDirection()
    {
        ImageButton oppositeDirection = (ImageButton)findViewById(R.id.oppositeDirectionButton);
        oppositeDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText start = (EditText)findViewById(R.id.startLocationText);
                EditText end = (EditText)findViewById(R.id.endLocationText);

                if (start.getText().toString().length() > 0 && end.getText().toString().length() > 0)
                {
                    String startText = start.getText().toString();
                    start.setText(end.getText().toString());
                    end.setText(startText);
                }
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupConnectionDateButton();
        SetupConnectionTimeButton();
        SetupOppositeDirection();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_menu);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.activity_About: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
