package group9.hsr.ch.businesstravelagent.Model;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class TransportSearch extends AsyncTask<Void, Void, Void> {

    private MainActivity mainActivity = null;
    String resultString;

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
                    execute();
                }
            });
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        TransportConnection transportConnection = new TransportConnection();
        ConnectionList result = transportConnection.GetConnection("Buchs SG", "Zürich HB");

        if ((result == null) || (result.getConnections().size() == 0))
        {
            resultString = "Keine Verbindung gefunden";
        }
        else {
            //String test = result.getConnections().get(0).getDuration();
            //transportSearchButton.setText(result.toString());
            //transportSearchButton.setText("erfolgreich");
            resultString = "erfolgreich";
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Button transportSearchButton = GetSearchButton();

        transportSearchButton.setText(resultString);
    }
}
