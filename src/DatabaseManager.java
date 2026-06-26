import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    private static final String URL =
        "jdbc:postgresql://localhost:5433/game_project";

    private static final String USER =
        "postgres";

    private static final String PASSWORD =
        "n4n4p._r4y21";

    public static Connection getConnection()
            throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}