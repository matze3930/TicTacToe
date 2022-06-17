package com.company.games;

/**
 * Dieser Aufz�hlungstyp enth�lt alle Namen f�r Spieler und Spielsituationen,
 * wie sie beim Spiel TicTacToe vorkommen. Jede enum-Konstante enth�lt einen
 * String, der beispielsweise als Anzeigesymbol im "board" genutzt werden kann.
 */
public enum TTT_Constants {
    PLAYER1("X"), PLAYER2("O"), EMPTY(""), CONTINUE("continue"), TIE("tie");

    public String tag; // Das "Text-Etikett" f�r diese Konstante

    TTT_Constants(String tag) {
        this.tag = tag;
    }

}
