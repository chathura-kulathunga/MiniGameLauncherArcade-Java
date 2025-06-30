package gamelauncher.games.puzzle;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class PuzzleSlider extends JFrame {
    private JButton[] buttons = new JButton[9]; // 8 tiles + 1 empty
    private JPanel gridPanel;
    private int emptyIndex = 8; // start with empty at end

    public PuzzleSlider() {
        setTitle("🧩 Puzzle Slider");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initButtons();
        shuffleTiles();

        JButton shuffleButton = new JButton("🔄 Shuffle");
        shuffleButton.addActionListener(e -> shuffleTiles());

        add(gridPanel, BorderLayout.CENTER);
        add(shuffleButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initButtons() {
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton(i < 8 ? String.valueOf(i + 1) : "");
            btn.setFont(new Font("Segoe UI", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(33, 150, 243));
            btn.setForeground(Color.WHITE);
            final int index = i;
            btn.addActionListener(e -> moveTile(index));
            buttons[i] = btn;
            gridPanel.add(btn);
        }
    }

    private void moveTile(int index) {
        if (isAdjacent(index, emptyIndex)) {
            buttons[emptyIndex].setText(buttons[index].getText());
            buttons[index].setText("");
            emptyIndex = index;
        }
    }

    private boolean isAdjacent(int i1, int i2) {
        int row1 = i1 / 3, col1 = i1 % 3;
        int row2 = i2 / 3, col2 = i2 % 3;
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }

    private void shuffleTiles() {
        java.util.List<String> labels = new ArrayList<>();
        for (int i = 1; i <= 8; i++) labels.add(String.valueOf(i));
        labels.add(""); // empty

        Collections.shuffle(labels);

        for (int i = 0; i < 9; i++) {
            buttons[i].setText(labels.get(i));
            if (labels.get(i).equals("")) emptyIndex = i;
        }
    }
}