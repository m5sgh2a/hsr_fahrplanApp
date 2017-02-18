package group9.hsr.ch.businesstravelagent.Model;

import android.location.Location;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;
import ch.schoeb.opendatatransport.model.StationList;

public class TransportConnection {

    private IOpenTransportRepository repo;
    private ConnectionList connectionList;
    private String startLocation;
    private String endLocation;

    private void OpenRepository() {
        if (repo == null) {
            repo = OpenTransportRepositoryFactory.CreateOnlineOpenTransportRepository();
        }
    }

    private void SearchConnection() {
        try {
            connectionList = repo.searchConnections(startLocation, endLocation);
        } catch (OpenDataTransportException e) {
            connectionList = null;
        }
    }

    public ConnectionList GetConnection(String startLocation, String endLocation) {
        OpenRepository();
        SearchConnection();

        return connectionList;
    }

    public StationList GetStationName(String location, String type )
    {
        StationList list =null;
        try{
            OpenRepository();
            list = repo.findStations(location, type);
        }
        catch(OpenDataTransportException e)
        {
            //do nothing
        }
        return list;
    }
}
