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
        Cells[5][5].ChangeState();
        Cells[5][4].ChangeState();
        Cells[4][4].ChangeState();
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
                    System.out.print(" . ");
                else
                    System.out.print(" O ");
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
        return Cells.length;
    }
}

