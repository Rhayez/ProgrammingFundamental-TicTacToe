import java.awt.*;
import javax.swing.*;

public class StatisticsFrame extends JFrame {

    private Player currentPlayer;
    private PlayerService playerService;

    private JLabel lblUsername;
    private JLabel lblWins;
    private JLabel lblLosses;
    private JLabel lblDraws;
    private JLabel lblScore;

    public StatisticsFrame(Player player) {

        this.currentPlayer = player;
        this.playerService = new PlayerService();

        setTitle("My Statistics");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 5, 5));

        lblUsername = new JLabel();
        lblWins = new JLabel();
        lblLosses = new JLabel();
        lblDraws = new JLabel();
        lblScore = new JLabel();

        JButton btnRefresh = new JButton("Refresh");
        JButton btnClose = new JButton("Close");

        panel.add(lblUsername);
        panel.add(lblWins);
        panel.add(lblLosses);
        panel.add(lblDraws);
        panel.add(lblScore);
        panel.add(btnRefresh);
        panel.add(btnClose);

        add(panel);

        loadStatistics();

        btnRefresh.addActionListener(e -> loadStatistics());

        btnClose.addActionListener(e -> dispose());
    }

    private void loadStatistics() {

        Player latestPlayer =
                playerService.getPlayerById(
                        currentPlayer.getId()
                );

        if (latestPlayer == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load player data."
            );

            return;
        }

        lblUsername.setText(
                "Username : " +
                latestPlayer.getUsername()
        );

        lblWins.setText(
                "Wins : " +
                latestPlayer.getWins()
        );

        lblLosses.setText(
                "Losses : " +
                latestPlayer.getLosses()
        );

        lblDraws.setText(
                "Draws : " +
                latestPlayer.getDraws()
        );

        lblScore.setText(
                "Score : " +
                latestPlayer.getScore()
        );
    }
}