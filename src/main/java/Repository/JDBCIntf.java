package Repository;

import entity.request.Address;
import entity.request.Passport;
import entity.request.XmlRequest;
import entity.response.XmlResponse;

import java.sql.SQLException;

public interface JDBCIntf {

    Passport getPassport(long clientid) throws SQLException, ClassNotFoundException;

    Address getAddress(long clientid) throws SQLException, ClassNotFoundException;

    void saveResponse(XmlResponse xmlResponse) throws SQLException, ClassNotFoundException;

    void saveRequest(XmlRequest xmlRequest) throws SQLException, ClassNotFoundException;
}
