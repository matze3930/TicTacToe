package com.company.view;

import com.company.games.TTT_Constants;
import com.company.controller.Controller;
import com.company.model.TTT_Model;

public interface View {

	// Fügt den spezifizierten Controller einer View hinzu.
	void setController(Controller controller);
	
	// Eine view zeigt in den Spielfeld-Buttons die im sepezifizierten model gespeicherten Einträge an.
	void showBoard(TTT_Model board);
	
	// Eine view zeigt den Spieler an, der als nächster dran ist.
	void showNextPlayer(TTT_Constants player);
	
	// Eine view zeigt "Unentschieden" oder den Gewinner an.
	void showWinner(TTT_Constants player);

	// Eine view zeigt das board im Zustand zu Spielbeginn an.
	void resetView();
}
