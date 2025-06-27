package gamelauncher;

import javax.swing.*;
import java.awt.*;

public class GameLauncherUI extends JFrame {

    public GameLauncherUI() {
        setTitle("Mini Game Arcade");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("🎮 Welcome to the Mini Arcade!", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        add(label);

        setVisible(true);
    }
}