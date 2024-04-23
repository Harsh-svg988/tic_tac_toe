package models;

import enums.PlayerType;

public class Player {
    String name;
    Symbol symbol;
    PlayerType playerType;
    Player(String name,Symbol symbol,PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

}
