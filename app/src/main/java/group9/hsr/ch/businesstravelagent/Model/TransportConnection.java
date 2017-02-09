package group9.hsr.ch.businesstravelagent.Model;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;

public class TransportConnection {

    private IOpenTransportRepository repo;

    public TransportConnection() throws OpenDataTransportException {
        repo = OpenTransportRepositoryFactory.CreateLocalOpenTransportRepository();
    }

    public ConnectionList GetConnection(String startLocation, String endLocation) {
        try {
            return repo.searchConnections(startLocation, endLocation);
        } catch (OpenDataTransportException e) {
            return null;
        }
    }
}
