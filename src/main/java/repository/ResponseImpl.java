package repository;

import repository.Interface.ResponseIntf;
import entity.response.XmlResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static repository.Interface.JDBCProperties.JDBC_PASSWORD;
import static repository.Interface.JDBCProperties.JDBC_URL;
import static repository.Interface.JDBCProperties.JDBC_USER;

public class ResponseImpl implements ResponseIntf {

    @Override
    public long save(XmlResponse xmlResponse) throws Exception {
        long id = 0l;

        final String query = "INSERT INTO RESPONSES (ID, CLIENTID, RATING, PROBABILITY, ERROR) VALUES (?, ?, ?,?,?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, xmlResponse.getResponseId());
            statement.setLong(2, xmlResponse.getClientId());
            statement.setInt(3, xmlResponse.getClientRating());
            statement.setDouble(4, xmlResponse.getProbability());
            statement.setBoolean(5, xmlResponse.isError());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
            }
        }
        return id;
    }

    @Override
    public List<XmlResponse> getByClientId(long clientid) throws Exception {

        final String query = "SELECT * FROM RESPONSES where client id = ?";
        List<XmlResponse> responsesList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, clientid);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    XmlResponse xmlResponse = new XmlResponse(clientid);
                    xmlResponse.setClientRating(resultSet.getInt("RATING"));
                    xmlResponse.setProbability(resultSet.getDouble("PROBABILITY"));
                    xmlResponse.setError(resultSet.getBoolean("ERROR"));
                    responsesList.add(xmlResponse);
                }
            }
        }
        return responsesList;
    }
}
