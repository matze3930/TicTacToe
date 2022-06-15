package com.company.model;

import com.company.games.TTT_Constants;

public class TTT_Model {
	TTT_Constants[][] board; // Das logische Spielfeld
	
	public TTT_Model(int size) {
		this.board = new TTT_Constants[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				board[i][j] = TTT_Constants.EMPTY;
	}
	
	public void reset()	{
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = TTT_Constants.EMPTY;
	}
	
	public int getSize() {
		return board.length;
	}
	
	public void setEntry(int row, int col, TTT_Constants player) {		
		if (board[row][col] == TTT_Constants.EMPTY) // andernfalls "besetzt"
			board[row][col] = player;
	}
	
	public TTT_Constants getEntry(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * @param row the board's row to be considered
	 * @param player the player to be looked for
	 * @return true iff each entry of the specified row contains the specified player
	 */
	private boolean checkRow(int row, TTT_Constants player) {
		for (int col = 0; col < board[row].length; col++)
			if (board[row][col] != player)
				return false;
		return true;
	}
	
	/**
	 * @param col the board's column to be considered
	 * @param player the player to be looked for
	 * @return true iff each entry of the specified column contains the specified player
	 */
	private boolean checkCol(int col, TTT_Constants player) {
		for (int row = 0; row < board.length; row++)
			if (board[row][col] != player)
				return false;
		return true;
	}
	
	private boolean checkMajorDiagonal(TTT_Constants player) {
		for (int counter = 0; counter < board.length; counter++)
			if ( board[counter][counter] != player)
				return false;
		return true;
	}
	/**
	private boolean checkMinorDiagonal(TTT_Constants player) {
		// To Do
	}
	
	public TTT_Constants checkWin_LargeBoard() {
		// To Do
	}
	*/
	public TTT_Constants checkWin() {
		final String win1String = 
				TTT_Constants.PLAYER1.tag + TTT_Constants.PLAYER1.tag + TTT_Constants.PLAYER1.tag;
		final String win2String = 
				TTT_Constants.PLAYER2.tag + TTT_Constants.PLAYER2.tag + TTT_Constants.PLAYER2.tag;
		final String[] stringsToCheck = { // Hier nur f�r ein 3x3 Spielfeld
			board[0][0].tag + board[0][1].tag + board[0][2].tag, // rows 1,2,3
			board[1][0].tag + board[1][1].tag + board[1][2].tag,
			board[2][0].tag + board[2][1].tag + board[2][2].tag,
			board[0][0].tag + board[1][0].tag + board[2][0].tag, // columns 1,2,3
			board[0][1].tag + board[1][1].tag + board[2][1].tag,
			board[0][2].tag + board[1][2].tag + board[2][2].tag,
			board[0][0].tag + board[1][1].tag + board[2][2].tag, // both diagonals
			board[0][2].tag + board[1][1].tag + board[2][0].tag, };
		
		for (String string : stringsToCheck) {
			if (string.equals(win1String))
				return TTT_Constants.PLAYER1;
			if (string.equals(win2String))
				return TTT_Constants.PLAYER2;
		}
		// Hier: Es gibt (noch) keinen Sieger.
		// Weiterspielen, falls noch nicht alle Felder belegt sind 
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board.length; col++)
				if (board[row][col] == TTT_Constants.EMPTY)
					return TTT_Constants.CONTINUE;
		// Hier: Kein Sieger UND alle Felder belegt ==> unentschieden (tie)
		return TTT_Constants.TIE;
	}
}
