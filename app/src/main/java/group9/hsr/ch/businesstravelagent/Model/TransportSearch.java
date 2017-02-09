package group9.hsr.ch.businesstravelagent.Model;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class TransportSearch {

    private MainActivity mainActivity = null;

    public TransportSearch(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private Button GetSearchButton() {
        return (Button) mainActivity.findViewById(R.id.transportSearchButton);
    }

    public void Register() {
        final Button transportSearchButton = GetSearchButton();
        {
            transportSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TransportConnection transportConnection = new TransportConnection();
                    ConnectionList result = transportConnection.GetConnection("Buchs SG", "Zürich HB");

                    String resultString;
                    if (result == null)
                    {
                        resultString = "error";
                    }
                    else {
                        transportSearchButton.setText(result.toString());
                    }
                }
            });
        }
    }
}
