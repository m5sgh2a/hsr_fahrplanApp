package ch.schoeb.opendatatransport;

import ch.schoeb.opendatatransport.model.ConnectionList;
import ch.schoeb.opendatatransport.model.StationList;

public interface IOpenTransportRepository {

    StationList findStations(String query, String type) throws OpenDataTransportException;

    ConnectionList searchConnections(String from, String to) throws OpenDataTransportException;

    ConnectionList searchConnections(String from, String to, String via, String date, String time, Boolean isArrivalTime) throws OpenDataTransportException;
}
