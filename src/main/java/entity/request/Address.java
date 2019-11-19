package entity.request;

import java.io.Serializable;
import java.text.StringCharacterIterator;

public class Address implements Serializable {

    private long clientId;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;

    public Address() {
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String print() {
        StringBuffer addressStr = new StringBuffer("Address\n");
        if (clientId > 0 && region != null && region.length() > 0) {
            addressStr.append("clientId:" + "\t" + clientId + "\n");
            addressStr.append("region:" + "\t" + region + "\n");
            addressStr.append("city:" + "\t" + city + "\n");
            addressStr.append("street:" + "\t" + street + "\n");
            addressStr.append("houseNumber:" + "\t" + houseNumber + "\n");
            addressStr.append("flatNumber:" + "\t" + flatNumber + "\n");
        }

        return addressStr.toString();
    }

}
