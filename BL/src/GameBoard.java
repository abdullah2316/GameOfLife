import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameBoard {
    private Cell[][] Cells;
    int Size;

    public GameBoard(){
        Cells = new Cell[10][10];
        Size = 10;
        for(int i=0; i < Size ; i++) {
            for (int j = 0; j < Size; j++) {
                Cells[i][j] = new Cell();
            }
        }
    }
    public GameBoard(int size){
        Size = size;
        Cells = new Cell[size][size];
        for(int i=0; i < Size ; i++){
            for(int j=0; j < Size ; j++){
                Cells[i][j] = new Cell();
            }
        }
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

    public ArrayList<Integer> ClearBoard() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if(Cells[i][j].check_IsAlive()) {
                    Cells[i][j].setAlive(false);
                    array.add(i);
                    array.add(j);
                }
            }
        }
        return array;
    }

    public ArrayList<Integer> Pattern() {
        Cells[2][4].ChangeState();
        Cells[3][4].ChangeState();
        Cells[4][4].ChangeState();
        Cells[3][5].ChangeState();
        Cells[3][3].ChangeState();
        ArrayList<Integer> array = new ArrayList<Integer>(10);
        array.add(2);
        array.add(4);
        array.add(3);
        array.add(4);
        array.add(4);
        array.add(4);
        array.add(3);
        array.add(5);
        array.add(3);
        array.add(3);
        return array;
    }

    public ArrayList<Integer> ResetBoard(){
        ClearBoard();
        return Pattern();
    }

    public boolean getCell (int row, int column) {
        return Cells[row][column].check_IsAlive();
    }

    public int getSize (){
        return Size;
    }
    
    public int check_neighbor(int row, int col) {
        int count = 0;

        if (row > 0)                            if (Cells[row - 1][col].check_IsAlive())        count++;
        if (row > 0 && col > 0)                 if (Cells[row - 1][col - 1].check_IsAlive())    count++;
        if (row > 0 && col < Size - 1)          if (Cells[row - 1][col + 1].check_IsAlive())    count++;
        if (row < Size - 1)                     if (Cells[row + 1][col].check_IsAlive())        count++;
        if (row < Size - 1 && col > 0)          if (Cells[row + 1][col - 1].check_IsAlive())    count++;
        if (row < Size - 1 && col < Size - 1)   if (Cells[row + 1][col + 1].check_IsAlive())    count++;
        if (row < Size - 1 && col > 0)          if (Cells[row][col - 1].check_IsAlive())        count++;
        if (col < Size - 1)                     if (Cells[row][col + 1].check_IsAlive())        count++;

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
    public void copyfromArray (int[] array)
    {
        int index = 1;
        for (int i=0; i< Size; i++)
        {
            for(int j=0; j< Size; j++)
            {
                if(index < array[0]) {
                    if (array[index] == i && array[index + 1] == j) {
                        Cells[i][j].setAlive(true);
                        index += 2;
                    }
                    else
                        Cells[i][j].setAlive(false);
                }
                else
                    Cells[i][j].setAlive(false);
            }
        }
    }
    public ArrayList<Integer> copyToArray () {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < Size; i++)
            for (int j = 0; j < Size; j++)
                if (Cells[i][j].check_IsAlive()) {
                    array.add(i);
                    array.add(j);
                }
        return array;
    }
}
