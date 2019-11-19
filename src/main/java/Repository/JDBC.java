package Repository;

import Util.Constants;
import Util.Utility;
import bean.Driver;
import entity.request.Address;
import entity.request.Passport;
import entity.request.XmlRequest;
import entity.response.XmlResponse;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

import static Repository.JDBCProperties.JDBC_PASSWORD;
import static Repository.JDBCProperties.JDBC_URL;
import static Repository.JDBCProperties.JDBC_USER;


public class JDBC implements JDBCIntf {
    private Logger log;

    public JDBC(final Logger log) {
        this.log = log;
    }

    public Passport getPassport(long clientid) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM passports where client id = ?";

        Passport passport = new Passport();
        passport.setClientId(clientid);

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            statement = connection.prepareStatement(query);
            statement.setLong(1, clientid);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                passport.setSerial(resultSet.getInt("serial"));
                passport.setNumber(resultSet.getInt("number"));
                passport.setIssueDate(resultSet.getString("issuedate"));
                log.info(passport.print());
            } else {
                return null;
            }
            return passport;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

    public Address getAddress(long clientid) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "SELECT * FROM addresses where client id = ?";

        Address address = new Address();
        address.setClientId(clientid);

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            statement = connection.prepareStatement(query);
            statement.setLong(1, clientid);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                address.setRegion(resultSet.getString("region"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouseNumber(resultSet.getString("house"));
                address.setFlatNumber(resultSet.getString("flat"));
                log.info(address.print());
            } else {
                return null;
            }
            return address;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }


    public void saveResponse(XmlResponse xmlResponse) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "INSERT INTO RESPONSES (ID, CLIENTID, RATING, PROBABILITY, ERROR) VALUES (?,?,?,?,?)";

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            statement = connection.prepareStatement(query);
            statement.setLong(1, xmlResponse.getResponseId());
            statement.setLong(2, xmlResponse.getClientId());
            statement.setInt(3, xmlResponse.getClientRating());
            statement.setDouble(4, xmlResponse.getProbability());
            statement.setBoolean(5, xmlResponse.isError());
            statement.executeUpdate();

            log.info(statement.toString());

        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

    public void saveRequest(XmlRequest xmlRequest) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        final String query = "INSERT INTO REQUESTS (CLIENTID, SERVICETYPE, DATE) VALUES (?,?,?)";

        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            statement = connection.prepareStatement(query);
            statement.setLong(1, xmlRequest.getPersonId());
            statement.setString(2, Constants.SERVICE_TYPE);
            statement.setString(3, LocalDate.now().toString());
            statement.executeUpdate();

            log.info(statement.toString());

        } catch (SQLException e) {
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

}
