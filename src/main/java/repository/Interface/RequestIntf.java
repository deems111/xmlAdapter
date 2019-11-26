package repository.Interface;

import entity.request.XmlRequest;

import java.util.List;

public interface RequestIntf {

    long save (XmlRequest xmlRequest) throws Exception;

    List <XmlRequest> getByClientId(long clientid) throws Exception;

}
