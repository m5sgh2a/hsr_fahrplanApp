package group9.hsr.ch.businesstravelagent.Model;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

public class TransportSearch {

    private MainActivity mainActivity = null;
    String resultString;

    public TransportSearch(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private Button GetSearchButton() {
        return (Button) mainActivity.findViewById(R.id.transportSearchButton);
    }

    private ProgressBar GetProgressBar() {
        return (ProgressBar) mainActivity.findViewById(R.id.transportSearchProgressBar);
    }

    private EditText GetStartLocationText() {
        return (EditText) mainActivity.findViewById(R.id.startLocationText);
    }

    private EditText GetEndLocationText() {
        return (EditText) mainActivity.findViewById(R.id.endLocationText);
    }

    public void Register() {
        final Button transportSearchButton = GetSearchButton();

        transportSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateSearch validateSearch = new ValidateSearch();
                validateSearch.execute();

                if (validateSearch.GetErrorMessage().length() > 0) {

                } else {
                    new SearchWorker().execute();
                }
            }
        });

    }

    class ValidateSearch {
        private String errorMessage;

        public String GetErrorMessage() {
            return this.errorMessage;
        }

        private boolean isStartLocationValid() {
            EditText startLocationText = GetStartLocationText();

            return startLocationText != null && startLocationText.length() > 0;
        }

        private void setStartLocationErrorMessage() {
            if (!isStartLocationValid()) {
                errorMessage = "Bitte geben Sie einen Startort ein.";
            }
        }

        private void setEndLocationErrorMessage() {
            if (!isEndLocationValid()) {
                errorMessage = "Bitte geben Sie einen Zielort ein.";
            }
        }

        private boolean isEndLocationValid() {
            EditText endLocationText = GetEndLocationText();

            return endLocationText != null && endLocationText.length() > 0;
        }

        public void execute() {
            errorMessage = "";
            setStartLocationErrorMessage();
            setEndLocationErrorMessage();
        }
    }

    class SearchWorker extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            TransportConnection transportConnection = new TransportConnection();
            ConnectionList result = transportConnection.GetConnection("Buchs SG", "Zürich HB");


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
