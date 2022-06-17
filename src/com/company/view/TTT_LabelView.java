package com.company.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.company.controller.Controller;
import com.company.games.TTT_Constants;
import com.company.model.TTT_Model;

@SuppressWarnings("serial") public class TTT_LabelView extends JFrame implements View {
    private JLabel informLabel;        // Zeigt die aktuelle Spielsituation an.
    private JLabel[][] board;        // Die Felder zum Eintragen von "X" bzw. "O".
    private JPanel panel;            // Panel für die Felder mit "X" bzw. "O".

    private Controller controller;    // Der Controller für diese view.

    /**
     * Bei jedem Spielfeld-Label wird später ein OnAction-ActionListener registriert.
     * Jeder OnAction-ActionListener kennt die row-col-Koordinaten "seines" Labels.
     * Steht die Mouse über einem Label und wird dabei die Mouse-Taste gedrückt,
     * werden diese Feld-Koordinaten dem Controller gemeldet.
     */
    private class OnAction extends MouseAdapter {
        private int row, col;
        private Color hoverColor = Color.RED;
        private Color oldColor;

        public OnAction(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override public void mouseClicked(MouseEvent e) {
            controller.select(row, col);
        }

        @Override public void mouseEntered(MouseEvent e) {
            board[row][col].setBackground(Color.RED);
        }

        @Override public void mouseExited(MouseEvent e) {
            board[row][col].setBackground(Color.GRAY);
        }

    } // end inner class OnAction

    public TTT_LabelView(TTT_Model model) {
        // Grundeinstellungen für diesen JFrame:
        this.setTitle("L A B E L V I E W @ H K A");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 480);
        this.setLocation(500, 0); // rechts neben dem ersten Frame
        this.setVisible(true);

        // Formatiere die Anzeige für die Spielsituation:
        this.informLabel = new JLabel();
        this.informLabel.setBackground(new Color(100, 25, 100));
        this.informLabel.setForeground(new Color(0, 255, 0));
        this.informLabel.setFont(new Font("Ink Free", Font.BOLD, 48));
        this.informLabel.setHorizontalAlignment(JLabel.CENTER);
        this.informLabel.setOpaque(true);
        this.add(informLabel, BorderLayout.NORTH);

        // Font für Beschriftung der Spiel-Buttons
        Font font = new Font("Ink Free", Font.BOLD, 80);

        panel = new JPanel();
        panel.setLayout(new GridLayout(model.getSize(), model.getSize()));

        board = new JLabel[model.getSize()][model.getSize()];
        for (int row = 0; row < model.getSize(); row++)
            for (int col = 0; col < model.getSize(); col++) {
                board[row][col] = new JLabel(TTT_Constants.EMPTY.tag);
                board[row][col].setFont(font);
                board[row][col].setFocusable(false);
                board[row][col].setBackground(Color.GRAY);
                board[row][col].setBorder(BorderFactory.createLineBorder(Color.pink));
                board[row][col].addMouseListener(new OnAction(row, col));
                board[row][col].setOpaque(true);
                board[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                board[row][col].setVerticalAlignment(SwingConstants.CENTER);
                panel.add(board[row][col]);

            }
        this.add(panel); // F�ge das panel diesem JFrame hinzu:

    }

    @Override public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override public void showBoard(TTT_Model model) {
        //build JPanel, JLAbel etc.
        for (int row = 0; row < model.getSize(); row++)
            for (int col = 0; col < model.getSize(); col++)
                board[row][col].setText(model.getEntry(row, col).tag);
    }

    @Override public void showNextPlayer(TTT_Constants player) {
        this.informLabel.setText(player.tag + "  next");

    }

    @Override public void showWinner(TTT_Constants player) {
        if (player == TTT_Constants.TIE)
            this.informLabel.setText("tie");
        else
            this.informLabel.setText("winner: " + player.tag);
        // Disable all Buttons:
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                board[row][col].setEnabled(false);
    }

    @Override public void resetView() {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                board[row][col].setEnabled(true);
    }

}
