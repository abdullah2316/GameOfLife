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
        State = true;
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
        GameBoard temp = new GameBoard(Board.getSize());
        temp.copy(Board);
        int size = Board.getSize();
        for(int i = 0 ; i < size ; i++)
        {
            for(int j = 0 ; j < size ; j++)
            {
                int Neighbor_Count = Board.check_neighbor(i, j);

                if (( Neighbor_Count == 3) &&! Board.isAlive(i, j))
                    temp.isClicked(i,j);

                else if(( Neighbor_Count < 2 || Neighbor_Count > 3) && Board.isAlive(i, j))
                    temp.isClicked(i,j);
            }
        }
        Board.copy(temp);
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
        Board.ResetBoard();
    }
}
