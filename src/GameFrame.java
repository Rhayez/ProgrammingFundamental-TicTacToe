import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GameFrame extends JFrame {

    private JButton[] buttons;

    private GameLogic game;

    private Player currentPlayer;

    private PlayerService playerService;

    public GameFrame(Player player) {

        this.currentPlayer = player;

        this.playerService =
                new PlayerService();

        game = new GameLogic();

        setTitle("Tic Tac Toe");

        setSize(400, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new GridLayout(3, 3)
        );

        buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {

            buttons[i] =
                    new JButton("");

            buttons[i].setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            40
                    )
            );

            final int index = i;

            buttons[i].addActionListener(
                    e -> handleMove(index)
            );

            add(buttons[i]);
        }
    }

    private void handleMove(int index) {

        boolean success =
                game.makeMove(index, "X");

        if (!success) {

            return;

        }

        buttons[index].setText("X");

        if (game.checkWinner("X")) {

            playerService.updateStatistics(
                    currentPlayer.getId(),
                    1,
                    0,
                    0,
                    10
            );

            JOptionPane.showMessageDialog(
                    this,
                    "You Win!"
            );

            disableBoard();

            return;
        }

        if (game.isDraw()) {

            playerService.updateStatistics(
                    currentPlayer.getId(),
                    0,
                    0,
                    1,
                    3
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Draw!"
            );

            disableBoard();

            return;
        }

        computerMove();
    }

    private void computerMove() {

        Random random =
                new Random();

        int move;

        do {

            move =
                    random.nextInt(9);

        } while (
                !game.makeMove(
                        move,
                        "O"
                )
        );

        buttons[move].setText("O");

        if (game.checkWinner("O")) {

            playerService.updateStatistics(
                    currentPlayer.getId(),
                    0,
                    1,
                    0,
                    0
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Computer Wins!"
            );

            disableBoard();

            return;
        }

        if (game.isDraw()) {

            playerService.updateStatistics(
                    currentPlayer.getId(),
                    0,
                    0,
                    1,
                    3
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Draw!"
            );

            disableBoard();

            return;
        }
    }

    private void disableBoard() {

        for (JButton button : buttons) {

            button.setEnabled(false);

        }
    }
}