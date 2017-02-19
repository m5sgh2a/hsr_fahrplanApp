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

    private int threshold = 3;

    private AutoCompleteTextView textViewStart;
    private AutoCompleteTextView textViewDestination;
    private ArrayAdapter<String> adapter;

    public void Register()
    {
        textViewStart = (AutoCompleteTextView) mainActivity.findViewById(R.id.startLocationText);
        textViewDestination = (AutoCompleteTextView) mainActivity.findViewById(R.id.endLocationText);
        textViewStart.setThreshold(threshold);
        textViewDestination.setThreshold(threshold);
        textViewStart.addTextChangedListener(new TextWatcher() {
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
                    AutoCompleteWorker worker = new AutoCompleteWorker(s.toString(), true);
                    worker.execute();
                }
            }
        });
        textViewDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    AutoCompleteWorker worker = new AutoCompleteWorker(s.toString(), false);
                    worker.execute();
                }
            }
        });
    }

    private class AutoCompleteWorker extends AsyncTask<Void, Void, StationList>
    {
        String destination;
        boolean start;

        public AutoCompleteWorker(String _destination, boolean _start )
        {
            destination = _destination;
            start = _start;
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

            if(destination.length() >= threshold && stationList != null) {
                for (int i = 0; i < stationList.getStations().size() && i < 5; i++) {
                    dropDownList.add(stationList.getStations().get(i).getName());
                }
            }

            mainActivity.runOnUiThread(new Runnable() {
                public void run() {
                    adapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_list_item_1, dropDownList);
                    if(start) {
                        textViewStart.setAdapter(adapter);
                    }
                    else
                    {
                        textViewDestination.setAdapter(adapter);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
