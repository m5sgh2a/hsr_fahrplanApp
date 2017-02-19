package group9.hsr.ch.businesstravelagent.Model;


class TransportSearchParameter {
    private String startLocation = "";
    private String endLocation = "";
    private String date = "";
    private String time = "";
    private Boolean isArrival = false;

    TransportSearchParameter(String startLocation,
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

    String GetStartLocation() {
        return this.startLocation;
    }

    String GetEndLocation() {
        return this.endLocation;
    }

    String GetDate() {
        return this.date;
    }

    String GetTime() {
        return this.time;
    }

    Boolean GetIsArrival() {
        return this.isArrival;
    }
}
