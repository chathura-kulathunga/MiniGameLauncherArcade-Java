package gamelauncher;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class LauncherLoading extends JFrame {

    public LauncherLoading() {
        setTitle("Game Launcher - Loading");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());

        // === Logo / Splash ===
        JLabel splashLabel = new JLabel();
        splashLabel.setIcon(new ImageIcon("src/gamelauncher/assets/splash/splash.gif")); // 🔁 Your splash/loading GIF
        splashLabel.setHorizontalAlignment(SwingConstants.CENTER);
        splashLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(splashLabel, BorderLayout.CENTER);

        // === Footer text ===
        JLabel loadingText = new JLabel("Loading Arcade Magic...", SwingConstants.CENTER);
        loadingText.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loadingText.setForeground(new Color(33, 150, 243));
        add(loadingText, BorderLayout.SOUTH);

        setVisible(true);

        // === Auto open launcher after 2.5s ===
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                dispose(); // Close splash
                new GameLauncherUI(); // Open launcher
            }
        }, 2500);
    }

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarculaLaf());
        } catch (Exception e) {
            System.out.println("Theme load failed!");
        }

        new LauncherLoading();
    }
    
}