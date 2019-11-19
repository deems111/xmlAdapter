package entity.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(namespace = "//o7planning.org/jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlResponse implements Serializable {

    @XmlElement(name = "id")
    private long responseId;
    @XmlElement(name = "result")
    private int clientRating;
    @XmlElement(name = "probability")
    private double probability;
    @XmlElement(name = "error")
    private boolean error;

    private long clientId;

    public XmlResponse(long clientId) {
        this.clientId = clientId;
    }

    public XmlResponse(long clientId, long responseId, int clientRating, double probability, boolean error) {
        this.clientId = clientId;
        this.responseId = responseId;
        this.clientRating = clientRating;
        this.probability = probability;
        this.error = error;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getResponseId() {
        return responseId;
    }

    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    public int getClientRating() {
        return clientRating;
    }

    public void setClientRating(int clientRating) {
        this.clientRating = clientRating;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
