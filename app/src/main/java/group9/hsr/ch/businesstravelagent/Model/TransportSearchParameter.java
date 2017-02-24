package group9.hsr.ch.businesstravelagent.Model;


public class TransportSearchParameter {
    private String startLocation = "";
    private String endLocation = "";
    private String date = "";
    private String time = "";
    private Boolean isArrival = false;

    public TransportSearchParameter(String startLocation,
                                    String endLocation,
                                    String date,
                                    String time,
                                    Boolean isArrival) {

        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.date = date;
        this.time = time;
        this.isArrival = isArrival;
    }

    public String GetStartLocation() {
        return this.startLocation;
    }

    public String GetEndLocation() {
        return this.endLocation;
    }

    public String GetDate() {
        return this.date;
    }

    public String GetTime() {
        return this.time;
    }

    public Boolean GetIsArrival() {
        return this.isArrival;
    }
}
