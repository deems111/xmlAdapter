package repository;


import repository.Interface.AddressIntf;
import entity.request.Address;

import java.sql.*;

import static repository.Interface.JDBCProperties.JDBC_PASSWORD;
import static repository.Interface.JDBCProperties.JDBC_URL;
import static repository.Interface.JDBCProperties.JDBC_USER;

public class AddressImpl implements AddressIntf {

    @Override
    public void save(Address address) throws SQLException {
        final String query = "INSERT INTO PASSPORTS (CLIENTID, REGION, CITY,  STREET, HOUSE, FLAT) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, address.getClientId());
            statement.setString(2, address.getRegion());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getHouseNumber());
            statement.setString(6, address.getFlatNumber());
            statement.executeUpdate();
        }
    }

    @Override
    public entity.request.Address getByClientId(long clientid) throws SQLException {
        final String query = "SELECT * FROM addresses where client id = ?";

        entity.request.Address address = new entity.request.Address();
        address.setClientId(clientid);

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, clientid);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    address.setRegion(resultSet.getString("region"));
                    address.setCity(resultSet.getString("city"));
                    address.setStreet(resultSet.getString("street"));
                    address.setHouseNumber(resultSet.getString("house"));
                    address.setFlatNumber(resultSet.getString("flat"));
                } else {
                    return null;
                }
            }
        }
        return address;
    }

    @Override
    public void deleteByClientId(long clientid) throws SQLException {
        final String query = "DELETE FROM addresses where client id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, clientid);
            stmt.executeUpdate();
        }
    }
}
