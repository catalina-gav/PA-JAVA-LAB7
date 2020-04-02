package com.board.app;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Integer> table=new ArrayList<>();
    private int m;
    private int k;
    Token token=new Token();
    public Board(int m,int k){
        this.m=m;
        this.k=k;
    }

    public int getK() {
        return k;
    }

    public List<Integer> getTable() {
        return table;
    }

    public void setTable(int n) {
        for(int i=0;i<n;i++){
            int tok=token.createToken(m);
            while(table.contains(tok)==true && tok!=0){
                tok=token.createToken(m);
            }
            table.add(tok);

        }
    }
}
