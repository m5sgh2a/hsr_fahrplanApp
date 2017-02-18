package group9.hsr.ch.businesstravelagent.Model;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.location.Location;

import group9.hsr.ch.businesstravelagent.Controller.MainActivity;
import group9.hsr.ch.businesstravelagent.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by station on 18/02/2017.
 */

public class ClosestStation implements GoogleApiClient.OnConnectionFailedListener
{

    private MainActivity mainActivity = null;
    private GoogleApiClient googleApiClient;

    public ClosestStation(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    private Button GetOppositeDirectionButton() {
        return (Button) mainActivity.findViewById(R.id.nextStationButton);
    }

    public void Register()
    {
        ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        googleApiClient=new GoogleApiClient.Builder(mainActivity)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        final Button oppositeDirectionButton = GetOppositeDirectionButton();
        oppositeDirectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED)
                    {
                        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                        //TODO
                    }
                }
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
}
