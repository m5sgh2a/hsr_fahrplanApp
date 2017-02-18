package group9.hsr.ch.businesstravelagent.Model;

import android.location.Location;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;
import ch.schoeb.opendatatransport.model.StationList;

public class TransportConnection {

    private IOpenTransportRepository repository;
    private ConnectionList connectionList;
    private String startLocation;
    private String endLocation;

    private void OpenRepository() {
        if (repository == null) {
            repository = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
        }
    }

    private void SearchConnection() {
        try {
            connectionList = repository.searchConnections(startLocation, endLocation);
        } catch (OpenDataTransportException e) {
            connectionList = null;
        }
    }

    ConnectionList GetConnection(String startLocation, String endLocation) {
        OpenRepository();
        SearchConnection();

        return connectionList;
    }

    public StationList GetStationName(String location, String type )
    {
        StationList list =null;
        try{
            OpenRepository();
            list = repository.findStations(location, type);
        }
        catch(OpenDataTransportException e)
        {
            //do nothing
        }
        return list;
    }
}
