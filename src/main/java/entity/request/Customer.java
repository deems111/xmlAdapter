package entity.request;

import java.io.Serializable;

public class Customer implements Serializable {

    private long id;
    private long socialId;
    private int networkId;
    private String email;
    private String surname;
    private String name;
    private String fatherName;
    private String birthdate;
    private String inn;

    public Customer(Builder builder) {
        this.id = builder.id;
        this.socialId = builder.socialId;
        this.networkId = builder.networkId;
        this.email = builder.email;
        this.surname = builder.surname;
        this.name = builder.name;
        this.fatherName = builder.fatherName;
        this.birthdate = builder.birthdate;
        this.inn = builder.inn;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSocialId() {
        return socialId;
    }

    public void setSocialId(long socialId) {
        this.socialId = socialId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public static class Builder {
        private long id;
        private long socialId = 0l;
        private int networkId = 0;
        private String email = "";
        private String surname;
        private String name;
        private String fatherName = "";
        private String birthdate;
        private String inn = "";

        public Builder(long id) {
            this.id = id;
        }

        public Builder(long id, String surname,
                       String name, String birthdate) {
            this.id = id;
            this.surname = surname;
            this.name = name;
            this.birthdate = birthdate;
        }

        public Builder socialId(long value) {
            socialId = value;
            return this;
        }

        public Builder networkId(int value) {
            networkId = value;
            return this;
        }

        public Builder email(String value) {
            email = value;
            return this;
        }

        public Builder fatherName(String value) {
            fatherName = value;
            return this;
        }

        public Builder inn(String value) {
            inn = value;
            return this;
        }
    }

}