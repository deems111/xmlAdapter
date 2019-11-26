package repository;

import repository.Interface.CustomerIntf;
import entity.request.Customer;

import java.sql.*;

import static repository.Interface.JDBCProperties.JDBC_PASSWORD;
import static repository.Interface.JDBCProperties.JDBC_URL;
import static repository.Interface.JDBCProperties.JDBC_USER;

public class CustomerImpl implements CustomerIntf {

    @Override
    public long save(Customer customer) throws SQLException {
        long id = 0L;
        final String query = "INSERT INTO CLIENTS (socialId, networkId,  email, surname, name, fatherName, birthdate, inn) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, customer.getSocialId());
            statement.setInt(2, customer.getNetworkId());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getSurname());
            statement.setString(5, customer.getName());
            statement.setString(6, customer.getFatherName());
            statement.setString(7, customer.getBirthdate());
            statement.setString(8, customer.getInn());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys())  {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }

            return id;
        }
    }

    @Override
    public Customer getByClientId(long clientid) throws Exception {

        Customer.Builder builder;
        final String query = "SELECT * FROM CLIENTS where clientid = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setLong(1, clientid);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    builder = new Customer.Builder(clientid, resultSet.getString("surname"),
                            resultSet.getString("name"),
                            resultSet.getString("birthdate"));
                    builder.socialId(resultSet.getLong("socialId"));
                    builder.networkId(resultSet.getInt("networkId"));

                    setOptionalFields(resultSet, builder);

                } else {
                    throw new Exception("Client with id = " + clientid + " wasn't found");
                }
            }
            return new Customer(builder);
        }
    }

    @Override
    public void deleteByClientId(long clientid) throws SQLException {
        final String query = "DELETE FROM clients where client id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, clientid);
            stmt.executeUpdate();
        }
    }

    private void setOptionalFields(ResultSet resultSet, Customer.Builder builder) throws SQLException {

        if (resultSet.getString("email") != null)
            builder.email(resultSet.getString("email"));
        if (resultSet.getString("fatherName") != null)
            builder.fatherName(resultSet.getString("fatherName"));
        if (resultSet.getString("inn") != null)
            builder.inn(resultSet.getString("inn"));
    }
}
