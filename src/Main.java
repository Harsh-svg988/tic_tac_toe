import controllers.GameController;
import exceptions.InvalidMoveException;
import models.*;

import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);


        int dimension = 3;
        List<Player> players = List.of(
                new Player("harsh",new Symbol('$'), PlayerType.HUMAN),
                new Bot("Bot",new Symbol('*'),PlayerType.BOT,BotDifficultyLevel.EASY)
        );
        Game game = gameController.startGame(dimension,players);
//        gameController.printBoard(game);
        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            //1. print the board.
            gameController.printBoard(game);

            //2. Player's turn
            gameController.makeMove(game);
        }

        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}
