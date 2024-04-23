package models;

import enums.GameState;

import java.util.List;

public class Game {
    Board board;
    List<Player> listOfPlayer;
    int nextPlayerMoveIndex;
    List<Move>listOfMoves;
    GameState gameState;
    Winner winner;



}
