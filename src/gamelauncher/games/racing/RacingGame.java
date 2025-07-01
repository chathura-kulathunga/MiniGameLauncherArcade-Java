package gamelauncher.games.racing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RacingGame extends JFrame {
    private Timer timer;
    private int carX = 150; // car position X
    private int carY = 500; // fixed Y
    private int obstacleX;
    private int obstacleY = -100;
    private int score = 0;
    private Image carImg;
    private Random rand = new Random();

    public RacingGame() {
        setTitle("🏎️ Racing Game");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Load car image
        carImg = new ImageIcon("src/gamelauncher/games/racing/car.png").getImage();
        // Start with random obstacle X
        obstacleX = rand.nextInt(300);

        // Timer: updates game every 30 ms
        timer = new Timer(30, e -> gameLoop());
        timer.start();

        // Key controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && carX > 10) {
                    carX -= 20;
                }
                if (key == KeyEvent.VK_RIGHT && carX < 300) {
                    carX += 20;
                }
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    private void gameLoop() {
        obstacleY += 10; // move obstacle down

        if (obstacleY > 600) {
            obstacleY = -100;
            obstacleX = rand.nextInt(300);
            score += 10; // add score when pass obstacle
        }

        // Check collision
        if (new Rectangle(carX, carY, 80, 100).intersects(new Rectangle(obstacleX, obstacleY, 80, 100))) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "💥 Crash! Your score: " + score);
            dispose();
        }

        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw road
        g2.setColor(Color.GRAY);
        g2.fillRect(50, 50, 300, 500);

        // Draw car
        g2.drawImage(carImg, carX, carY, 80, 100, this);

        // Draw obstacle
        g2.setColor(Color.RED);
        g2.fillRect(obstacleX, obstacleY, 80, 100);

        // Draw score
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g2.drawString("Score: " + score, 140, 70);
    }
}