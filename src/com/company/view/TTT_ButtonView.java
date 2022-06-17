package com.company.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.company.controller.Controller;
import com.company.games.TTT_Constants;
import com.company.model.TTT_Model;

@SuppressWarnings("serial") public class TTT_ButtonView extends JFrame implements View {
    private JLabel informLabel;        // Zeigt die aktuelle Spielsituation an.
    private JButton[][] board;        // Die Felder zum Eintragen von "X" bzw. "O".
    private JPanel panel;            // Panel f�r die Felder mit "X" bzw. "O".
    private Controller controller;    // Der Controller f�r diese view.

    /**
     * Bei jedem Spielfeld-Button wird sp�ter ein OnAction-ActionListener registriert.
     * Jeder OnAction-ActionListener kennt die row-col-Koordinaten "seines" Buttons.
     * Wird ein Button gedr�ckt, werden diese Feld-Koordinaten dem Controller gemeldet.
     */
    private class OnAction implements ActionListener {
        private int row, col;

        public OnAction(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override public void actionPerformed(ActionEvent e) {
            controller.select(row, col);
        }
    } // end inner class OnAction

    public TTT_ButtonView(TTT_Model model) {
        // Grundeinstellungen f�r diesen JFrame:
        this.setTitle("B U T T O N V I E W @ H K A");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(480, 480);
        this.setVisible(true);

        // Formatiere die Anzeige f�r die Spielsituation:
        this.informLabel = new JLabel();
        this.informLabel.setBackground(new Color(100, 25, 100));
        this.informLabel.setForeground(new Color(0, 255, 0));
        this.informLabel.setFont(new Font("Ink Free", Font.BOLD, 48));
        this.informLabel.setHorizontalAlignment(JLabel.CENTER);
        this.informLabel.setOpaque(true);
        this.add(informLabel, BorderLayout.NORTH);

        // Font f�r Beschriftung der Spiel-Buttons
        Font font = new Font("Ink Free", Font.BOLD, 80);

        // Erzeuge das Panel mit seinen Spielfeld-Buttons:
        panel = new JPanel();
        panel.setLayout(new GridLayout(model.getSize(), model.getSize()));
        board = new JButton[model.getSize()][model.getSize()];
        for (int row = 0; row < model.getSize(); row++)
            for (int col = 0; col < model.getSize(); col++) {
                board[row][col] = new JButton(TTT_Constants.EMPTY.tag);
                board[row][col].setFont(font);
                board[row][col].setFocusable(false);
                board[row][col].addActionListener(new OnAction(row, col));
                panel.add(board[row][col]);
            }
        this.add(panel); // F�ge das panel diesem JFrame hinzu:
    }

    /**
     * F�gt den spezifizierten Controller diesem TTT_ButtonView hinzu.
     */
    @Override public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Zeigt in den Spielfeld-Buttons die im sepezifizierten model
     * gespeicherten Eintr�ge der Spieler.
     */
    @Override public void showBoard(TTT_Model model) {
        for (int row = 0; row < model.getSize(); row++)
            for (int col = 0; col < model.getSize(); col++)
                board[row][col].setText(model.getEntry(row, col).tag);
    }

    /**
     * Zeigt den Spieler an, der als n�chster dran ist.
     */
    @Override public void showNextPlayer(TTT_Constants player) {
        this.informLabel.setText(player.tag + "  next");
    }

    /**
     * Zeigt unentschieden an oder den Spieler, der gewonnen hat.
     */
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

    /**
     * Zeigt das board im Zustand zu Spielbeginn an.
     */
    @Override public void resetView() {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                board[row][col].setEnabled(true);
    }

}
