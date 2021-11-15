import java.io.IOException;
import java.awt.AWTException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // write your code here
        int i = 0;
        GameLogic object = new GameLogic();
        object.ConstructBoard();
        object.displayBoard();
        Thread.sleep(1000);
        clearScreen();
        while(i < 2) {

            object.updateBoard();
            System.out.println();
            object.displayBoard();
            Thread.sleep(1000);
            clearScreen();
        }
    }
    public static void clearScreen() throws InterruptedException {
        System.out.println(System.lineSeparator().repeat(100));
    }

}

