import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerService {

    // ===========================
    // LOGIN
    // ===========================
    public Player login(String username, String password) {

        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    // ===========================
    // GET PLAYER BY ID
    // ===========================
    public Player getPlayerById(int id) {

        String sql = "SELECT * FROM players WHERE id = ?";

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    // ===========================
    // TOP 5 SCORERS
    // ===========================
    public ArrayList<Player> getTopFiveScorers() {

        ArrayList<Player> players = new ArrayList<>();

        String sql = """
                SELECT *
                FROM players
                ORDER BY score DESC, wins DESC
                LIMIT 5
                """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                players.add(

                        new Player(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getInt("wins"),
                                rs.getInt("losses"),
                                rs.getInt("draws"),
                                rs.getInt("score")
                        )

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return players;
    }

    // ===========================
    // UPDATE STATISTICS
    // ===========================
    public void updateStatistics(int playerId,
                                 int wins,
                                 int losses,
                                 int draws,
                                 int score) {

        String sql = """
                UPDATE players
                SET wins = wins + ?,
                    losses = losses + ?,
                    draws = draws + ?,
                    score = score + ?
                WHERE id = ?
                """;

        try (
                Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, wins);
            stmt.setInt(2, losses);
            stmt.setInt(3, draws);
            stmt.setInt(4, score);
            stmt.setInt(5, playerId);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}