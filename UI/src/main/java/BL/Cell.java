package BL;

public class Cell {
    private boolean isAlive;

    public Cell() {
        isAlive = false;
    }

    public Cell(boolean alive) {
        isAlive = alive;
    }

    public boolean check_IsAlive() {
        return isAlive;
    }

    public void ChangeState() {
        isAlive = !isAlive;
    }

    public void setAlive(boolean val) {
        isAlive = val;
    }
}
