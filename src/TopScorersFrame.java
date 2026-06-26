import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TopScorersFrame extends JFrame {

    public TopScorersFrame() {

        setTitle("Top 5 Scorers");
        setSize(600,300);
        setLocationRelativeTo(null);

        PlayerService service =
                new PlayerService();

        ArrayList<Player> players =
                service.getTopFiveScorers();

        String[] columns = {
                "Username",
                "Wins",
                "Losses",
                "Draws",
                "Score"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        for (Player player : players) {

            Object[] row = {

                    player.getUsername(),
                    player.getWins(),
                    player.getLosses(),
                    player.getDraws(),
                    player.getScore()

            };

            model.addRow(row);

        }

        JTable table =
                new JTable(model);

        add(
                new JScrollPane(table)
        );
    }
}