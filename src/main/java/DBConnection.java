import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static Connection connection;

    private static String dbName = "db_bot";
    private static String dbUser = "root";
    private static String dbPass = "1234";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection()
    {
        if(connection == null)
        {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                        "?serverTimezone=Europe/Moscow" +
                        "&user=" + dbUser + "&password=" + dbPass + "&useSSL=false");
                connection.createStatement().execute("DROP TABLE IF EXISTS gpl");
                connection.createStatement().execute("CREATE TABLE gpl (" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name varchar(255), " +
//                        "vendor_pn varchar(255) NOT NULL, " +
                        "partnumber varchar(255) NOT NULL, " +
                        "vendor varchar(255) NOT NULL, " +
                        "price_usd DOUBLE(10,2), " +
                        "PRIMARY KEY (id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO gpl(name, partnumber, vendor, price_usd) " +
                "VALUES" + insertQuery.toString();
//        System.out.println(sql);
        insertQuery.delete(0, Integer.MAX_VALUE);
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void multiInsertQuery(String name, String partnumber, String vendor, String price_usd) throws SQLException {
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") +
                "('" + name + "', '" + partnumber + "', '" + vendor + "', " + price_usd + ")");
//        if (insertQuery.toString().getBytes().length > 4000000){
//            executeMultiInsert();
//            insertQuery = null;
//        }
    }
}
