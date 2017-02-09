package group9.hsr.ch.businesstravelagent.Model;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;

public class TransportConnection {

    private IOpenTransportRepository repo;
    private ConnectionList connectionList;
    private String startLocation;
    private String endLocation;

    private void OpenRepository() {
        if (repo == null) {
            repo = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
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
}
