package com.company.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.company.model.TTT_Model;
import com.company.view.View;
import com.company.games.TTT_Constants;

public class TTT_Controller implements Controller {
    TTT_Model model;                // Das Model
    List<View> views;                // Die View(s)
    TTT_Constants currentPlayer;    // Der aktuelle Spieler

    public TTT_Controller(TTT_Model model, List<View> views) {
        this.model = model;
        this.views = views;
    }

    @Override public void select(int rowOfBoard, int colOfBoard) {
        if (model.getEntry(rowOfBoard, colOfBoard) == TTT_Constants.EMPTY) {
            model.setEntry(rowOfBoard, colOfBoard, currentPlayer);

            views.forEach(view -> {
                view.showBoard(this.model);
            });
            if (model.checkWin() == TTT_Constants.CONTINUE) {
                currentPlayer = nextPlayer(currentPlayer);
                views.forEach(view -> {
                    view.showNextPlayer(currentPlayer);
                });

            } else {
                views.forEach(view -> {
                    view.showWinner(model.checkWin());
                });
                gameFinished();
            }
        }
    }

    @Override public void startGame() {

        model.reset();
        //Choose start player
        currentPlayer = randomlyChooseStartPlayer();
        views.forEach(view -> {
            System.out.println("Board" + view.toString());
            //Connect controller with view
            view.setController(this);
            view.resetView();
            //Display board
            view.showBoard(this.model);
            //Display start player
            view.showNextPlayer(currentPlayer);
        });
    }

    private TTT_Constants nextPlayer(TTT_Constants currentPlayer) {
        if (currentPlayer == TTT_Constants.PLAYER1)
            return TTT_Constants.PLAYER2;
        else
            return TTT_Constants.PLAYER1;
    }

    private TTT_Constants randomlyChooseStartPlayer() {
        List<TTT_Constants> players = Arrays.asList(TTT_Constants.PLAYER1, TTT_Constants.PLAYER2);
        Random rand = new Random();
        return players.get(rand.nextInt(players.size()));
    }

    public void gameFinished() {
        int valueOptionPane = JOptionPane.showConfirmDialog(null, "Try Again?", "Game finished.",
                JOptionPane.YES_NO_OPTION);
        if (valueOptionPane == 0) {
            startGame();
        } else {
            System.exit(0);
        }
    }
}
