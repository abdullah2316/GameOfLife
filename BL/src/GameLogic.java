package com.company;

public class GameLogic {
    private GameBoard Board;
    private GameBoard NewBoard;
    private boolean State;
    private int Zoom_Factor;
    private int Counter;
    private int Speed;
    private int SpeedLimit;

    public GameLogic(){
        Zoom_Factor = 1;
        Counter = 0;
        Speed = 1;
        SpeedLimit = 10;
    }
    public void ConstructBoard(){
        Board = new GameBoard();
        NewBoard = new GameBoard();
    }
    public void displayBoard(){
        Board.displayBoard();
    }
    public void SaveState(){

    }
    public void updateBoard(){
        int return_count = 0;
        //System.out.print(Board.check_neighbor(2,4));
        for(int i = 1; i < Board.Size() -1; i++)
        {
            for(int j = 1 ; j < Board.Size() -1; j++)
            {
                return_count = Board.check_neighbor(i, j);
                if ((return_count == 3) && Board.isAlive(i,j) == false )
                {
                    NewBoard.isClicked(i,j);
                }
                else if( (return_count < 2 || return_count > 3) && Board.isAlive(i , j) == true )
                {
                    NewBoard.isClicked(i,j);
                }
            }
        }
        Board.copy(NewBoard);

    }
    public void updateCounter(int counter){
        Counter = counter;
    }
    public void resetCounter(){
        Counter = 0;
    }
    public void incrementSpeed(){
        if (Speed < SpeedLimit)
            Speed++;
    }
    public void decrementSpeed(){
        if (Speed > 1)
            Speed--;
    }
    public void ResetGrid(){
        Board.ResetBoard();
    }
    public void Start(){
        State = true;
    }
    public void Stop(){
        State = false;
    }
    public void Reset(){
        Zoom_Factor = 1;
        Counter = 0;
        Speed = 1;
        //Board.ResetBoard();
    }
    public void Grow(){
    }
    public void Shrink(){
    }
}
