import java.awt.*;

public class board {
    private Cell[][] Cells;
    int Size;
    //  private Object Cell;

    public board() {
        Cells = new Cell[15][15];
        Size = 15;
        for(int i=0; i < 15 ; i++)
            for (int j = 0; j <15; j++)
                Cells[i][j] = new Cell();
        Cells[2][4].ChangeState();
        Cells[3][4].ChangeState();
        Cells[4][4].ChangeState();
        Cells[3][5].ChangeState();
        Cells[3][3].ChangeState();
    }

    public board(int size){
        Size = size;
        Cells = new Cell[size][size];
        for(int i=0; i < size ; i++)
            for (int j = 0; j < size; j++)
                Cells[i][j] = new Cell();
    }

    public void clear() {

        for(int i = 0; i < Size; i++) {
            for(int j = 0; j < Size; j++) {
                Cells[i][j] = null;
                System.out.println(System.lineSeparator().repeat(100));
            }
        }
    }

    public void displayMenu(){
        System.out.println("+------------------------+");
        System.out.println("| Welcome to GamE of LifE  |");
        System.out.println("+------------------------+");

    }


    public void displayBoard() {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++)
                if (Cells[i][j].check_IsAlive())
                    System.out.print(" 0 ");
                else
                    System.out.print(" . ");
            System.out.println();
        }
    }


}
