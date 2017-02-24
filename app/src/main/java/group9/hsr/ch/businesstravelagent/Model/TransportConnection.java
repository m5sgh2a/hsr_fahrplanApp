package group9.hsr.ch.businesstravelagent.Model;

import ch.schoeb.opendatatransport.IOpenTransportRepository;
import ch.schoeb.opendatatransport.OpenDataTransportException;
import ch.schoeb.opendatatransport.OpenTransportRepositoryFactory;
import ch.schoeb.opendatatransport.model.ConnectionList;
import ch.schoeb.opendatatransport.model.StationList;

class TransportConnection {

    private IOpenTransportRepository repository;
    private ConnectionList connectionList;

    private void OpenRepository() {
        if (repository == null) {
            repository = OpenTransportRepositoryFactory.CreateOnlineOpenTransportRepository();
        }
    }

    private void SearchConnection(TransportSearchParameter parameter) {

        connectionList = new ConnectionList();

        try {
            connectionList = repository.searchConnections(parameter.GetStartLocation(),
                    parameter.GetEndLocation(),
                    null,
                    parameter.GetDate(),
                    parameter.GetTime(),
                    parameter.GetIsArrival());
        } catch (Exception e) {
            //do nothing
        }
    }

    ConnectionList GetConnection(TransportSearchParameter parameter) {
        OpenRepository();
        SearchConnection(parameter);

        return connectionList;
    }

    StationList GetStationName(String location, String type) {
        StationList list = null;

        try {
            OpenRepository();
            list = repository.findStations(location, type);
        } catch (OpenDataTransportException e) {
            //do nothing
        }
        return list;
    }
}
