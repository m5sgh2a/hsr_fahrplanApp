package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.R;

public class TransportSearch {

    private Activity activity;
    private String resultString;

    public TransportSearch(Activity activity) {
        this.activity = activity;
    }

    private Button GetSearchButton() {
        return (Button) activity.findViewById(R.id.transportSearchButton);
    }

    private ProgressBar GetProgressBar() {
        return (ProgressBar) activity.findViewById(R.id.transportSearchProgressBar);
    }

    private EditText GetStartLocationText() {
        return (EditText) activity.findViewById(R.id.startLocationText);
    }

    private EditText GetEndLocationText() {
        return (EditText) activity.findViewById(R.id.endLocationText);
    }

    public void Register() {
        final Button transportSearchButton = GetSearchButton();

        transportSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecuteSearch();
            }
        });
    }

    private void ExecuteSearch(){
        TransportSearchValidate validateSearch = new TransportSearchValidate(
                GetStartLocationText().getText().toString(),
                GetEndLocationText().getText().toString());
        validateSearch.Execute();

        if (validateSearch.AreErrorsAvaialble()) {
           new DialogBox().ShowOkMessage(
                   "Fehler",
                   validateSearch.GetErrorMessageAsOneString(),
                   activity);
        }
        else
        {
            SearchWorker worker = new SearchWorker(GetStartLocationText().getText().toString(), GetEndLocationText().getText().toString());
            worker.execute();
        }
    }

    private class SearchWorker extends AsyncTask<Void, Void, Void> {

        private String startLocation;
        private String destination;

        public SearchWorker(String _startLocation, String _destination)
        {
            startLocation = _startLocation;
            destination = _destination;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            TransportConnection transportConnection = new TransportConnection();
            ConnectionList result = transportConnection.GetConnection(startLocation, destination);


            if ((result == null) || (result.getConnections().size() == 0)) {
                resultString = "Keine Verbindung gefunden";
            } else {
                //String test = result.getConnections().get(0).getDuration();
                //transportSearchButton.setText(result.toString());
                //transportSearchButton.setText("erfolgreich");

                resultString = "erfolgreich";
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Button searchButton = GetSearchButton();
            searchButton.setText("Bitte warten...");
            searchButton.setEnabled(false);

            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Button searchButton = GetSearchButton();
            searchButton.setText("Suchen");
            searchButton.setEnabled(true);

            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
        }
    }
}
