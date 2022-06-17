package com.company.view;

import com.company.controller.Controller;
import com.company.games.TTT_Constants;
import com.company.model.TTT_Model;

/**
 * This view is passive - just for displaying purposes.
 */
public class TTT_ConsoleView implements View {
    private String[][] consoleBoard;

    /**
     * Create the board to be displayed as 2-dimensional String array
     * corresponding to the specified model.
     */
    public TTT_ConsoleView(TTT_Model model) {
        this.consoleBoard = new String[model.getSize()][model.getSize()];
        for (int row = 0; row < model.getSize(); row++)
            for (int col = 0; col < model.getSize(); col++)
                consoleBoard[row][col] = model.getEntry(row, col).tag;
    }

    @Override public void setController(Controller controller) {
        // Do nothing: this view is passive only
    }

    @Override public void showBoard(TTT_Model board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                TTT_Constants entry = board.getEntry(row, col);
                if (entry == TTT_Constants.EMPTY)
                    System.out.print("-" + "\t");
                else
                    System.out.print(entry.tag + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("-----------------");
    }

    @Override public void showNextPlayer(TTT_Constants currentPlayer) {
        // do nothing to keep this view clear
    }

    @Override public void showWinner(TTT_Constants player) {
        System.out.println("Winner: " + player.tag + "\n");
    }

    @Override public void resetView() {
        // Nothing to do in this view
    }

}
