package gamelauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLauncherUI extends JFrame {

    public GameLauncherUI() {
        setTitle("Mini Game Arcade 🎮");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === Title ===
        JLabel titleLabel = new JLabel("🎮 Mini Game Arcade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // === Panel for buttons ===
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        buttonPanel.setOpaque(false); // make background transparent

        // === Create buttons ===
        buttonPanel.add(createGameButton("Puzzle Slider", "src/gamelauncher/assets/icons/puzzle.png", this));
        buttonPanel.add(createGameButton("Memory Match", "src/gamelauncher/assets/icons/memory.png", this));
        buttonPanel.add(createGameButton("Brick Breaker", "src/gamelauncher/assets/icons/brick.png", this));
        buttonPanel.add(createGameButton("Snake Game", "src/gamelauncher/assets/icons/snake.png", this));
        buttonPanel.add(createGameButton("Tic-Tac-Toe", "src/gamelauncher/assets/icons/tic.png", this));
        buttonPanel.add(createGameButton("Racing Game", "src/gamelauncher/assets/icons/racing.png", this));

        add(buttonPanel, BorderLayout.CENTER);

        // === Gradient background panel ===
        setContentPane(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = new Color(255, 255, 255);
                Color c2 = new Color(230, 240, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titleLabel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // === Method to create styled button ===
    private JButton createGameButton(String text, String iconPath, JFrame launcherFrame) {
        ImageIcon icon = new ImageIcon(
                new ImageIcon(iconPath).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(33, 150, 243));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === Hover effect ===
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 136, 229));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(33, 150, 243));
            }
        });

        // === Click action ===
        button.addActionListener(e -> {
            launcherFrame.setEnabled(false); // Disable launcher
            if (text.equals("Puzzle Slider")) {
                JFrame gameWindow = new gamelauncher.games.puzzle.PuzzleSlider();
                gameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        launcherFrame.setEnabled(true); // Re-enable launcher when game closed
                        launcherFrame.toFront(); // Bring back to front
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, text + " clicked! (Game not yet implemented)");
                launcherFrame.setEnabled(true); // Re-enable immediately for message
            }
        });

        return button;
    }
}
