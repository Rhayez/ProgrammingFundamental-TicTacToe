import java.awt.*;
import javax.swing.*;

public class MainMenuFrame extends JFrame {

    private Player currentPlayer;

    public MainMenuFrame(Player player) {

        this.currentPlayer = player;

        setTitle("Main Menu");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel lblWelcome =
                new JLabel(
                        "Welcome " +
                        currentPlayer.getUsername(),
                        SwingConstants.CENTER
                );

        JButton btnStartGame =
                new JButton("Start Game");

        JButton btnStatistics =
                new JButton("Statistics");

        JButton btnTop5 =
                new JButton("Top 5 Scorers");

        JButton btnExit =
                new JButton("Exit");

        panel.add(lblWelcome);
        panel.add(btnStartGame);
        panel.add(btnStatistics);
        panel.add(btnTop5);
        panel.add(btnExit);

        add(panel);

        // START GAME
        btnStartGame.addActionListener(
                e -> {

                    GameFrame frame =
                            new GameFrame(
                                    currentPlayer
                            );

                    frame.setVisible(true);

                }
        );

        // STATISTICS
        btnStatistics.addActionListener(
                e -> {

                    StatisticsFrame frame =
                            new StatisticsFrame(
                                    currentPlayer
                            );

                    frame.setVisible(true);

                }
        );

        // TOP 5 SCORERS
        btnTop5.addActionListener(
                e -> {

                    TopScorersFrame frame =
                            new TopScorersFrame();

                    frame.setVisible(true);

                }
        );

        // EXIT
        btnExit.addActionListener(
                e -> System.exit(0)
        );
    }
}