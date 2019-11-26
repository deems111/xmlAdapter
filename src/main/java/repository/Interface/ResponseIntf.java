package repository.Interface;

import entity.response.XmlResponse;

import java.util.List;

public interface ResponseIntf {

    long save (XmlResponse xmlResponse) throws Exception;

    List <XmlResponse> getByClientId(long clientid) throws Exception;

}
