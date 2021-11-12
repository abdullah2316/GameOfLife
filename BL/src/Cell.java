public class Cell {
    private int Row;
    private int Column;
    private boolean isAlive;

    public Cell(int r, int c, boolean alive)
    {
        Row = r;
        Column = c;
        isAlive = alive;
    }

    public boolean check_IsAlive()
    {
        return isAlive;
    }

    public void ChangeState()
    {
        isAlive = !isAlive;
    }

    public boolean isNeighbour(Cell otherCell)
    {
        return ((Row == otherCell.Row + 1 || Row == otherCell.Row - 1) && (Column == otherCell.Column + 1 || Column == otherCell.Column + 1));
    }
}
