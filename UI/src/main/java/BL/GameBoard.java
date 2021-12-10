package BL;

import java.util.ArrayList;

public class GameBoard {

    int Row_Size;
    int Col_Size;
    private Cell[][] Cells;

    public GameBoard(int r, int c) {
        Row_Size = r;
        Col_Size = c;
        Cells = new Cell[Row_Size][Col_Size];
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                Cells[i][j] = new Cell();
            }
        }
    }

    public GameBoard(ArrayList<Integer> cells, int r, int c) {
        Row_Size = r;
        Col_Size = c;
        Cells = new Cell[Row_Size][Col_Size];
        for (int i = 0; i < Row_Size; i++) {
            for (int j = 0; j < Col_Size; j++) {
                Cells[i][j] = new Cell();

            }
        }

        for (int i = 0; i < cells.size(); i += 2) {
            Cells[cells.get(i) % Row_Size][cells.get(i + 1) % Col_Size].ChangeState();
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
    }

    public boolean isAlive(int row, int column) {
        return Cells[row][column].check_IsAlive();
    }

    public ArrayList<Integer> ClearBoard() {
        ArrayList<Integer> array = new ArrayList<>();
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
        ArrayList<Integer> array = new ArrayList<>();
        if (Row_Size == 40) {
            Cells[20][51].ChangeState();
            Cells[21][51].ChangeState();
            Cells[22][51].ChangeState();
            Cells[21][52].ChangeState();
            Cells[21][50].ChangeState();

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

        } else {
            Cells[3][5].ChangeState();
            Cells[3][6].ChangeState();
            Cells[4][7].ChangeState();
            Cells[5][8].ChangeState();
            Cells[6][6].ChangeState();

            array.add(3);
            array.add(5);
            array.add(3);
            array.add(6);
            array.add(4);
            array.add(7);
            array.add(5);
            array.add(8);
            array.add(6);
            array.add(6);

        }
        return array;
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


}
