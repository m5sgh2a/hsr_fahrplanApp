package group9.hsr.ch.businesstravelagent.Model;

import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import ch.schoeb.opendatatransport.model.StationList;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

/**
 * Created by station on 19/02/2017.
 */

public class Autocomplete {

    private MainActivity mainActivity = null;

    public Autocomplete(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    List<String> dropDownList = new ArrayList<String>(){};

    public void Register()
    {
        final AutoCompleteTextView textView = (AutoCompleteTextView) mainActivity.findViewById(R.id.startLocationText);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_list_item_1,dropDownList);
        textView.setAdapter(adapter);
        textView.setThreshold(2);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //empty
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    AutoCompleteWorker worker = new AutoCompleteWorker(s.toString(), adapter);
                    worker.execute();
                }
            }
        });
    }

    private class AutoCompleteWorker extends AsyncTask<Void, Void, StationList>
    {
        String destination;
        ArrayAdapter<String> adapter;

        public AutoCompleteWorker(String _destination, ArrayAdapter<String> _adapter )
        {
            destination = _destination;
            adapter = _adapter;
        }

        @Override
        protected StationList doInBackground(Void... voids)
        {
            TransportConnection transportConnection = new TransportConnection();
            StationList result = transportConnection.GetStationName(destination, "station");

            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(StationList stationList) {
            super.onPostExecute(stationList);

            dropDownList.clear();
            for(int i=0; i< stationList.getStations().size() && i<5; i++)
            {
                dropDownList.add( stationList.getStations().get(i).getName() );
            }
            adapter.notifyDataSetChanged();
        }
    }
}
