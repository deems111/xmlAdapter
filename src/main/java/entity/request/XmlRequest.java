package entity.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(namespace = "//o7planning.org/jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequest implements Serializable {

    @XmlElement(name = "customer")
    private Customer customer;
    @XmlElement(name = "passport")
    private Passport passport;
    @XmlElement(name = "address")
    private Address adress;

    public XmlRequest() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getLivingAdress() {
        return adress;
    }

    public void setLivingAdress(Address livingAdress) {
        this.adress = livingAdress;
    }
}
