import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private PlayerService playerService;

    public LoginFrame() {

        playerService = new PlayerService();

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Username"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Password"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        panel.add(new JLabel(""));
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {

        String username =
                txtUsername.getText();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        Player player =
                playerService.login(
                        username,
                        password
                );

        if (player != null) {

        JOptionPane.showMessageDialog(
            this,
            "Login Berhasil"
        );

        MainMenuFrame menu =
        new MainMenuFrame(player);

        menu.setVisible(true);

        dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Username atau Password Salah"
            );

        }
    }
}