package group9.hsr.ch.businesstravelagent.Model;

import java.util.ArrayList;

class TransportSearchValidate {

    private ArrayList<String> errorMessage = new ArrayList<>();
    private String startLocation;
    private String endLocation;

    TransportSearchValidate(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public ArrayList<String> GetErrorMessage() {
        return this.errorMessage;
    }

    private boolean IsStartLocationValid() {
        return startLocation != null && startLocation.length() > 0;
    }

    private void SetStartLocationErrorMessage() {
        if (!IsStartLocationValid()) {
            errorMessage.add("Bitte geben Sie einen Startort ein.");
        }
    }

    private void SetEndLocationErrorMessage() {
        if (!IsEndLocationValid()) {
            errorMessage.add("Bitte geben Sie einen Zielort ein.");
        }
    }

    private boolean IsEndLocationValid() {
        return endLocation != null && endLocation.length() > 0;
    }

    void Execute() {
        errorMessage.clear();

        SetStartLocationErrorMessage();
        SetEndLocationErrorMessage();
    }

    String GetErrorMessageAsOneString() {
        String result = "";

        for (int i = 0; i < errorMessage.size(); i++) {
            if (errorMessage.size() > 1) {
                result += "- ";
            }

            result += errorMessage.get(i);

            if (i < errorMessage.size() - 1) {
                result += "\n";
            }
        }

        return result;
    }

    boolean AreErrorsAvailable() {
        return errorMessage.size() > 0;
    }
}
