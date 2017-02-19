package group9.hsr.ch.businesstravelagent.Model;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.Controller.TransportAdapter;
import group9.hsr.ch.businesstravelagent.R;

public class TransportSearch {

    private Activity activity;
    private ConnectionList result;
    private TransportConnection transportConnection = new TransportConnection();
    private TransportSearchParameter searchParameter;
    private HelperDate helperDate = new HelperDate();

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

    private ListView GetTransportConnecdtionListView() {
        return (ListView) activity.findViewById(R.id.transportConnectionListView);
    }

    private ToggleButton GetArrivalToggle() {
        return (ToggleButton) activity.findViewById(R.id.arrivalToggle);
    }

    private Button GetDateButton() {
        return (Button) activity.findViewById(R.id.transportDateButton);
    }

    private Button GetTimeButton() {
        return (Button) activity.findViewById(R.id.transportTimeButton);
    }

    public void Register() {
        final Button transportSearchButton = GetSearchButton();

        transportSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSearchParameter();
                ExecuteSearch();
            }
        });
    }

    private void ExecuteSearch() {
        TransportSearchValidate validateSearch = new TransportSearchValidate(
                GetStartLocationText().getText().toString(),
                GetEndLocationText().getText().toString());
        validateSearch.Execute();

        if (validateSearch.AreErrorsAvailable()) {
            new DialogBox().ShowOkMessage(
                    "Fehler",
                    validateSearch.GetErrorMessageAsOneString(),
                    activity);
        } else {
            new SearchWorker().execute();
        }
    }

    private void ShowResult() {
        ListView transportConnectionListView = GetTransportConnecdtionListView();
        TransportAdapter transportAdapter = new TransportAdapter(activity, result);
        transportConnectionListView.setAdapter(transportAdapter);
    }

    private void SetSearchParameter() {
        searchParameter = new TransportSearchParameter(
                GetStartLocationText().getText().toString(),
                GetEndLocationText().getText().toString(),
                GetDateButton().getTag().toString(),
                GetTimeButton().getTag().toString(),
                GetArrivalToggle().isChecked());
    }

    private class SearchWorker extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            //"Buchs SG", "Zürich HB"
            result = transportConnection.GetConnection(searchParameter);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            FormatSearchButtonBeforeSearch();
            FormatProgressBarBeforeSearch();
        }

        private void FormatProgressBarBeforeSearch() {
            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        private void FormatSearchButtonBeforeSearch() {
            Button searchButton = GetSearchButton();
            searchButton.setText("Bitte warten...");
            searchButton.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            FormatProgressBarAfterSearch();
            FormatSearchButtonAfterSearch();
            ShowResult();
        }

        private void FormatProgressBarAfterSearch() {
            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
        }

        private void FormatSearchButtonAfterSearch() {
            Button searchButton = GetSearchButton();
            searchButton.setText("Suchen");
            searchButton.setEnabled(true);
        }
    }
}
