package com.board.app;


public class Game {
    public static void main(String[] args) {
        Board board=new Board(50,10);
        board.setTable(500);
        Runnable player1 = new Player("Gigel",board);
        Runnable player2= new Player("Paul",board);
        new Thread(player1).start();
        new Thread(player2).start();
    }
}
