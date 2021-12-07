package BL;

import java.util.ArrayList;

public class GameBoard {

    int Row_Size;
    int Col_Size;
    int Size;
    private Cell[][] Cells;

    public GameBoard() {
        Row_Size = 40;
        Col_Size = 100;
        Cells = new Cell[Row_Size][Col_Size];
        Size = 10;
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                Cells[i][j] = new Cell();
            }
        }
    }

    public GameBoard(ArrayList<Integer> cells) {
        Row_Size = 40;
        Col_Size = 100;
        Cells = new Cell[Row_Size][Col_Size];
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                Cells[i][j] = new Cell();

            }
        }
        for (int i = 0; i < cells.size(); i += 2) {
            Cells[cells.get(i)][cells.get(i + 1)].ChangeState();
        }
    }


    public ArrayList<Integer> get_Alive() {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                if (Cells[i][j].check_IsAlive()) {
                    array.add(i);
                    array.add(j);
                }
            }
        }
        return array;
    }

    public void isClicked(int row, int column) {
        Cells[row][column].ChangeState();
        System.out.println("row: " + row + " col: " + column);
    }

    public boolean isAlive(int row, int column) {
        return Cells[row][column].check_IsAlive();
    }

    public ArrayList<Integer> ClearBoard() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                if (Cells[i][j].check_IsAlive()) {
                    Cells[i][j].setAlive(false);
                    array.add(i);
                    array.add(j);
                }
            }
        }
        return array;
    }

    //fixed pre-built initial pattern
    public ArrayList<Integer> Pattern() {
        Cells[20][51].ChangeState();
        Cells[21][51].ChangeState();
        Cells[22][51].ChangeState();
        Cells[21][52].ChangeState();
        Cells[21][50].ChangeState();
        ArrayList<Integer> array = new ArrayList<>();
        array.add(20);
        array.add(51);
        array.add(21);
        array.add(51);
        array.add(22);
        array.add(51);
        array.add(21);
        array.add(52);
        array.add(21);
        array.add(50);
        return array;
    }

    public ArrayList<Integer> ResetBoard() {
        ClearBoard();
        return Pattern();
    }

    public boolean getCell(int row, int column) {
        return Cells[row][column].check_IsAlive();
    }

    public int getRowSize() {
        return Row_Size;
    }

    public int getColSize() {
        return Col_Size;
    }

    public int check_neighbor(int row, int col) {
        int count = 0;
        if (row > 0) if (Cells[row - 1][col].check_IsAlive()) count++;
        if (row > 0 && col > 0) if (Cells[row - 1][col - 1].check_IsAlive()) count++;
        if (row > 0 && col < Col_Size - 1) if (Cells[row - 1][col + 1].check_IsAlive()) count++;
        if (row < Row_Size - 1) if (Cells[row + 1][col].check_IsAlive()) count++;
        if (row < Row_Size - 1 && col > 0) if (Cells[row + 1][col - 1].check_IsAlive()) count++;
        if (row < Row_Size - 1 && col < Col_Size - 1) if (Cells[row + 1][col + 1].check_IsAlive()) count++;
        if (row < Row_Size - 1 && col > 0) if (Cells[row][col - 1].check_IsAlive()) count++;
        if (col < Col_Size - 1) if (Cells[row][col + 1].check_IsAlive()) count++;

        return count;
    }

    public void copy(GameBoard obj) {
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                Cells[i][j].setAlive(obj.Cells[i][j].check_IsAlive());
            }
        }
    }

    public void copyfromArray(int[] array) {
        int index = 1;
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                if (index < array[0]) {
                    if (array[index] == i && array[index + 1] == j) {
                        Cells[i][j].setAlive(true);
                        index += 2;
                    } else
                        Cells[i][j].setAlive(false);
                } else
                    Cells[i][j].setAlive(false);
            }
        }
    }

    public ArrayList<Integer> copyToArray() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < Row_Size; i++)
            for (int j = 0; j < Col_Size; j++)
                if (Cells[i][j].check_IsAlive()) {
                    array.add(i);
                    array.add(j);
                }
        return array;
    }


}
