package group9.hsr.ch.businesstravelagent.Model;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import ch.schoeb.opendatatransport.model.Coordinate;
import ch.schoeb.opendatatransport.model.StationList;
import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.location.Location;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by station on 18/02/2017.
 */

public class ClosestStation implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks
{

    private MainActivity mainActivity = null;
    private GoogleApiClient googleApiClient;
    private Geocoder geocoder;

    private Button GetNextStationButton() {
        return (Button) mainActivity.findViewById(R.id.nextStationButton);
    }

    private EditText GetStartLocationText() {
        return (EditText) mainActivity.findViewById(R.id.startLocationText);
    }

    private ProgressBar GetProgressBar() {
        return (ProgressBar) mainActivity.findViewById(R.id.transportSearchProgressBar);
    }

    public ClosestStation(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void Register()
    {
        geocoder = new Geocoder(mainActivity, Locale.getDefault());

        //ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        googleApiClient=new GoogleApiClient.Builder(mainActivity)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        final Button nextStationButton = GetNextStationButton();
        nextStationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                /*if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED)
                    {*/
                        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                        TransportConnection transportConnection = new TransportConnection();
                        try {
                            List<android.location.Address> list=geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                            if(list != null && list.size()>0)
                            {
                                Coordinate coord = new Coordinate();
                                coord.setY(location.getLongitude());
                                coord.setX(location.getLatitude());
                                ClosestStationWorker worker = new ClosestStationWorker(list.get(0).getLocality(), "station", coord);
                                worker.execute();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    /*}
                }*/
            }
        });
    }

    public void onResume()
    {
        googleApiClient.connect();
    }

    public void onPause()
    {
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        apiAvailability.getErrorDialog(mainActivity, connectionResult.getErrorCode(), 1).show();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        //empty
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        //empty
    }



    class ClosestStationWorker extends AsyncTask<Void, Void, StationList>
    {
        String resultString;
        String locationName;
        String type;
        Coordinate startingPoint;

        public ClosestStationWorker(String _locationName, String _type, Coordinate _startingPoint)
        {
            locationName = _locationName;
            type = _type;
            startingPoint = _startingPoint;
        }
        @Override
        protected StationList doInBackground(Void... voids)
        {
            TransportConnection transportConnection = new TransportConnection();
            StationList result = transportConnection.GetStationName(locationName, type);

            if ((result == null) || (result.getStations().size() == 0)) {
                resultString = "Keine Station gefunden";
            }
            else
            {
                resultString = "erfolgreich";
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Button nexLocationButton = GetNextStationButton();
            nexLocationButton.setEnabled(false);

            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(StationList stationList) {
            super.onPostExecute(stationList);

            double minDist = 0;
            int posMinDist = 0;
            for(int i=0; i< stationList.getStations().size(); i++)
            {
                Number lattitude = stationList.getStations().get(i).getCoordinate().getX();
                Number longitude = stationList.getStations().get(i).getCoordinate().getY();
                double x = pow(lattitude.doubleValue() - startingPoint.getX().doubleValue(),2);
                double y = pow(longitude.doubleValue() - startingPoint.getY().doubleValue(),2);
                double dist = abs(sqrt(x+y));
                if(i==0)
                {
                    minDist = dist;
                }
                else if(dist<minDist)
                {
                    minDist = dist;
                    posMinDist = i;
                }
            }
            String stationName = stationList.getStations().get(posMinDist).getName();
            EditText startLocationText = GetStartLocationText();
            startLocationText.setText(stationName);

            Button nexLocationButton = GetNextStationButton();
            nexLocationButton.setEnabled(true);

            ProgressBar progressBar = GetProgressBar();
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
        }
    }

}
