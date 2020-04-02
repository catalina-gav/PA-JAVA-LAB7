package com.board.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Token {
    private int token;

    public int createToken(int m) {
        Random rand = new Random();
        this.token=rand.nextInt(m);
        return this.token;
    }
}
