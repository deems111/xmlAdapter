package repository.Interface;

import entity.request.Address;

public interface AddressIntf {

    void save(Address address) throws Exception;

    Address getByClientId(long clientid) throws Exception;

    void deleteByClientId(long clientid) throws Exception;

}