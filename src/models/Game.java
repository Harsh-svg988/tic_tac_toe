package models;

import exceptions.InvalidMoveException;
import strategies.WinningAlgo;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int nextPlayerMoveIndex;
    private List<Move>moves;
    private GameState gameState;
    private Player winner;
    private WinningAlgo winningAlgo;

    public Game(int dimension,List<Player>players){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
        this.winningAlgo = new WinningAlgo();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void printBoard(){

        this.board.printBoard();
    }
    public boolean validateMove (Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove(Game game)throws InvalidMoveException{
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is "+ currentPlayer.getName()+"move");


//      move that current player wants to play
        Move move = currentPlayer.makeMove(board);
//      validate the move
        if(!validateMove(move)){
            throw new InvalidMoveException("Invalid move made by " + currentPlayer.getName());
        }
        int row  = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMove = new Move(cellToChange,currentPlayer);
        moves.add(finalMove);
        nextPlayerMoveIndex = (nextPlayerMoveIndex +1) % players.size();

/*
        WinningAlgo winningAlgo = new WinningAlgo();
*/

        if (winningAlgo.checkWinner(board, finalMove)) {
            gameState = GameState.ENDED;
            winner = currentPlayer;
        }

    }
}
