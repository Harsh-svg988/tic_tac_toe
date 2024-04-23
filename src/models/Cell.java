package models;

import enums.CellState;

public class Cell {
    int row;
    int col;
    Player player;
    private CellState cellState;
    Cell(int row,int col,Player player, CellState cellState){
        this.row = row;
        this.col = col;
        this.player = player;
        this.cellState = cellState;
    }
}
