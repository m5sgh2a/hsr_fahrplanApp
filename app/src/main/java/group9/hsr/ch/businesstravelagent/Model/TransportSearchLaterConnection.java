package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import group9.hsr.ch.businesstravelagent.R;

public class TransportSearchLaterConnection {
    private Activity activity = null;
    private HelperDate helperDate = new HelperDate();

    public TransportSearchLaterConnection(Activity activity) {
        this.activity = activity;
    }

    private Button GetLaterConnectionButton() {
        return (Button) activity.findViewById(R.id.transportSearchLaterConnectionButton);
    }

    public void Register() {
        final Button lateConnectionButton = GetLaterConnectionButton();

        lateConnectionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                TransportSearch transportSearch = new TransportSearch(activity);
                transportSearch.ExecuteSearchLaterConnection();
            }
        });
    }

    public void DisableButton() {
        GetLaterConnectionButton().setEnabled(false);
    }
}
