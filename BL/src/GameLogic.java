public class GameLogic {
    private GameBoard Board;
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
    }
    public void displayBoard(){
        Board.displayBoard();
    }
    public void SaveState(){

    }
    public void updateBoard(){

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
