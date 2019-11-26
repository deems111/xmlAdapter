package repository.Interface;

import entity.request.Customer;

public interface CustomerIntf {

    long save(Customer customer) throws Exception;

    Customer getByClientId(long clientid) throws Exception;

    void deleteByClientId(long clientid) throws Exception;
}
