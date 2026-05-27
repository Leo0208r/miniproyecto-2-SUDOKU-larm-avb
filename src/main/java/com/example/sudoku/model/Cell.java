package com.example.sudoku.model;

public class Cell {
    private int value;
    private boolean fixed;
    public Cell(int value, boolean fixed){
        this.value=value;
        this.fixed=fixed;
    }
    public int getValue(){
        return value;
    }
    public boolean getFixed(){
        return fixed;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setFixed(boolean fixed){
        this.fixed=fixed;
    }
}
