package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class WinningAlgo {
   HashMap<Integer,HashMap<Character,Integer>>rowMaps = new HashMap<>();
   HashMap<Integer,HashMap<Character,Integer>>colMaps = new HashMap<>();
   HashMap<Character,Integer>leftDiagonalMap = new HashMap<>();
   HashMap<Character,Integer>rightDiagonal = new HashMap<>();



   public boolean checkWinner(Board board, Move move){
      int row = move.getCell().getRow();
      int col = move.getCell().getCol();

      Character character = move.getPlayer().getSymbol().getS();
//      ROW
      if (!rowMaps.containsKey(row)) {
         rowMaps.put(row, new HashMap<>());
      }
      HashMap<Character, Integer> currRowMap = rowMaps.get(row);

      if (!currRowMap.containsKey(character)) {
         currRowMap.put(character, 1);
      } else {
         currRowMap.put(character, currRowMap.get(character) + 1);
      }
      if (currRowMap.get(character) == board.getSize()) {
         return true;
      }
//      COL
      if(!colMaps.containsKey(col)){
         colMaps.put(col,new HashMap<>());
      }
      HashMap<Character,Integer> currColMap = colMaps.get(col);

      if(!currColMap.containsKey(character)){
         currColMap.put(character,1);
      }
      else{
         currColMap.put(character,currColMap.get(character)+1);
      }
      if(currColMap.get(character) == board.getSize()){
         return  true;
      }
//      left Diagonal
      if (row == col) {
         if (!leftDiagonalMap.containsKey(character)) {
            leftDiagonalMap.put(character, 1);
         } else {
            leftDiagonalMap.put(character, leftDiagonalMap.get(character) + 1);
         }

         if (leftDiagonalMap.get(character) == board.getSize()) {
            return true;
         }
      }
//      right diagonal
      if(row + col == board.getSize()-1){
         if(!rightDiagonal.containsKey(character)){
            rightDiagonal.put(character,1);
         }
         else{
            rightDiagonal.put(character,rightDiagonal.get(character)+1);
         }
         if(rightDiagonal.get(character) == board.getSize()){
            return true;
         }
      }
      return false;


   }
}
