package repository.Interface;

import entity.request.Passport;

public interface PassportIntf {

    void save(Passport passport) throws Exception;

    Passport getByClientId(long clientid) throws Exception;

    void deleteByClientId(long clientid) throws Exception;

}