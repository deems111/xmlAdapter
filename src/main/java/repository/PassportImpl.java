package repository;

import entity.request.Passport;

import java.sql.*;

import static repository.Interface.JDBCProperties.JDBC_PASSWORD;
import static repository.Interface.JDBCProperties.JDBC_URL;
import static repository.Interface.JDBCProperties.JDBC_USER;

public class PassportImpl implements repository.Interface.PassportIntf {

    @Override
    public void save(Passport passport) throws SQLException {

        final String query = "INSERT INTO PASSPORTS (CLIENTID, SERIAL, NUMBER,  ISSUEDATE) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, passport.getClientId());
            statement.setInt(1, passport.getSerial());
            statement.setInt(3, passport.getNumber());
            statement.setString(4, passport.getIssueDate());
            statement.executeUpdate();
        }
    }

    @Override
    public Passport getByClientId(long clientid) throws SQLException {
        final String query = "SELECT * FROM passports where client id = ?";

        Passport passport = new Passport();
        passport.setClientId(clientid);

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, clientid);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    passport.setSerial(resultSet.getInt("serial"));
                    passport.setNumber(resultSet.getInt("number"));
                    passport.setIssueDate(resultSet.getString("issuedate"));
                } else {
                    return null;
                }
            }
            return passport;
        }
    }

    @Override
    public void deleteByClientId(long clientid) throws SQLException, ClassNotFoundException {

        final String query = "DELETE FROM passports where client id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, clientid);
            stmt.executeUpdate();
        }

    }
}
