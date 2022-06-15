package com.company.controller;

public interface Controller {
	
	/**
	 * This method is called to inform a Controller that the 
	 * current player selected the board's field (row, col).
	 */
	public void select(int rowOfBoard, int colOfBoard);
	
	
	/**
	 * Causes a Controller to randomly choose the first player
	 * and to make sure that all views are prepared for the next play. 
	 */
	public void startGame();
}
