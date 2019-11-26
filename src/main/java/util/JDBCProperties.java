import java.util.ResourceBundle;

public interface JDBCProperties {

    ResourceBundle resource = ResourceBundle.getBundle("JDBC");

    String JDBC_driver = resource.getString("DBDriver");
    String JDBC_URL = resource.getString("URL");
    String JDBC_USER = resource.getString("username");
    String JDBC_PASSWORD = resource.getString("password");
}
