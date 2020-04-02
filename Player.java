package com.board.app;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player implements Runnable{
    private String name;
    private boolean won=false;
    List<Integer> solution =new ArrayList<>();
    Board board;
    public Player(String name, Board board){
        this.name=name;
        this.board=board;
    }
    private synchronized boolean isProgression(List<Integer> list,int k){
        if(list.size()<2 && list.size()<k)
            return false;
        int ratio=list.get(1)-list.get(0);
        for(int i=2;i<list.size();i++){
            if(list.get(i)==0)
                list.set(i,list.get(i-1)+ratio);
            if(list.get(i)-ratio!=list.get(i-1))
                return false;
        }
        return true;
    }

    public synchronized void showSolution(List<Integer> solution){
        synchronized (solution){
        System.out.println("solutia lui " + name + " este: ");
        for(Integer i : solution) {
            System.out.print(i);
            System.out.print(" ");
        }
        }
    }

    public void run() {
        synchronized (board) {
            System.out.println(board.getTable().size());
            while (!won && board.getTable().size()!=0) {
                Random rand = new Random();
                int index = rand.nextInt(board.getTable().size());

                //System.out.println(index);
                solution.add(board.getTable().get(index));
                board.getTable().remove(index);
                if (isProgression(solution, board.getK()))
                    won = true;
                if (board.getTable().size() == 0) {
                    System.out.println("nu mai sunt elemente pe tabla");
                }
            }
        }
        showSolution(solution);


    }

}
