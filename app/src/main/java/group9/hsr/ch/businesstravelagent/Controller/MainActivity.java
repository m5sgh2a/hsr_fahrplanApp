package group9.hsr.ch.businesstravelagent.Controller;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;
import group9.hsr.ch.businesstravelagent.R;

public class MainActivity extends AppCompatActivity {
    /*private IOpenTransportRepository repo;

    public MainActivity() throws OpenDataTransportException {
        repo = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
    }

    ConnectionList connectionList = repo.searchConnections("Buchs SG", "Zürich HB");
    */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
