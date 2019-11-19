package entity.request;

import java.io.Serializable;

public class Passport implements Serializable {

    private long clientId;
    private int serial;
    private int number;
    private String issueDate;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String print() {
        StringBuffer passportStr = new StringBuffer("Passport\n");
        if (clientId > 0 && serial > 0) {
            passportStr.append("clientId:" + "\t" + clientId + "\n");
            passportStr.append("serial:" + "\t" + serial + "\n");
            passportStr.append("number:" + "\t" + number + "\n");
            passportStr.append("issueDate:" + "\t" + issueDate + "\n");
        }
        return passportStr.toString();
    }
}
