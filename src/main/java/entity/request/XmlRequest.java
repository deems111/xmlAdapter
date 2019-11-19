package entity.request;

import Repository.JDBC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.SQLException;

@XmlRootElement(namespace = "//o7planning.org/jaxb")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRequest implements Serializable {

    @XmlElement(name = "person_id")
    private long personId;
    @XmlElement(name = "social_id")
    private String socialId;
    @XmlElement(name = "network_id")
    private String network_id;
    @XmlElement(name = "phone")
    private String phone;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "surname")
    private String surname;
    @XmlElement(name = "name1")
    private String name;
    @XmlElement(name = "name2")
    private String fatherName;
    @XmlElement(name = "birthdate")
    private String birthdate;
    @XmlElement(name = "inn")
    private long inn;
    @XmlElement(name = "passport")
    private Passport passport;
    @XmlElement(name = "address")
    private Address livingAdress;

    public XmlRequest() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getNetwork_id() {
        return network_id;
    }

    public void setNetwork_id(String network_id) {
        this.network_id = network_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getLivingAdress() {
        return livingAdress;
    }

    public void setLivingAdress(Address livingAdress) {
        this.livingAdress = livingAdress;
    }
}
