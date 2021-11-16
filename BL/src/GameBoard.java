package com.company;

public class GameBoard {
    private Cell[][] Cells;
    int Size;

    public GameBoard(){
        Cells = new Cell[10][10];
        Size = 10;
        for(int i=0; i < 10 ; i++){
            for(int j=0; j < 10 ; j++){
                Cells[i][j] = new Cell();
            }
        }
        /*Cells[2][4].ChangeState();
        Cells[3][4].ChangeState();
        Cells[4][4].ChangeState();
        Cells[3][5].ChangeState();
        Cells[3][3].ChangeState();*/
        
        Cells[4][4].ChangeState();
        Cells[4][3].ChangeState();
        Cells[4][5].ChangeState();
    }
    public GameBoard(int size){
        Size = size;
        Cells = new Cell[size][size];
    }

    public void displayBoard(){
        for(int i=0; i < 10 ; i++){
            for(int j=0; j < 10 ; j++){
                if (Cells[i][j].check_IsAlive())
                    System.out.print(" 0 ");
                else
                    System.out.print(" . ");
            }
            System.out.println();
        }
    }

    public void isClicked(int row, int column){
        Cells[row][column].ChangeState();
    }
    public boolean isAlive(int row, int column){
        return Cells[row][column].check_IsAlive();
    }
    public void ResetBoard(){

    }
    public boolean getCell (int row, int column) {
        return Cells[row][column].check_IsAlive();
    }
    public int Size (){
        return Size;
    }
    public int check_neighbor(int row, int col)
    {
        int count = 0;
        if (row > 0 && row < Size - 1 && col > 0 && col < Size - 1) {
            if (Cells[row - 1][col].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row + 1][col].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row][col - 1].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row][col + 1].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row - 1][col - 1].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row - 1][col + 1].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row + 1][col - 1].check_IsAlive() == true) {
                count++;
            }
            if (Cells[row + 1][col + 1].check_IsAlive() == true) {
                count++;
            }
        }

        return count;
    }
    public void copy (GameBoard obj)
    {
        for (int i=0; i< Size; i++)
        {
            for(int j=0; j< Size; j++)
            {
                Cells[i][j].setAlive(obj.Cells[i][j].check_IsAlive());
            }
        }
    }
}
