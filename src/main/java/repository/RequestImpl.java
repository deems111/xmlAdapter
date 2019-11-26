package repository;

import repository.Interface.RequestIntf;
import util.Constants;
import entity.request.XmlRequest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static repository.Interface.JDBCProperties.JDBC_PASSWORD;
import static repository.Interface.JDBCProperties.JDBC_URL;
import static repository.Interface.JDBCProperties.JDBC_USER;

public class RequestImpl implements RequestIntf {

    @Override
    public long save(XmlRequest xmlRequest) throws Exception {

        long id = 0L;
        final String query = "INSERT INTO REQUESTS (ID, CLIENTID, SERVICETYPE, DATE) VALUES (?,?,?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, xmlRequest.getCustomer().getId());
            statement.setString(2, Constants.SERVICE_TYPE);
            statement.setString(3, LocalDate.now().toString());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys())  {

                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                } else {
                    throw new Exception("Can not get XmlRequest id");
                }
            }
            return id;
        }
    }

    @Override
    public List<XmlRequest> getByClientId(long clientid) throws Exception {
        final String query = "SELECT * FROM REQUESTS where client id = ?";
        List<XmlRequest> requestsList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setLong(1, clientid);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    XmlRequest xmlRequest = new XmlRequest();
                    xmlRequest.setCustomer(new CustomerImpl().getByClientId(clientid));
                    xmlRequest.setPassport(new PassportImpl().getByClientId(clientid));
                    xmlRequest.setLivingAdress(new AddressImpl().getByClientId(clientid));
                }
            }
            return requestsList;
        }
    }
}