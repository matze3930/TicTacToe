package com.company.games;

import java.util.LinkedList;
import java.util.List;

import com.company.controller.Controller;
import com.company.controller.TTT_Controller;
import com.company.model.TTT_Model;
import com.company.view.TTT_ButtonView;
import com.company.view.TTT_ConsoleView;
import com.company.view.TTT_LabelView;
import com.company.view.View;


public class TTT_Game {
    // The model - the logical board containing the fields:
    private TTT_Model model;

    // All views displaying the model:
    private TTT_ButtonView buttonView;	// view using buttons
    private TTT_LabelView labelView;	// view using labels
    private TTT_ConsoleView consoleView;// view printing on console

    // A Collection containing all views:
    private List<View> views;

    // The Controller coordinating actions between the model and the views
    private Controller controller;

    /**
     * Construct a TicTacToe game object with all sub-parts
     * including a model of the requested size.
     */
    public TTT_Game(int size) {
        // Create the model - a logical board of the specified size:
        this.model = new TTT_Model(size);

        // Create all views, each with the model as data to be displayed:
        this.buttonView = new TTT_ButtonView(model);
        this.labelView = new TTT_LabelView(model);
        this.consoleView = new TTT_ConsoleView(model);

        // Create a list and add all views to it:
        this.views = new LinkedList<>();
        this.views.add(buttonView);
        this.views.add(labelView);
        this.views.add(consoleView);

        // Create a controller to coordinate actions between model und view(s):
        this.controller = new TTT_Controller(model, views);
    }


    public static void main(String[] args) {
        final int boardSize = 4; // or 4, 5, ...?
        TTT_Game ticTacToe = new TTT_Game(boardSize);
        ticTacToe.controller.startGame();
    }

}
